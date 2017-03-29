package com.finager.model;

//use Prefer module
//use ReadData module

import java.util.Vector;


public class SmallCatg {

	private ReadData rd;
	
	public SmallCatg(ReadData rd){
		this.rd = rd;
	}
	
	public Vector<Double> smaller(int v){
		//create a variable to store the result
		Vector<Double> result = new Vector<Double>();
		
		//get the graph of the data
		Digraph g = rd.Graph();
		//get the value of the data
		Vector<Double> vo = rd.Value();
		
		//for all adjacent nodes of big category
		Iterable<Integer> small = g.adj(v);
		for (Integer item:small){
			//store the value of small category
			result.add(vo.get(item));
		}
		return result;
	}
	

}
