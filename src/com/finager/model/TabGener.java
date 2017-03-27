package com.finager.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

//use Prefer module
//use SmallCatg module
//use Ration module

public class TabGener {
	private ReadData f;
	private Ratio k_income;
	private Prefer prefs;
	private SmallCatg sc;
	
	public TabGener(String prov, int year, int income, int saving, Double[] pref) throws IOException{
		//partition the data set, omit unnecessary data
				//predict the future value
				Partition pat = new Partition(year);
				pat.Partition_IO();
				
				//create a map which used to match
				// String of category -> Integer
				Catg catg = new Catg();
				catg.match();
				
				//read data from Ontario
				f = new ReadData(prov);
	
				//generate income matching ratio
				k_income = new Ratio(income,saving,f);
				
				//generate data by user preferences
				prefs = new Prefer(pref, f);
				
				//generate small category based on big category
				sc = new SmallCatg(f);
	}
	
	public Vector<Double> block(int c){
		//create a new variable to store the result
		Vector<Double> result = new Vector<Double>();
		
		//get the index in the value vector
		int inx = f.Index2Catg(c);
		
		//original value from dataset
		result = sc.smaller(inx);
		
		//user prefences ratio calculation
		Double old_val = f.Value().get(inx);
		Double new_val = prefs.Value(c);
		Double k_pref = old_val / new_val;
		
		//adjust by user input
		for (int i = 0; i < result.size(); i++) {
			Double item = result.get(i);
			//adjust by user income, preferences
			item = item * k_pref * k_income.k();
			
			//set the value back
			result.set(i, item);
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException{
		Double[] input = new Double[13];
		for (int i = 0; i < input.length; i++) {
			input[i] = 1.0;
		}
		
		//example run
		TabGener run = new TabGener("Ontario",2016,50000,10000,input);
		//12 blocks in total
		System.out.println(run.block(12));
	}

}
