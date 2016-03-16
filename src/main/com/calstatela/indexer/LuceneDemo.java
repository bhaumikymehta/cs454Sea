/*package com.calstatela.indexer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.TermVector;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hit;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;

public class LuceneDemo {
	public static final String FILES_TO_INDEX_DIRECTORY = "C:\\Jugal\\Courses\\CS454_SearchEngine\\WebCrawlerStorage\\html";
	public static final String INDEX_DIRECTORY = "C:\\Jugal\\Courses\\CS454_SearchEngine\\WebCrawlerStorage\\index";

	public static final String FIELD_PATH = "path";
	public static final String FIELD_CONTENTS = "contents";

	public static void main(String[] args) throws Exception {

		createIndex();
		searchIndex("google");
		IndexReader r = IndexReader.open( INDEX_DIRECTORY);
		
		int num = r.numDocs();
		for ( int i = 0; i < num; i++)
		{
		    if ( ! r.isDeleted( i))
		    {
		        Document d = r.document( i);
		        System.out.println( "d : docid : "+d.get("id") +i+ "=" +d);
		        System.out.println("======="+r.getTermFreqVector(i, "contents"));
		        
		    }
		}
		r.close();
		
	}
	
	public double getTF(IndexReader reader, String term, String field, int docID) throws IOException
	{
	    TermFreqVector termVector = reader.getTermFreqVector(docID, field);
	    int idx = termVector.indexOf(term);
	    if (idx == -1)
	    {
	        return 0;
	    }
	    else
	    {
	        int[] freqs = termVector.getTermFrequencies();
	        return freqs[idx];
	    }
	}
	
	public double getIDF(IndexReader reader, String field, String termName) throws IOException
	{
	    return Math.log(reader.numDocs()/ ((double)reader.docFreq(new Term(field, termName))));
	}

	public static void createIndex() throws CorruptIndexException, LockObtainFailedException, IOException {
		Analyzer analyzer = new StandardAnalyzer();
		boolean recreateIndexIfExists = true;
		IndexWriter indexWriter = new IndexWriter(INDEX_DIRECTORY, analyzer, recreateIndexIfExists);
		File dir = new File(FILES_TO_INDEX_DIRECTORY);
		File[] files = dir.listFiles();
		for (File file : files) {
			Document document = new Document();
			FieldType type = new FieldType();
			type.setIndexed(true);
			type.setStored(true);
			type.setStoreTermVectors(true);
			String path = file.getCanonicalPath();
			document.add(new Field(FIELD_PATH, path, Field.Store.YES, Index.TOKENIZED,TermVector.YES));

			Reader reader = new FileReader(file);
			document.add(new Field(FIELD_CONTENTS, reader));

			indexWriter.addDocument(document);
		}
		indexWriter.optimize();
		indexWriter.close();
	}
	public static void searchIndex(String searchString) throws IOException, ParseException {
		System.out.println("Searching for '" + searchString + "'");
		Directory directory = FSDirectory.getDirectory(INDEX_DIRECTORY);
		IndexReader indexReader = IndexReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);

		Analyzer analyzer = new StandardAnalyzer();
		QueryParser queryParser = new QueryParser(FIELD_CONTENTS, analyzer);
		Query query = queryParser.parse(searchString);
		Hits hits = indexSearcher.search(query);
		System.out.println("Number of hits: " + hits.length());

		Iterator<Hit> it = hits.iterator();
		while (it.hasNext()) {
			Hit hit = it.next();
			Document document = hit.getDocument();
			String path = document.get(FIELD_PATH);
			System.out.println("Hit: " + path);
		}

	}
}
*/