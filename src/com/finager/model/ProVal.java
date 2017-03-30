package com.finager.model;

public class ProVal implements Comparable<ProVal>{
	private String prov;
	private Double val;
	
	public ProVal (String prov, Double val){
		this.prov = prov;
		this.val = val;
	}
	
	public Double getVal(){
		return this.val;
	}
	
	public String getProv(){
		return this.prov;
	}

	@Override
	public int compareTo(ProVal other) {
		return this.val.compareTo(other.getVal());
	}

	public String toString() {
		return "ProVal [prov=" + prov + ", val=" + val + "]";
	}


}
