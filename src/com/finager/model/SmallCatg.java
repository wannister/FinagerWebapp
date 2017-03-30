package com.finager.model;

//use ReadData module

import java.util.Vector;

/**
 * Project Information*
 * -------------------* 
 * Name: Finager*
 * Course Code: CS 2XB3* 
 * Lab Section: 01* 
 * The {@code SmallCatg} class provides methods to generate small category expenditure 
 * generate small category based on big category.
 */
public class SmallCatg {

	private ReadData rd;
	/**
	 * SmallCatg constructor get ReadData. 
	 * @param rd-The ReadData constructor from ReadData.java.
	 */
	public SmallCatg(ReadData rd){
		this.rd = rd;
	}
	/**
	 * Get all small category of given big category.
	 * @param v-The given position of big category.
	 * @return-All small category of given big category.
	 */
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
