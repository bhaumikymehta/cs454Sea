package Indexer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexerServlet
 */
@WebServlet("/IndexerServlet")
public class IndexerServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/Display.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String q=request.getParameter("search");
		ArrayList<Data> d=new ArrayList<Data>();
		LuceneTester t=new LuceneTester();
		t.sortUsingRelevance(q,d);
		//System.out.println("="+d.size());
		
		for(Data da:d){
			PageRank pgrank=new PageRank();
			da.setLinkScore(pgrank.pageRank());
			//da.setAvg((da.getLinkScore()+da.getTfidfScore())/2);
		}
		Collections.sort(d);
		this.getServletContext().setAttribute("Files", d);
		request.getRequestDispatcher("/WEB-INF/Display.jsp").forward(request, response);
	}

}
