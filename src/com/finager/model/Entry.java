package finager;

import java.io.*;
import java.util.Arrays;

import edu.princeton.cs.algs4.LinearRegression;

public class Entry {
	double[] realyear, realexpenditure;
	double[] year = new double[50];
	double[] expenditure = new double[50];
	double selectyear;
	String[] information = new String[4];
	File csv = new File("data/" + "predict" + ".csv");

	Entry() {

	}

	Entry(double Selectyear) throws IOException {

		this.selectyear = Selectyear;
		PrintWriter clear = new PrintWriter(csv);
		clear.close();
	}

	public void EntryIO() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data/expenditure.csv"));
		reader.readLine();// skip the first line
		String line = null;
		int i = 0;
		double checker = 0;
		while ((line = reader.readLine()) != null) {
			String item[] = line.split(",");// seperate a line by ,
			if (item[2].contains("2007"))// skip the prices in 2007
				continue;
			// first column
			if (item[item.length-2].contains("15."))//skip the outside canada line
				continue;
			if (item[item.length-2].contains("16."))//skip the outside canada line
				continue;
			if (Double.parseDouble(item[0]) > checker) { // add the elements to
															// an array if the
															// year is
															// increasing
				year[i] = Double.parseDouble(item[0]);
				checker = year[i];
				information[0] = item[1];
				information[1] = item[item.length - 2];
				information[2] = item[item.length - 3];
				information[3] = item[item.length - 4];
				try {
					expenditure[i] = Double.parseDouble(item[item.length - 1]);
					i++;
				} catch (Exception e) {
					continue;

				}

			}

			else {
				checker = Double.parseDouble(item[0]);
				realyear = Arrays.copyOfRange(year, 0, i);
				realexpenditure = Arrays.copyOfRange(expenditure, 0, i);
				writetofile();

				i = 0;

			}

		}
		reader.close();
	}

	public double predict() {
		LinearRegression A = new LinearRegression(realyear, realexpenditure);
		return A.predict(this.selectyear);
	}

	public void writetofile() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); //
		bw.write(selectyear + "," + information[0] + "," + information[1] + ","
				+ information[2].substring(1, information[2].length()) + "," + predict()+ "," + information[3]);
		bw.newLine();
		bw.close();

	}

	public static void main(String[] args) throws IOException {
		Entry A = new Entry(2016);
		A.EntryIO();
	}
}
