package com.finager.model;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;


/**
 * Project Information*
 * -------------------* 
 * Name: Finager*
 * Course Code: CS 2XB3* 
 * Lab Section: 01* 
 * The {@code ReadData} class provides methods to separate data from 
 * the output of Partition.java and built a digraph.
 */
public class ReadData {
	private Digraph data;
	private Vector<Double> value;
	private Vector<Integer> index_list;
	private Vector<String> catg_name; 
	private Vector<String> prov_info;
	/**
	 * ReadData use to read from file.
	 * 1.add all predicted average expenditure to a Vector<Double>.
	 * 2.separate big category and small category.
	 * 3.built a digraph with edges from big category to small category.
	 * @param file-The Vector<String> of information with predicted average expenditure for 
	 *             all categories of selected provinces.
	 */
	public ReadData(Vector<String> file){
		
		this.data = new Digraph(1000);// create a digraph with 1000 vertices 
		
		this.value = new Vector<Double>();
		this.index_list = new Vector<Integer>();
		//the catg_name store all categories' name
		this.catg_name = new Vector<String>();
		//the prov_info store predicted average expenditure for all categories of selected provinces.
		this.prov_info = file;
		Read();
	}
	//getter
	/**
	 * Get a digraph with edges from big category to small category.
	 * @return-A digraph with edges from big category to small category.
	 */
	public Digraph Graph(){
		return this.data;
	}
	//getter
	/**
	 * Get all predicted expenditure value for all categories of selected provinces.
	 * @return-All predicted expenditure value for all categories of selected provinces.
	 */
	public Vector<Double> Value(){
		return this.value;
	}
	//getter
	/**
	 * Get the position of given big category index.
	 * @param catg-The big category index.
	 * @return-The position of given big category index.
	 */
	public int Catg2Index(int catg){
		return this.index_list.indexOf(catg);
	}
	//getter
	/**
	 * Get the big category index of given position.
	 * @param index-The given position.
	 * @return-The big category index of given position.
	 */
	public int Index2Catg(int index){
		return this.index_list.elementAt(index);
	}
	
	//getter
	/**
	 * Get the big category index list.
	 * @return-The Vector<Integer> of all big category index.
	 */
	public Vector<Integer> indexes(){
		return index_list;
	}
	//getter
	/**
	 * Get all categories of selected provinces.
	 * @return-The Vector<String> of all categories of selected provinces.
	 */
	public Vector<String> CatgNames(){
		return catg_name;	 
	}

	/**
	 * Separate useful information from file.
	 * Built a digraph with edges from big category to small category.
	 */
	private void Read(){
		//line containing provincial total
		String[] total = this.prov_info.elementAt(0).split(",");
		
		//retrieve and parse provincial total
		Double final_val = Double.parseDouble(total[1]);
		
		//stores provincial total in a Vector<Double>
		this.value.add(0,final_val);
		this.catg_name.add(" ");
		//big category index
		int BC_ind = 0; 
		
		for (int i = 1; i<prov_info.size();i++) {
			//stores first big category string
			String line = prov_info.elementAt(i);
			
			//increment BC and i together
			BC_ind++;
			
			//add the big category index to the list
			this.index_list.add(i);				
			
			//split the line
			String[] input = line.split(",");
			//get the value of the sum
			Double sum = Double.parseDouble(input[1]);
			//store expenditure value into the list
			this.value.add(i,sum);					
			//store category names into string vector
			this.catg_name.add(input[5]);
			//value of sub-categories
			String line2; 
			
			while(i<107 & Math.floor(Math.abs(sum))!=0.0){
				i++; //manually increase counter

				line2 = this.prov_info.elementAt(i); //temp stores string containing sub-catg values			
			
				//split the line
				input = line2.split(",");
				//get the value of line
				double current_val = Double.parseDouble(input[1]);
				//store into the value vector
				this.value.add(i,current_val);
				//store name into string vector
				this.catg_name.add(input[5]);
				
				//if the line is not big category
				if (sum != current_val){
					//add edge from big category to small category
					this.data.addEdge(BC_ind, i);
				}
				sum -= current_val;				
			}
			//jump the BC to i position
			BC_ind=i;
		}
		//System.out.println(this.value);
	}
	/*
	public static void main(String [] args){
		ReadData f = new ReadData("Ontario");
		//f.Read();
		//System.out.println(f.value);
		//System.out.println(f.Index2Catg(0));
		//System.out.println(f.Catg2Index(5));
	}//*/
	

}
