package com.finager.model;

import java.util.Vector;

/**
 * Constructor:Vector<String> containing only data Partition
 * constructor(line2,prov); after predicting future value, String prov
 * 
 * Excepted output: Vector<String> containing only data from user province
 * 
 * @author mina
 *
 */
public class Partition {

	String Province;
	Vector<String> Partitioninput = new Vector<String>();
	Vector<String> Partitionoutput = new Vector<String>();

	Partition() {

	}

	public Partition(Vector<String> partitioninput, String province) {
		this.Partitioninput = partitioninput;
		this.Province = province;
	}

	public void Partition_IO() {
		int i = 0;

		while (i < Partitioninput.size()) {
			String[] inputitem = Partitioninput.get(i).split(",");
			if (inputitem[2] == Province) {
				String transfer = inputitem[0] + "," + inputitem[1] + "," + inputitem[2] + "," + inputitem[3] + ","
						+ inputitem[4] + "," + inputitem[5];
				Partitionoutput.add(transfer);
			}
			i++;
		}
	}

	public Vector<String> partitionoutput() {
		return Partitionoutput;
	}

	/*public static void main(String[] args) {
		Partition Test1 = new Partition("Alberta");
		for (int i = 0; i < 1497; i++) {
			System.out.println(Test1.partitionoutput().get(i));

		}
	}*/

}
