package com.finager.model;

//use ReadData module

public class Prefer {
	Double[] pref;
	ReadData rd;
	public Preference(Double[] user, ReadData read){
		pref = user;
		rd = read;
		Replace();
		DataRevised();
	}
	public Double Value(int catg){
		
	}
	private void Replace(){
		for(int i = 0; i<pref.length;i++){
			pref [i] = pref [i] * rd.Value().elementAt(index); //how to retrive this
		}
	}
	private void DataRevised(){
		double oldData = 0; //how to retrive this
		double newData = 0;
		for (double entry : pref) {
		    newData += entry;
		}
		double kRatio = oldData / newData;
		for(int i = 0; i<pref.length; i++){
			pref[i] = pref[i]*kRatio;
		}		
	}

}

