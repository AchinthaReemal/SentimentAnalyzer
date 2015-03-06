
/* First created by JCasGen Sun Feb 15 12:19:57 IST 2015 */
package org.apache.uima.tutorial;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Sun Feb 15 13:03:41 IST 2015
 * @generated */
public class Sentiment_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Sentiment_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Sentiment_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Sentiment(addr, Sentiment_Type.this);
  			   Sentiment_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Sentiment(addr, Sentiment_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Sentiment.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.tutorial.Sentiment");



  /** @generated */
  final Feature casFeat_Sentiment;
  /** @generated */
  final int     casFeatCode_Sentiment;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSentiment(int addr) {
        if (featOkTst && casFeat_Sentiment == null)
      jcas.throwFeatMissing("Sentiment", "org.apache.uima.tutorial.Sentiment");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Sentiment);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentiment(int addr, String v) {
        if (featOkTst && casFeat_Sentiment == null)
      jcas.throwFeatMissing("Sentiment", "org.apache.uima.tutorial.Sentiment");
    ll_cas.ll_setStringValue(addr, casFeatCode_Sentiment, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Sentiment_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Sentiment = jcas.getRequiredFeatureDE(casType, "Sentiment", "uima.cas.String", featOkTst);
    casFeatCode_Sentiment  = (null == casFeat_Sentiment) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Sentiment).getCode();

  }
}



    