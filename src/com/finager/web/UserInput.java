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
		String y = request.getParameter("year");
		
		List result = new ArrayList<String>();
		result.add(y);
		
		request.setAttribute("block", result);
		RequestDispatcher view = request.getRequestDispatcher("table.jsp");
		view.forward(request, response);
	}

}
