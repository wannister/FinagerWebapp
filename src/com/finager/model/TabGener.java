package com.finager.model;

import java.io.*;

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

	public TabGener(Vector<String> data, String prov, Double year, Double income, Double saving, Double[] pref) {

		Entry a = new Entry(data, year);
		Vector<String> d2 = a.getoutput();

		// partition the data set, omit unnecessary data
		Partition pat = new Partition(d2, prov);
		Vector<String> d3 = pat.partitionoutput();

		// read data from Ontario
		f = new ReadData(d3);

		// generate income matching ratio
		k_income = new Ratio(income, saving, f);

		// generate data by user preferences
		prefs = new Prefer(pref, f);

		// generate small category based on big category
		sc = new SmallCatg(f);
	}

	public Vector<Double> block(int c) {
		// create a new variable to store the result
		Vector<Double> result = new Vector<Double>();

		// get the index in the value vector
		int inx = f.Index2Catg(c);

		// original value from dataset
		result = sc.smaller(inx);

		// user prefences ratio calculation
		Double old_val = f.Value().get(inx);
		Double new_val = prefs.Value(c);
		Double k_pref = old_val / new_val;

		// insert the overall value at the front
		result.add(0, new_val);

		// adjust by user input
		for (int i = 0; i < result.size(); i++) {
			Double item = result.get(i);
			// adjust by user income, preferences
			item = item * k_pref * k_income.k();

			// set the value back
			result.set(i, item);
		}
		return result;
	}

	public Vector<String> Name(int c) {
		// create a new variable to store the result
		Vector<String> name = new Vector<String>();

		// get the index in the value vector
		int inx = f.Index2Catg(c);

		// original value from dataset
		int result = sc.smaller(inx).size();

		// adjust by user input
		for (int i = 0; i <= result; i++) {
			String item = f.CatgNames().elementAt(inx + i);
			item = item.substring(0, item.length() - 1);
			// set the value back
			name.add(i, item);
		}
		return name;
	}

	// test all modules together
	public static void main(String[] args) throws IOException {

		Double[] input = new Double[13];
		for (int i = 0; i < input.length; i++) {
			input[i] = 1.0;
		}

		// example run

		// readData fake data
		Vector<String> trial = new Vector<String>();
		String fName = "WebContent/WEB-INF/expenditure.csv";
		File file = new File(fName);
		// opens and reads requested file
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		String line;
		while ((line = br.readLine()) != null) {
			trial.add(line);
		}
		br.close();

		TabGener run = new TabGener(trial, "Ontario", 2016.0, 50000.0, 10000.0, input);

		// 12 blocks in total
		System.out.println(run.block(0));
		System.out.print(run.Name(0));

	}

}
