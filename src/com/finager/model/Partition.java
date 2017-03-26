package com.finager.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Partition {
	double year;
	double[] household = { 13320610, 208845, 56460, 390280, 314010, 3395340, 4887510, 466140, 409645, 1390275, 1764635,
			14115, 14700, 8660 };
	double[] growthrate = { 176628, 2333, 666, 2687, 3610, 41199, 66497, 3472, 4501, 26815, 24297, 300, 93, 161 };
	double[] selectyearhousehold = new double[14];
	String[] province = { "Alberta", "British Columbia", "Canada", "Manitoba", "New Brunswick",
			"Newfoundland and Labrador", "Northwest Territories", "Nova Scotia", "Nunavut", "Ontario",
			"Prince Edward Island", "Quebec", "Saskatchewan", "Yukon" };

	Partition() {

	}

	public Partition(double year) throws IOException {
		this.year = year;
		Entry A = new Entry(year);
		A.EntryIO();
		int i = 0;
		while(i<14){
		PrintWriter clear = new PrintWriter("data/"+province[i]+".csv");
		clear.close();
		i++;}
	}

	public void household_revised(double year) {
		for (int i = 0; i < 14; i++)
			selectyearhousehold[i] = household[i] + (year - 2011) * growthrate[i];
		// householdarray is 2011 household data;
	} // realhousehold = h.2011+growth*(year-2011);

	public void Partition_IO() throws IOException {
		double year = this.year;
		household_revised(year);
		BufferedReader reader = new BufferedReader(new FileReader("data/predict.csv"));
		String line = null;
		int i = 0;
		while ((line = reader.readLine()) != null) {
			String item[] = line.split(",");
			double yearnumber = Double.parseDouble(item[2].substring(0, 1));
			BufferedWriter bw = new BufferedWriter(new FileWriter("data/" + item[1] + ".csv", true)); //
			bw.write(item[0] + "," + item[1] + "," + item[2] + "," + item[3] + ","
					+ 1000000 * Double.parseDouble(item[4]) / (selectyearhousehold[(int) yearnumber - 1]) + ","
					+ item[5].substring(0, item[5].length()-1));
			bw.newLine();
			bw.close();
		}
		reader.close();
	}

	public static void main(String[] args) throws IOException {
		//Partition test1 = new Partition(2018);// TODO Auto-generated method stub
		//test1.Partition_IO();
	}

}
