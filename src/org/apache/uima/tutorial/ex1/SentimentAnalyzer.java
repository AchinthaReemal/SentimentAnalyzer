package org.apache.uima.tutorial.ex1;

import java.util.Properties;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.tutorial.Sentiment;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class SentimentAnalyzer extends JCasAnnotator_ImplBase {

	StanfordCoreNLP pipeline;

	public void initialize(UimaContext ctx)
			throws ResourceInitializationException {
		super.initialize(ctx);

		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		pipeline = new StanfordCoreNLP(props);

	}

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {

		String line = jcas.getDocumentText();

		int mainSentiment = 0;
		if (line != null && line.length() > 0) {
			int longest = 0;
			Annotation annotation = pipeline.process(line);
			for (CoreMap sentence : annotation
					.get(CoreAnnotations.SentencesAnnotation.class)) {
				Tree tree = sentence
						.get(SentimentCoreAnnotations.AnnotatedTree.class);
				int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
				String partText = sentence.toString();
				if (partText.length() > longest) {
					mainSentiment = sentiment;
					longest = partText.length();
				}

			}
		}

		String sentiment = "";
		switch (mainSentiment) {
		case 0:
			sentiment = "Very Happy";
			break;
		case 1:
			sentiment = "Happy";
			break;
		case 2:
			sentiment = "Neutral";
			break;
		case 3:
			sentiment = "Sad";
			break;
		case 4:
			sentiment = "Very Sad";
			break;
		}

		// System.out.println("Sentiment :" + sentiment);
		Sentiment annotation = new Sentiment(jcas);
		annotation.addToIndexes(jcas);
		annotation.setSentiment(sentiment);

	}
}