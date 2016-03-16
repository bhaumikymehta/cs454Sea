package com.calstatela.indexer;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;

public class LuceneTester {

	String indexDir = "C:\\Jugal\\Courses\\CS454_SearchEngine\\WebCrawlerStorage\\index";
	String dataDir = "C:\\Jugal\\Courses\\CS454_SearchEngine\\en";
	Indexer indexer;
	Searcher searcher;

	public static void main(String[] args) {
		LuceneTester tester;
		try {
			tester = new LuceneTester();
			tester.createIndex();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	private void createIndex() throws IOException{
		indexer = new Indexer(indexDir);
		int numIndexed;
		long startTime = System.currentTimeMillis();	
		numIndexed = indexer.createIndex(dataDir);
		long endTime = System.currentTimeMillis();
		indexer.close();
		System.out.println(numIndexed+" File indexed, time taken: "
				+(endTime-startTime)+" ms");		
	}

}