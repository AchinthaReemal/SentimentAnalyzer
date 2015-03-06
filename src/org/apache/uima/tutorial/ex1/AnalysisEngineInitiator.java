package org.apache.uima.tutorial.ex1;

import java.io.File;
import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

public class AnalysisEngineInitiator {
   
    private AnalysisEngine analysisEngine = null;
   
    public AnalysisEngineInitiator() throws ResourceInitializationException {
       
        File descriptorFile = new File("desc/analysisEngine.xml");       //set the descriptor xml
        XMLInputSource descriptorSource = null;
        try {
            descriptorSource = new XMLInputSource(descriptorFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        ResourceSpecifier specifier = null;
        try {
            specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(descriptorSource);
        } catch (InvalidXMLException e) {
            e.printStackTrace();
        }
        analysisEngine = UIMAFramework.produceAnalysisEngine(specifier);
       
    }



  // this method will be used to set the analysis text 
    public JCas process(String text) throws UIMAException{   
       
        JCas jcas = analysisEngine.newJCas();
        jcas.setDocumentText(text);
        analysisEngine.process(jcas);
        return jcas;   
       
    }

}