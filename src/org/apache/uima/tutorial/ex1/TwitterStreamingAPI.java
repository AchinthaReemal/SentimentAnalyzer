package org.apache.uima.tutorial.ex1;

import java.net.MalformedURLException;

import org.wso2.carbon.databridge.agent.thrift.exception.AgentException;
import org.wso2.carbon.databridge.commons.exception.AuthenticationException;
import org.wso2.carbon.databridge.commons.exception.DifferentStreamDefinitionAlreadyDefinedException;
import org.wso2.carbon.databridge.commons.exception.MalformedStreamDefinitionException;
import org.wso2.carbon.databridge.commons.exception.StreamDefinitionException;
import org.wso2.carbon.databridge.commons.exception.TransportException;

import twitter4j.Place;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreamingAPI {

	public static void main(String[] args) throws AgentException,
			StreamDefinitionException, MalformedStreamDefinitionException,
			DifferentStreamDefinitionAlreadyDefinedException,
			MalformedURLException, AuthenticationException, TransportException {

		final TestWithTwitterStreamingAPI testWithTwitterStreamingAPI 
									= new TestWithTwitterStreamingAPI();
		final String streamID;

		System.out.println("Streaming Started");

		streamID = testWithTwitterStreamingAPI.defineInputStream();

		StatusListener listener = new StatusListener() {
			public void onStatus(Status status) {

				final Place place = status.getPlace();

				if (place != null) {
					System.out.println("Stream ID : " + streamID);

					testWithTwitterStreamingAPI.receiveTwitterFeed(
							status.getText(), place.getName(), streamID);

					System.out.println(status.getUser().getName() + " : "
							+ place.getName() + " Tweeted: " + status.getText()
							+ "  Tweeted AT: " + status.getCreatedAt());
				}

			}

			public void onDeletionNotice(
					StatusDeletionNotice statusDeletionNotice) {
			}

			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			}

			public void onException(Exception ex) {
				ex.printStackTrace();
			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub

			}
		};

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey("AmFn6IAaLuECzgNYYDCQkTrrc")
				.setOAuthConsumerSecret(
						"u2Zhv2pdhck093E0yLN8UUbCvUq5CmsItMbUVdo0QO7qXWUwSf")
				.setOAuthAccessToken(
						"232027476-xudOB6AamrcKbkhDM7IgKMkYsBkTAmwUbo86lsh2")
				.setOAuthAccessTokenSecret(
						"ttvt2JfZFsHfAK63UKBVCBdySITqMVhuexDkmUgOeK7yB");

		TwitterStreamFactory tf = new TwitterStreamFactory(cb.build());
		TwitterStream twitterStream = tf.getInstance();
		twitterStream.addListener(listener);
		twitterStream.sample();

	}

}
