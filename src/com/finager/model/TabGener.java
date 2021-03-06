package com.finager.model;

import java.io.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

//use Prefer module
//use SmallCatg module
//use Ration module
/**
 * Project Information*
 * -------------------* 
 * Name: Finager*
 * Course Code: CS 2XB3*
 * Lab Section: 01* 
 * The {@code TabGener} class provides methods apply other java file and 
 * generate output transfer to user.
 */
public class TabGener {
	private ReadData f;
	private Ratio k_income;
	private Prefer prefs;
	private SmallCatg sc;
	private Ranking r;
        /**
         * Transfer user input to all other java file and apply them.
         * @param datail-The expenditure data read from the original expenditure.csv file.
         *              This file records the annual expenditure of a household in all category from 1981 to 2015.
         * @param prov-The name of the province selected by the user.
         * @param year-The year the user chooses to expect the expenditure.
         * @param income-The user's annual income.
         * @param saving-The total amount of money user wants to save this year.
         * @param pref-The user's preference for each big category.
         */
	public TabGener(Vector<String> data, String prov, Double year, Double income, Double saving, Double[] pref) {
        // use entry constructor predicts the expenditure for a given year
		Entry a = new Entry(data, year);
		Vector<String> d2 = a.getoutput();

		// partition the data set, get the predicted average expenditure of selected provinces
		Partition pat = new Partition(d2, prov);
		Vector<String> d3 = pat.partitionoutput();

		// read data from selected provinces
		f = new ReadData(d3);

		// generate income matching ratio
		k_income = new Ratio(income, saving, f);

		// generate data by user preferences
		prefs = new Prefer(pref, f);

		// generate small category based on big category
		sc = new SmallCatg(f);
		
		r = new Ranking(d2);
	}
        /**
         * Calculate small category expenditure based on big category preference and overall K.
         * @param c-The big category position.
         * @return-The adjust expenditure from given big category to all its small category.
         */
	public Vector<Double> block(int c) {
		// create a new variable to store the result
		Vector<Double> result = new Vector<Double>();

		// get the index of big category in the value vector
		int inx = f.Index2Catg(c);

		// original value from dataset
		Vector<Double> temp = sc.smaller(inx);
		Collections.reverse(temp);
		
		// user prefences ratio calculation
		Double old_val = f.Value().get(inx);
		Double new_val = prefs.Value(c);
		Double k_pref = new_val / old_val;

		// insert the overall value at the front
		result.add(new_val * k_income.k());

		// adjust by user input
		for (int i = 0; i < temp.size(); i++) {
			Double item = temp.get(i);
			// adjust by user income, preferences
			item = item * k_pref * k_income.k();

			// set the value back
			result.add(item);
		}
		return result;
	}
        /**
         * Get the category name from one big category to all its small category.
         * @param c-The big category position.
         * @return-The category name from one big category to all its small category.
         */
	public Vector<String> Name(int c) {
		// create a new variable to store the result
		Vector<String> name = new Vector<String>();

		// get the index of big category in the value vector
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
	
	public ProVal[] Ranking(){
		return this.r.getRank();
	}
	public Double FindProv(String pc){
		return this.r.find(pc);
	}

	// test all modules together
	/**
	 * Test
	 * @param args-main method
	 * @throws IOException if stream to aFile cannot be written to or closed.
	 */
	public static void main(String[] args) throws IOException {

		Double[] input = new Double[13];
		for (int i = 0; i < input.length; i++) {
			input[i] = 1.0;
		}
		input[0] = 0.9;

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
