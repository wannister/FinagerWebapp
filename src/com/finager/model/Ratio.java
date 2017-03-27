package com.finager.model;

//use ReadData module

import java.util.Vector;

public class Ratio {
	
	private double income;
	private double saving;
	private double user;
	private String user_prov;
	
	public Ratio(double income, double saving ) {
		this.income = income;
		this.saving = saving;
		this.user = income - saving;
	}
	
	public double getIncome(){
		return this.income;
	}

	public double getSaving(){
		return this.saving;
	}

	public double getUser(){
		return this.user;
	}
	
	public double overall(){
		
		ReadData rd = new ReadData(user_prov);
		Vector<Double> v = rd.Value();
		double overall = v.get(0);
		return overall;
	}
	
	public double k(){
		double k = overall()/this.user;
		return k;
	}
	
	public static void main(String[] args){
		
		Ratio r = new Ratio(1000, 100);
		System.out.println(r.getUser());
		System.out.println(r.overall());
		System.out.println(r.k());
	}

}
