

/* First created by JCasGen Sun Feb 15 12:19:57 IST 2015 */
package org.apache.uima.tutorial;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sun Feb 15 13:03:41 IST 2015
 * XML source: /home/achintha/workspace/UIMAAnnotatorTest/desc/sentimentAnalyserDescriptor.xml
 * @generated */
public class Sentiment extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Sentiment.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Sentiment() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Sentiment(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Sentiment(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Sentiment(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
  //*--------------*
  //* Feature: Sentiment

  /** getter for Sentiment - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSentiment() {
    if (Sentiment_Type.featOkTst && ((Sentiment_Type)jcasType).casFeat_Sentiment == null)
      jcasType.jcas.throwFeatMissing("Sentiment", "org.apache.uima.tutorial.Sentiment");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Sentiment_Type)jcasType).casFeatCode_Sentiment);}
    
  /** setter for Sentiment - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentiment(String v) {
    if (Sentiment_Type.featOkTst && ((Sentiment_Type)jcasType).casFeat_Sentiment == null)
      jcasType.jcas.throwFeatMissing("Sentiment", "org.apache.uima.tutorial.Sentiment");
    jcasType.ll_cas.ll_setStringValue(addr, ((Sentiment_Type)jcasType).casFeatCode_Sentiment, v);}    
  }

    