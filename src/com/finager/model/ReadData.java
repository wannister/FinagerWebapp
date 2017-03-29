package com.finager.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import edu.princeton.cs.algs4.Digraph;

/**
 * Constructor:Vector<String> containing only data 
 * 				from user province
 * 
 * @author mina
 *
 */
public class ReadData {
	private Digraph data;
	private Vector<Double> value;
	private Vector<Integer> index_list;
	private Vector<String> prov_info;
	
	public ReadData(Vector<String> file){
		this.data = new Digraph(120);
		this.value = new Vector<Double>();
		this.index_list = new Vector<Integer>();
		this.prov_info = file;
		//ReadFile();
		Read();
	}
	//getter
	public Digraph Graph(){
		return this.data;
	}
	//getter
	public Vector<Double> Value(){
		return this.value;
	}
	//getter
	public int Catg2Index(int catg){
		return this.index_list.indexOf(catg);
	}
	//getter
	public int Index2Catg(int index){
		return this.index_list.elementAt(index);
	}
	
	//getter
	public Vector<Integer> indexes(){
		return index_list;
	}
	/*fake vector input data
	private void ReadFile(){
	 
		this.file_info.add("1981,Ontario,Current prices,Health insurance ,196.8,v62785314,7.1.90");
		this.file_info.add("1981,Ontario,Current prices,Health insurance ,96.8,v62785315,7.1.90");
		this.file_info.add("1981,Ontario,Current prices,Health insurance ,6.8,v62785314,7.1.90");
		this.file_info.add("1981,Ontario,Current prices,Health insurance ,90.0,v62785314,7.1.90");
		this.file_info.add("1981,Ontario,Current prices,Health insurance ,100.0,v62785314,7.1.90");
		this.file_info.add("1981,Ontario,Current prices,Health insurance ,80.0,v62785314,7.1.90");
		this.file_info.add("1981,Ontario,Current prices,Health insurance ,20.0,v62785314,7.1.90");
	}
	*/
	
	private void Read(){
		/* old code used for comparison
		try {
			File file = new File("data/" + this.user_prov + ".csv");
			//opens and reads requested file
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String[] input = bufferedReader.readLine().split(",");
			
			Double total = Double.parseDouble(input[4]);
			
			//provincial total
			this.value.add(0,total);
			
			//total value of first big category
			String line;
			
			int i = 0; //value index initialized to provincial total
			int BC_ind = 0; //big category index
			
			while ((line = bufferedReader.readLine()) != null) {
				//increment BC and i together
				i++; BC_ind++;
				
				//add the index to the lsit
				this.index_list.add(i);				
				
				//split the line
				input = line.split(",");
				//get the value of the sum
				Double sum = Double.parseDouble(input[4]);
				//store value into the list
				this.value.add(i,sum);					
				
				String line2; //value of sub-categories
				
				while(Math.floor(Math.abs(sum))!=0.0){
					line2 = bufferedReader.readLine();
					//only increment i
					i++;
				
					//split the line
					input = line2.split(",");
					//get the value of line
					double current_val = Double.parseDouble(input[4]);
					//store into the value vector
					this.value.add(i,current_val);
					
					//if the line is not big category
					if (sum != current_val){
						this.data.addEdge(BC_ind, i);
					}
					sum -= current_val;
					
				}
				//jump the BC to i position
				BC_ind=i;
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		//line containing provincial total
		String[] total = this.prov_info.elementAt(0).split(",");
		
		//retrieve and parse provincial total
		Double final_val = Double.parseDouble(total[1]);
		
		//stores provincial total
		this.value.add(0,final_val);
		
		int BC_ind = 0; //big category index
		
		for (int i = 1; i<prov_info.size();i++) {
			//stores first big category string
			String line = prov_info.elementAt(i);
			
			//increment BC and i together
			BC_ind++;
			
			//add the index to the list
			this.index_list.add(i);				
			
			//split the line
			String[] input = line.split(",");
			//get the value of the sum
			Double sum = Double.parseDouble(input[1]);
			//store value into the list
			this.value.add(i,sum);					
			
			String line2; //value of sub-categories
			
			while(Math.floor(Math.abs(sum))!=0.0){
				i++; //manually increase counter
				
				line2 = this.prov_info.elementAt(i); //temp stores string containing sub-catg values			
			
				//split the line
				input = line2.split(",");
				//get the value of line
				double current_val = Double.parseDouble(input[1]);
				//store into the value vector
				this.value.add(i,current_val);
				
				//if the line is not big category
				if (sum != current_val){
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
