package com.finager.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import com.finager.model.*;; // notice this

public class UserInput extends HttpServlet{
	
	//jsp page sample run
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
						throws IOException, ServletException{
		//combine all the preferences to an array of double
		String p0 = request.getParameter("pref0");
		String p1 = request.getParameter("pref1");
		String p2 = request.getParameter("pref2");
		String p3 = request.getParameter("pref3");
		String p4 = request.getParameter("pref4");
		String p5 = request.getParameter("pref5");
		String p6 = request.getParameter("pref6");
		String p7 = request.getParameter("pref7");
		String p8 = request.getParameter("pref8");
		String p9 = request.getParameter("pref9");
		String p10 = request.getParameter("pref10");
		String p11 = request.getParameter("pref11");
		String p12 = request.getParameter("pref12");
		
		List<Double> result = new ArrayList<Double>();
		result.add(Double.parseDouble(p0));
		result.add(Double.parseDouble(p1));
		result.add(Double.parseDouble(p2));
		result.add(Double.parseDouble(p3));
		result.add(Double.parseDouble(p4));
		result.add(Double.parseDouble(p5));
		result.add(Double.parseDouble(p6));
		result.add(Double.parseDouble(p7));
		result.add(Double.parseDouble(p8));
		result.add(Double.parseDouble(p9));
		result.add(Double.parseDouble(p10));
		result.add(Double.parseDouble(p11));
		result.add(Double.parseDouble(p12));
		
		//convert from vector to Double[]
		Double[] input = new Double[13];
		for (int i = 0; i < input.length; i++) {
			input[i] = result.get(i);
		}
		
		//TabGener run = new TabGener("Ontario",2016,50000,10000,input);
		
		//Read the data from expenditure.csv
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		ServletContext cntxt = getServletContext();
		String fName = "/WEB-INF/expenditure.csv";
		InputStream ins = cntxt.getResourceAsStream(fName);
		//create a variable to store the data
		Vector<String> lines = new Vector<String>();
		//start reading file
		BufferedReader br = new BufferedReader((new InputStreamReader(ins)));
		String word;
		while((word= br.readLine())!= null){
			lines.add(word);
		}
		//check if the size matches the original file
		System.out.println(lines.size());

		
		request.setAttribute("block", result);
		RequestDispatcher view = request.getRequestDispatcher("table.jsp");
		view.forward(request, response);
	}

}
