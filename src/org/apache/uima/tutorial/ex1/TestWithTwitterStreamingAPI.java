package org.apache.uima.tutorial.ex1;

import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.tutorial.Sentiment;
import org.wso2.carbon.databridge.agent.thrift.DataPublisher;
import org.wso2.carbon.databridge.agent.thrift.exception.AgentException;
import org.wso2.carbon.databridge.commons.Event;
import org.wso2.carbon.databridge.commons.exception.AuthenticationException;
import org.wso2.carbon.databridge.commons.exception.DifferentStreamDefinitionAlreadyDefinedException;
import org.wso2.carbon.databridge.commons.exception.MalformedStreamDefinitionException;
import org.wso2.carbon.databridge.commons.exception.NoStreamDefinitionExistException;
import org.wso2.carbon.databridge.commons.exception.StreamDefinitionException;
import org.wso2.carbon.databridge.commons.exception.TransportException;
import org.wso2.carbon.sample.twitterfeeds.KeyStoreUtil;

import twitter4j.Place;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TestWithTwitterStreamingAPI {

	public static final String STREAM_NAME1 = "publicTwitterInputFeed";
	public static final String VERSION1 = "1.0.0";
	public static DataPublisher dataPublisher;
	public static int twittercounter = 0;
	public String sentiment = "";
	public String location = "";
	final AnalysisEngine analysisEngine = null;

	public String defineInputStream() throws AgentException,
			StreamDefinitionException, MalformedStreamDefinitionException,
			DifferentStreamDefinitionAlreadyDefinedException,
			MalformedURLException, AuthenticationException, TransportException {

		System.out.println("Starting Statistics Agent");
		KeyStoreUtil.setTrustStoreParams();

		dataPublisher = new DataPublisher("tcp://localhost:7611", "admin",
				"admin");

		String streamID = "";

		try {
			streamID = dataPublisher.findStream(STREAM_NAME1, VERSION1);
			System.out.println("Stream already defined");
		} catch (NoStreamDefinitionExistException e) {
			System.out.println("No such Stream defined");
		}

		return streamID;
	}

	public void receiveTwitterFeed(String tweetText, String location,
			String streamID) {

		this.location = location;

		AnalysisEngineInitiator uimaPipeline = null;
		try {
			uimaPipeline = new AnalysisEngineInitiator();
		} catch (ResourceInitializationException e) {
			e.printStackTrace();
		}

		// run the sample document through the pipeline
		JCas output = null;
		try {
			output = uimaPipeline.process(tweetText);
		} catch (UIMAException e) {
			e.printStackTrace();
		}

		FSIndex index4 = output.getAnnotationIndex(Sentiment.type);
		for (Iterator<Sentiment> it = index4.iterator(); it.hasNext();) {
			Sentiment annotation = it.next();
			System.out.println("New AN3..." + annotation.getSentiment()
					+ " Location : " + location);

			this.sentiment = annotation.getSentiment();
		}

		if (!streamID.isEmpty()) {
			try {

				System.out.println("Publishing: " + streamID + " " + location
						+ " " + sentiment);
				publishEvents(dataPublisher, streamID, location, sentiment);
			} catch (AgentException e1) {
				e1.printStackTrace();
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}

		}

	}

	private static void publishEvents(DataPublisher dataPublisher,
			String streamId, String location, String sentiment)
			throws AgentException {

		Date date = new Date();
		String time = new Timestamp(date.getTime()).toString();

		Object[] meta = null;

		Object[] payload = new Object[] { sentiment, location };

		Object[] correlation = null;

		Event statisticsEvent = new Event(streamId, System.currentTimeMillis(),
				meta, correlation, payload);
		dataPublisher.publish(statisticsEvent);

	}
}
