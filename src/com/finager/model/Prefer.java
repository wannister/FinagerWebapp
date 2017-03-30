package com.finager.model;

import java.util.Arrays;

//use ReadData module
/**
 * Project Information*
 * -------------------* 
 * Name: Finager*
 * Course Code: CS 2XB3* 
 * Lab Section: 01* 
 * The {@code Prefer} class provides methods to generate expenditure by user preferences.
 */
public class Prefer {
	private Double[] pref;
	private ReadData rd;
	/**
	 * Prefer constructor use to calculate the big category expenditure adjust by user preferences.
	 * @param user-The user's preference for each big category.
	 * @param read-The ReadData constructor from ReadData.java.
	 */
	public Prefer(Double[] user, ReadData read){
		pref = user;
		rd = read;
		Replace();
	}
	/**
	 * Get the adjust expenditure of given category position.
	 * @param catg-The given category position.
	 * @return-The adjust expenditure of given category position.
	 */
	public Double Value(int catg){
		return pref[catg];
	}
	/**
	 * Get expenditure adjust by user preferences.
	 * @return-The expenditure adjust by user preferences.
	 */
	public Double[] vals(){
		return this.pref;
	}
	/**
	 * Calculate the big category expenditure adjust by user preferences.
	 * And adjust ration K.
	 */
	private void Replace(){
		
		for(int i = 0; i<pref.length;i++){
			//find the corresponding big category
			int catg = rd.Index2Catg(i);
			//find the corresponding value
			Double val = rd.Value().get(catg);
			pref [i] = pref [i] * val; 
		}
		//calculate the new overall expenditure
		double newData = 0;
		for (double entry : pref) {
		    newData += entry;
		}
		double oldData = rd.Value().get(0);
		
		//adjust the ratio
		double kRatio = oldData / newData;
		
		for(int i = 0; i<pref.length; i++){
			pref[i] = pref[i]*kRatio;
		}
	}

}

