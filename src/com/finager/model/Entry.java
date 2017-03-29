package com.finager.model;

import java.io.*;

import java.util.Arrays;
import java.util.Vector;

public class Entry {

	double[] realyear, realexpenditure;
	double[] year = new double[50];
	double[] expenditure = new double[50];
	static double selectyear;
	String[] information = new String[4];
	Vector<String> data = new Vector<String>();
	static Vector<String> output = new Vector<String>() ;

	public Entry(Vector<String> A, double Selectyear) {// Vector<String>;//sort
																// and search//

		Entry.selectyear = Selectyear;
		this.data = A;
	}

	public void readvector() {
		int i = 1;
		int j = 0;
		double checker = 0;
		while (i < 129778) {
			String item[] = data.get(i).split(",");// seperate a line by ,
			if (item[2].contains("2007") || item[item.length - 2].contains("15.")
					|| item[item.length - 2].contains("16.") || item[item.length - 4].contains("Expenditure by")
					|| item[item.length - 4].contains("Net expen"))// skip
																	// useless
																	// info
			{
				i++;
				continue;
			}

			if (Double.parseDouble(item[0]) > checker) { // add the elements to
															// an array if the
															// year is
															// increasing
				try {
					expenditure[j] = Double.parseDouble(item[item.length - 1]);
				} catch (Exception e) {
					i++;
					continue;

				}

				year[j] = Double.parseDouble(item[0]);
				checker = year[j];
				information[0] = item[1];
				information[1] = item[item.length - 2];
				information[2] = item[item.length - 3];
				information[3] = item[item.length - 4];
				i++;
				j++;
			}

			else {
				checker = Double.parseDouble(item[0]);
				realyear = Arrays.copyOfRange(year, 0, i);
				realexpenditure = Arrays.copyOfRange(expenditure, 0, i);
				LinearRegression predictdata = new LinearRegression(realyear, realexpenditure);
				output.add(selectyear + "," + predictdata.predict(selectyear) + "," + information[0] + ","
						+ information[1] + "," + information[2] + "," + information[3]);

				j = 0;
				year = new double[50];
				expenditure = new double[50];
				i++;
			}

		}
		

	}
	
	public Vector<String> getoutput(){  //get the output Vector of string
		
		return output;
		
	}

	/*public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data/expenditure.csv"));

		Vector<String> lines = new Vector<String>();

		String word;
		while ((word = reader.readLine()) != null) {
			lines.add(word);
		}
		VectorEntry test = new VectorEntry(lines, 2016);
		test.readvector();
		for (int i = 0; i < 1497; i++) {
			System.out.println(VectorEntry.output.get(i));
			
		}
		reader.close();
	}*/

}
