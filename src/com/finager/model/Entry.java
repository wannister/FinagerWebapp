package com.finager.model;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;
/**
 * Project Information*
 * -------------------* 
 * Name: Finager*
 * Course Code: CS 2XB3* 
 * Lab Section: 01* 
 * The {@code Entry} class provides methods to read from the expenditure data.
 */
public class Entry {

	double[] realyear, realexpenditure;
	double[] year = new double[50];// Used to store the year information from the data file.
	double[] expenditure = new double[50];// Used to store all useful expenditure from data file
	double selectyear;
	String[] information = new String[4];
	Vector<String> data = new Vector<String>();
	Vector<String> output = new Vector<String>();//Used to store predicted expenditure of all category
       /**
        * Entry constructor use to read from the expenditure data.
        * 1.skip useless information
        * 2.store useful information to arrays when the year is increasing
        * 3.otherwise use LinearRegression algorithm predicts the expenditure for a given year
        * @param A－The expenditure data read from the original expenditure.csv file.
        * @param Selectyear－The year the user chooses to expect the expenditure.
        */
	public Entry(Vector<String> A, double Selectyear) {// Vector<String>;//sort
														// and search//

		this.selectyear = Selectyear;
		this.data = A;
                //int i - Use to count the number of String in Vector<String> data
		int i = 1;
		//int j - Use to count how many year in one category
		int j = 0;
		//double checker - Used to check whether the year is increasing
		double checker = 0;
		while (i < 129778) {
			// seperate a String line by ,
			String item[] = data.get(i).split(",");
			//skip useless information
			if (item[2].contains("2007") || item[item.length - 2].contains("15.")
					|| item[item.length - 2].contains("16.") || item[item.length - 4].contains("Expenditure by")
					|| item[item.length - 4].contains("Net expen"))
			{
				i++;
				continue;
			}
                        // add the useful elements to an array if the year is increasing
			if (Double.parseDouble(item[0]) > checker) { 
				
				try {
					expenditure[j] = Double.parseDouble(item[item.length - 1]);
				} catch (Exception e) {
					i++;
					continue;

				}
                                //save the year information
				year[j] = Double.parseDouble(item[0]);
				checker = year[j];
				//save the place information-which province
				information[0] = item[1];
				//other information from data
				information[1] = item[item.length - 2];
				information[2] = item[item.length - 3];
				//save the category information
				information[3] = item[item.length - 4];
				i++;
				j++;
			}
                        // use LinearRegression algorithm predicts the expenditure for a given year when the year is not increasing 
			else {
				checker = Double.parseDouble(item[0]);
				realyear = Arrays.copyOfRange(year, 0, i);
				realexpenditure = Arrays.copyOfRange(expenditure, 0, i);
				LinearRegression predictdata = new LinearRegression(realyear, realexpenditure);
				//output add predicted expenditure of all category
				output.add(selectyear + "," + predictdata.predict(selectyear) + "," + information[0] + ","
						+ information[1] + "," + information[2] + "," + information[3]);

				j = 0;
				year = new double[50];
				expenditure = new double[50];
				i++;
			}

		}

	}
       /**
        * Get the information of predicted expenditure of all category from output Vector of string.
        * @return-The predicted expenditure of all category.
        */
	public Vector<String> getoutput() { 

		return output;

	}
       /**
        * Test 
        * @param args
        * @throws IOException
        */
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("WebContent/WEB-INF/expenditure.csv"));

		Vector<String> lines = new Vector<String>();

		String word;
		while ((word = reader.readLine()) != null) {
			lines.add(word);
		}
		Entry test = new Entry(lines, 2016);
		for (int i = 0; i < 1497; i++) {
			System.out.println(test.output.get(i));

		}
		System.out.println(test.output.size());
		reader.close();
	}

}
