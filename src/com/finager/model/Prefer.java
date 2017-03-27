package com.finager.model;

//use ReadData module

public class Prefer {
	private Double[] pref;
	private ReadData rd;
	
	public Prefer(Double[] user, ReadData read){
		pref = user;
		rd = read;
		Replace();
		DataRevised();
	}
	
	public Double Value(int catg){
		return pref[catg];
	}
	
	public Double[] vals(){
		return this.pref;
	}
	
	private void Replace(){
		for(int i = 0; i<pref.length;i++){
			//find the corresponding big category
			int catg = rd.Index2Catg(i);
			//find the corresponding value
			Double val = rd.Value().get(catg);
			
			pref [i] = pref [i] * val; 
		}
	}
	
	private void DataRevised(){
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

