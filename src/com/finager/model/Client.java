package com.finager.model;

import java.io.IOException;

public class Client {
	//test all the implementation together 
	
	public static void main(String[] args) throws IOException{
	
		/*
		//partition the data set, omit unnecessary data
		//predict the future value
		Partition pat = new Partition(2017);
		pat.Partition_IO();*/
		
		//create a map which used to match
		// String of category -> Integer
		Catg catg = new Catg();
		catg.match();
		
		//read data from Ontario
		ReadData f = new ReadData("Ontario");
		System.out.println("Start");
		
		System.out.println(catg.int2catg(7));
		
		System.out.println(f.Value());
		System.out.println(f.indexes());
		
		System.out.println(Math.floor(0.002048888421427364) == 0.0);
	}
}
