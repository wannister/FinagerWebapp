package com.finager.model;

//use ReadData module

import java.util.Vector;
/**
 * 
 * 
 *
 */
public class Ratio {
	
	private double user;
	private ReadData rd;
	/**
	 * 
	 * @param income
	 * @param saving
	 * @param rd
	 */
	public Ratio(double income, double saving, ReadData rd ) {
		this.user = income - saving;
		this.rd = rd;
	}
	/**
	 * 
	 * @return
	 */
	public double overall(){
		Vector<Double> v = rd.Value();
		double overall = v.get(0);
		return overall;
	}
	/**
	 * 
	 * @return
	 */
	public double k(){
		double k = overall()/this.user;
		return k;
	}

}
