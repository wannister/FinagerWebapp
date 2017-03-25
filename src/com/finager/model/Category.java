package finager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Category {
	File csv;
	static Map<Integer, String> match = new HashMap<Integer, String>();
	Category() throws IOException{
		this.csv = new File("data/Canada.csv");
		
	}
	
	private void match() throws IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(csv));
		String line = null;
		int i = 0;
		while ((line = reader.readLine()) != null) {
			String item[] = line.split(",");
			match.put(i, item[5]);
			i++;
		}
		reader.close();
		
	}
	
	public Integer catg2int(String s){		//Map cannot directly get 
		for(Integer i:match.keySet()){
			if  (match.get(i).equals(s)){
				return i;
			}	
		}
		return null;
	}
	public String int2catg(int i){
		return match.get(i);
	} 

	public static void main(String[] args) throws IOException {
		Category Test1 = new Category();
		Test1.match();
		System.out.println(Test1.int2catg(1));
		System.out.println(Test1.catg2int("Food and non-alcoholic beverages"));
		System.out.println(Test1.catg2int("Food"));
	}

}
