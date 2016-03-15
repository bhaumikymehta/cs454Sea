/*package com.calstatela.indexer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.index.DocsEnum;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.search.DocIdSetIterator;
import org.apache.lucene.search.similarities.DefaultSimilarity;
import org.apache.lucene.search.similarities.TFIDFSimilarity;
import org.apache.lucene.util.Bits;
import org.apache.lucene.util.BytesRef;


public class Tf_Idf 
{
	Map<String, Float> docFrequencies = new HashMap<>();	
	static float tf = 1;
	static float idf = 0;
	private float tfidf_score;

	static float[] tfidf = null;

	Map<String, Float> getIdfs(IndexReader reader, String field) throws IOException
	{
		*//** GET FIELDS **//*
		Fields fields = MultiFields.getFields(reader); //Get the Fields of the index 

		TFIDFSimilarity tfidfSIM = new DefaultSimilarity();

		for (String field1: fields) 
		{
			TermsEnum termEnum = MultiFields.getTerms(reader, field1).iterator(null);
			BytesRef bytesRef;
			while ((bytesRef = termEnum.next()) != null) 
			{
				if (termEnum.seekExact(bytesRef, true)) 
				{
					String term = bytesRef.utf8ToString(); 
					float idf = tfidfSIM.idf( termEnum.docFreq(), reader.numDocs() );
					docFrequencies.put(term, idf);      
				}
			}
		}

		return docFrequencies;
	}
	Map<String, Float> getIdfs1(IndexReader reader, String field) throws IOException
	{
	for (int docID=0; docID< reader.maxDoc(); docID++)
	{      
	        TermsEnum termsEnum = MultiFields.getTerms(reader, field).iterator(null);
	        DocsEnum docsEnum = null;
	  
	        Terms vector = reader.getTermVector(docID, CONTENT);
	        
	        try
	        {
	   termsEnum = vector.iterator(termsEnum);
	  } 
	        catch (NullPointerException e) 
	  {
	   e.printStackTrace();
	  }
	        BytesRef bytesRef = null;
	        while ((bytesRef = termsEnum.next()) != null) 
	        {
	         if (termsEnum.seekExact(bytesRef, true)) 
	         {
	          String term = bytesRef.utf8ToString(); 
	          float tf = 0; 
	           
	                docsEnum = termsEnum.docs(null, null, DocsEnum.FLAG_FREQS);
	                while (docsEnum.next() != DocIdSetIterator.NO_MORE_DOCS) 
	                {
	                	float count=vector.getSumTotalTermFreq();
	                }
	                 tf =  tfidfSIM.tf(docsEnum.freq()); 
	                    termFrequencies.put(term, tf); 
	                }  
	                 
	                float idf = docFrequencies.get(term);
	                float w = tf * idf;
	                tf_Idf_Weights.put(term, w); 
	         } 
	        } 
	  
	        return tf_Idf_Weights;
	}
	
	
	public void scoreCalculator(IndexReader reader,String field,String term) throws IOException 
	{ 
		*//** GET TERM FREQUENCY & IDF **//*
		TFIDFSimilarity tfidfSIM = new DefaultSimilarity();
		Bits liveDocs = MultiFields.getLiveDocs(reader);
		TermsEnum termEnum = MultiFields.getTerms(reader, field).iterator(null);
		BytesRef bytesRef;
		while ((bytesRef = termEnum.next()) != null) 
		{           
			if(bytesRef.utf8ToString().trim() == term.trim())
			{                  
				if (termEnum.seekExact(bytesRef, true)) 
				{
					idf = tfidfSIM.idf(termEnum.docFreq(), reader.numDocs());
					DocsEnum docsEnum = termEnum.docs(liveDocs, null);
					if (docsEnum != null) 
					{

						boolean doc; 
						while((doc = docsEnum.next())!=false) 
						{
							tf = tfidfSIM.tf(docsEnum.freq());
							tfidf_score = tf*idf; 
							System.out.println(" -tfidf_score- " + tfidf_score);
						}
					} 
				} 
			}
		} 
	}

}
*/