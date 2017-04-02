/*take the input of vector of string from Entry and one of the category
 * output a sorted Arraylist for the expenditure of the selected category
 * in different provinces
 * */

package com.finager.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class sortbycategory {
	// the total number of household in each province
	double[] household = { 1390275, 1764635, 13320610, 466140, 314010, 208845, 14700, 390280, 8660, 4887510, 56460,
			3395340, 409645, 14115 };
	// all provincial names
	String[] provincelist = { "Alberta", "British Columbia", "Canada", "Manitoba", "New Brunswick",
			"Newfoundland and Labrador", "Northwest Territories", "Nova Scotia", "Nunavut", "Ontario",
			"Prince Edward Island", "Quebec", "Saskatchewan", "Yukon" };

	ArrayList<String> catglist = new ArrayList<String>();

	sortbycategory(Vector<String> input, String category) {
		int i = 0;
		while (i < input.size()) {
			String[] catginput = input.get(i).split(",");
			if (category.equals(catginput[5])) {
					for(int j=0;j<14;j++){
						if (provincelist[j].equals(catginput[2])){
							double cost = 1000000*Double.parseDouble(catginput[1])/household[j];
							this.catglist.add(catginput[2]+","+cost+","+category);
						}
						
					}
				
				
				

			}
		}
	}
	public void sort(){
		int N = catglist.size();
		for(int i = 0;i<N;i++){
			int min = i;
			for(int j = i+1;j<N;j++){
				if(less(catglist.get(j),catglist.get(min))) min =j;}
			exch(i,min);	
			}
			
		}
		
	public boolean less(String A, String B){
		double Avalue = Double.parseDouble(A.split(",")[1]);
		double Bvalue = Double.parseDouble(A.split(",")[1]);
		if(Avalue<=Bvalue) return true;
		else return false;
	}	
	public void exch(int i , int  min){
		String temp = null;
		temp = catglist.get(min);
		catglist.set(min, catglist.get(i));
		catglist.set(i,temp);
		
	}
	public ArrayList<String> output(){
		sort();
		return catglist;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("WebContent/WEB-INF/expenditure.csv"));

		Vector<String> lines = new Vector<String>();

		String word;
		while ((word = reader.readLine()) != null) {
			lines.add(word);
		}
		Entry test = new Entry(lines, 2016);
		
		sortbycategory testsort = new sortbycategory(test.output,"Alberta");
		for(int i = 0;i<testsort.output().size();i++){
			
			System.out.println(testsort.catglist.get(i));
		}
		reader.close();
		
	}
	

}
