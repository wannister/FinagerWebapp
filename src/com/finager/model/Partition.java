package com.finager.model;

import java.io.*;
import java.util.Vector;

/**
 * Constructor:Vector<String> containing only data Partition
 * constructor(line2,prov); after predicting future value, String prov
 * 
 * Excepted output: Vector<String> containing only data from user province
 * 
 * @author mina
 *
 */
public class Partition {

	String Province;
	Vector<String> Partitioninput = new Vector<String>();
	Vector<String> Partitionoutput = new Vector<String>();
	double[] household = { 1390275, 1764635, 13320610, 466140, 314010, 208845, 14700, 390280, 8660, 4887510, 56460,
			3395340, 409645, 14115 };
	String[] provincelist = { "Alberta", "British Columbia", "Canada", "Manitoba", "New Brunswick",
			"Newfoundland and Labrador", "Northwest Territories", "Nova Scotia", "Nunavut", "Ontario",
			"Prince Edward Island", "Quebec", "Saskatchewan", "Yukon" };

	Partition() {

	}

	public Partition(Vector<String> partitioninput, String province) {
		this.Partitioninput = partitioninput;
		this.Province = province;
	
		int i = 0;
		double Household = 0;
		int j = 0;
		while( j < 14) {		//get the household of input province
			if (provincelist[j].equals(Province))
			{Household = household[j]; break; }
			j++;
		}
		while (i < 1497) {
			String[] inputitem = Partitioninput.get(i).split(",");
			if (inputitem[2].equals(Province)) {

				String transfer = inputitem[0] + "," + 1000000*Double.parseDouble(inputitem[1]) / Household + "," + inputitem[2] + "," + inputitem[3] + "," + inputitem[4] + "," + inputitem[5];				Partitionoutput.add(transfer);
			}
			i++;
		}
	}

	public Vector<String> partitionoutput() {
		return Partitionoutput;
	}

	/*public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("WebContent/WEB-INF/expenditure.csv"));
		Vector<String> lines = new Vector<String>();

		String word;
		while ((word = reader.readLine()) != null) {
			lines.add(word);
		}
		Entry test = new Entry(lines, 2016);
		Partition Test1 = new Partition(test.output, "Ontario");
		for (int i = 0; i < Test1.Partitionoutput.size(); i++) {
			System.out.println(Test1.partitionoutput().get(i));

		}
		reader.close();
	}*/

}
