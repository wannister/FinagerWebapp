package com.finager.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;


public class ReadData {
	private String user_prov;
	private Digraph data;
	private Vector<Double> value;
	private Vector<Integer> index_list;
	
	public ReadData(String prov){
		this.user_prov = prov;
		this.data = new Digraph(120);
		this.value = new Vector<Double>();
		this.index_list = new Vector<Integer>();
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
	
	private void Read(){
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
				i++;
				this.index_list.add(i);				
				int SC_ind = 0; //small category index
				
				this.data.addEdge(BC_ind, SC_ind);
				
				input = line.split(",");
				Double sum = Double.parseDouble(input[4]);
				this.value.add(i,sum);					
				
				String line2; //value of sub-categories
				while(((line2 = bufferedReader.readLine()) != null) & Math.floor(sum)!=0){
					SC_ind++;
					i++;
					this.index_list.add(i);
					
					this.data.addEdge(BC_ind, SC_ind);
					input = line2.split(",");
					double current_val = Double.parseDouble(input[4]);
					this.value.add(i,current_val);
					sum -= current_val;			
				}
				SC_ind = 0; //restore small category index
				BC_ind++; //move on to next big category
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	/*
	public static void main(String [] args){
		ReadData f = new ReadData("Ontario");
		System.out.println(f.value);
		//System.out.println(f.Index2Catg(0));
		//System.out.println(f.Catg2Index(5));
	}
	*/

}
