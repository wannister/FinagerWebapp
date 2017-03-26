package com.finager.model;

//use Prefer module
//use ReadData module

package linear_regression;

import java.util.Vector;

import edu.princeton.cs.algs4.Digraph;


public class SmallCatg {

	private static String user_prov;
	private static double income;
	private static double saving;
	
	public static Vector<Double> smaller(int v){
		
		ReadData rd = new ReadData(user_prov);
		Digraph g = rd.graph();
		Vector<Double> vo = rd.value();
	
		
		Ratio r = new Ratio(income, saving);
		double k = r.k();
		
		
		Vector<Double> vn = new Vector<Double>();
		for(int i = 0; i< vo.size(); i++){
			double a = vo.get(i) * k;
			
			vn.addElement(a);
		}
		Vector<Double> smallCatg = new Vector<Double>();
		for (int w : g.adj(v)) {
			
			smallCatg.addElement(vn.get(w));
        }
		return smallCatg;
	}
	
	public static int size(int i){
		return smaller(i).size();
	}
	
	public static void main(String[] args){
		user_prov = "Canada";
		income = 1000;
		saving = 100;
		
		System.out.println(smaller(0).get(0));
		System.out.println(smaller(0).get(1));
		System.out.println(size(0));
		
	}

}

