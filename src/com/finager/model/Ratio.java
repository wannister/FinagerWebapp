package com.finager.model;

//use ReadData module

import java.util.Vector;
/**
 * Project Information*
 * -------------------* 
 * Name: Finager*
 * Course Code: CS 2XB3* 
 * Lab Section: 01* 
 * The {@code Ratio} class provides method to calculate the ratio K.
 */
public class Ratio {
	
	private double user;
	private ReadData rd;
	/**
	 * Ratio constructor calculate the user's expenditure for one year.
	 * @param income-The user's annual income.
	 * @param saving－The total amount of money user wants to save this year.
	 * @param rd-The ReadData constructor from ReadData.java.
	 */
	public Ratio(double income, double saving, ReadData rd ) {
		this.user = income - saving;
		this.rd = rd;
	}
	/**
	 * Get the overall predicted average expenditure value from file.
	 * @return-The overall predicted average expenditure value from file.
	 */
	public double overall(){
		Vector<Double> v = rd.Value();
		double overall = v.get(0);
		return overall;
	}
	/**
	 * Calculate the ratio K between user's annual income and overall predicted average expenditure.
	 * @return-The ratio K.
	 */
	public double k(){
		double k = this.user/overall();
		return k;
	}

}
