package com.finager.web;

import java.io.IOException;

import javax.servlet.*;
import com.finager.model.*;

public class UserInput {
	//test all the implementation together
	public static void main(String[] args) throws IOException{
		
		//partition the data set, omit unnecessary data
		//predict the future value
		Partition pat = new Partition(2017);
		pat.Partition_IO();
		
		//create a map which used to match
		// String of category -> Integer
		Catg catg = new Catg();
		catg.match();
	}
	
	//jsp page sample run

}
