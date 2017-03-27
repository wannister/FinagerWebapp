package com.finager.model;

//use ReadData module

public class Prefer {
	Double[] pref;
	ReadData rd;
	public Prefer(Double[] user, ReadData read){
		pref = user;
		rd = read;
		Replace();
		DataRevised();
	}
	public Double Value(int catg){
		return rd.Value().get(catg);
	}
	private void Replace(){
		for(int i = 0; i<pref.length;i++){
			pref [i] = pref [i] * rd.Value().get(i); //how to retrieve this
		}
	}
	private void DataRevised(){
		//-----double oldData = rd.Value().; //how to retrieve this
		double newData = 0;
		for (double entry : pref) {
		    newData += entry;
		}
		double oldData = 0;
		double kRatio = oldData / newData;
		for(int i = 0; i<pref.length; i++){
			pref[i] = pref[i]*kRatio;
		}		
	}

}

