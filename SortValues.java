package com.suparn.sharma.assignment1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SortValues {

	public static void main(String[] args) throws Exception {

//		FileReader file = new FileReader("C:\\Supp\\Suparn_Sharma_CodeSpace\\src\\resources\\outputFile.txt");
		Path path = Paths.get("src","main","resources");
		File file = new File(path.toString()+"\\outputFile.txt");
		File resultFile = new File(path.toString()+"\\resultFile.txt");
		resultFile.createNewFile();

		FileWriter fw = new FileWriter(resultFile, false);
		PrintWriter pw = new PrintWriter(fw, false);
		pw.flush();
		pw.close();

		long startTimeMillis = System.currentTimeMillis();
		Scanner sc = new Scanner(file);
		ArrayList<String> values = new ArrayList<String>();
		List<Integer> temp = new ArrayList<Integer>();

		while (sc.hasNextLine()) {
			values.add(sc.nextLine());
		}
		for (int i = 0; i < values.size(); i++) {
			temp.add(Integer.parseInt(values.get(i).substring(0, 8)));
		}
		Collections.sort(temp);

		StringBuilder sb = new StringBuilder(8);
		FileOutputStream stream = new FileOutputStream(resultFile, true);
		for (int i = 0; i < values.size(); i++) {
			String val = temp.get(i).toString();
			for (int j = 0; j < values.size(); j++) {
				if (values.get(j).contains(val)) {
					String s = values.get(j) + "\n";
					byte[] b = s.getBytes();
					stream.write(b);
				}
			}
		}
		long diff = System.currentTimeMillis() - startTimeMillis;
		System.out.println("Throughput fare per record for Sorting: "+ values.size()/diff);
		stream.close();
	}
}
