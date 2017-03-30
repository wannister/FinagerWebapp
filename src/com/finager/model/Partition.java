package com.finager.model;

import java.io.*;
import java.util.Vector;

/**
 * Project Information*
 * -------------------* 
 * Name: Finager*
 * Course Code: CS 2XB3* 
 * Lab Section: 01* 
 * The {@code Partition} class provides methods to get the information of  
 * predicted average expenditure for all categories of selected provinces.
 */
public class Partition {

	String Province;
	Vector<String> Partitioninput = new Vector<String>();
	Vector<String> Partitionoutput = new Vector<String>();
	//the total number of household in each province
	double[] household = { 1390275, 1764635, 13320610, 466140, 314010, 208845, 14700, 390280, 8660, 4887510, 56460,
			3395340, 409645, 14115 };
	//all provincial names
	String[] provincelist = { "Alberta", "British Columbia", "Canada", "Manitoba", "New Brunswick",
			"Newfoundland and Labrador", "Northwest Territories", "Nova Scotia", "Nunavut", "Ontario",
			"Prince Edward Island", "Quebec", "Saskatchewan", "Yukon" };

	Partition() {

	}
        /**
         * Partition constructor to get the information of predicted average 
         * expenditure for all categories of selected provinces.
         * @param partitioninput-The output Vector of String from Entry.java .
         *                       The String has the format the selected year + predicted expenditure 
         *                       + the place + .. + category.
         * @param province-The name of the province selected by the user.
         */
	public Partition(Vector<String> partitioninput, String province) {
		this.Partitioninput = partitioninput;
		this.Province = province;
	
		int i = 0;//int i-Use to count String in partitioninput.
		double Household = 0;
		int j = 0;//int j-Use to count which province information in the array.
		while( j < 14) {		
			//get the household information of input province
			if (provincelist[j].equals(Province))
			{Household = household[j]; break; }
			j++;
		}
		while (i < 1497) {
			String[] inputitem = Partitioninput.get(i).split(",");
			//get information only from selected province 
			if (inputitem[2].equals(Province)) {
                                //calculate the average expenditure of one household for one year
				String transfer = inputitem[0] + "," + 1000000*Double.parseDouble(inputitem[1]) / Household + "," + inputitem[2] + "," + inputitem[3] + "," + inputitem[4] + "," + inputitem[5];				
				//add the expected average spend information for all categories of selected provinces to Vector <String>
				Partitionoutput.add(transfer);
			}
			i++;
		}
	}
        /**
         * Get the information of predicted average expenditure for all categories of selected provinces.
         * @return-The String of information with predicted average expenditure for all categories of selected provinces.
         */
	public Vector<String> partitionoutput() {
		return Partitionoutput;
	}
    /*
	public static void main(String[] args) throws IOException {
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
	}
    */
}
