package com.finager.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import com.finager.model.*;; // notice this

public class UserInput extends HttpServlet{
	
	//test all the implementation together 
	/*
	public static void main(String[] args) throws IOException{
	
	
		//partition the data set, omit unnecessary data
		//predict the future value
		Partition pat = new Partition(2017);
		pat.Partition_IO();
		
		//create a map which used to match
		// String of category -> Integer
		Catg catg = new Catg();
		catg.match();
	}*/
	
	//jsp page sample run
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
						throws IOException, ServletException{
		//combine all the preferences to an array of double
		String y = request.getParameter("year");
		
		List result = new ArrayList<String>();
		result.add(y);
		
		request.setAttribute("block", result);
		RequestDispatcher view = request.getRequestDispatcher("table.jsp");
		view.forward(request, response);
	}

}
