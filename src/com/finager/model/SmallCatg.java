package com.finager.model;

//use Prefer module
//use ReadData module

import java.util.Vector;

import edu.princeton.cs.algs4.Digraph;


public class SmallCatg {

	private static String user_prov;
	private static double income;
	private static double saving;
	private static double[] pre;

	
	public static Vector<Double> smaller(int v){
		
		ReadData rd = new ReadData(user_prov);
		Digraph g = rd.graph();
		Vector<Double> vo = rd.value();
	
		
		Prefer p = new Prefer(pre, rd);
		double k = -1;
		int c = 0;
        for (int w : g.adj(0)) {
        	
			if(w == v){
				
			k = vo.get(w)/ p.value(c);
			
			System.out.println(k);
			
			}
			else
				c++;
        }
		
       
		Vector<Double> smallCatg = new Vector<Double>();
		for (int w : g.adj(v)) {
			
			smallCatg.addElement(vo.get(w) * k);
			
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
		
		System.out.println(smaller(1).get(0));
		System.out.println(smaller(1).get(1));
		System.out.println(size(0));
		
	}

}
