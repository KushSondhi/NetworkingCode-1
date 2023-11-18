package com.suparn.sharma.assignment1;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.Scanner;

public class RandomIntegerString {

	public static void main(String[] args) throws Exception {

		System.out.print("Enter no. of Entries : ");
		Scanner sc = new Scanner(System.in);

		Integer entries = sc.nextInt();
		System.out.println(entries);

		System.out.print("Enter file path : ");
		String path = sc.next();

		Instant start = Instant.now();

		File file = new File(path);
		if (file.exists()) {
//			System.out.println("File Already exist");
			FileOutputStream pw = new FileOutputStream(path);
			pw.write(("").getBytes());
			pw.close();
		}

		Path outputDir = Paths.get(path);
//		System.out.println(outputDir.toAbsolutePath());

		Random random = new Random();

		int minValue = 10000000;
		int maxValue = 99999999;
		int randomNum = random.nextInt(maxValue - minValue) + minValue;
		String incrementer = "0";
		final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String lower = upper.toLowerCase();
		final String digits = "0123456789";
		final String s = upper.concat(digits).concat(lower);

		StringBuilder sb = new StringBuilder(46);
		FileOutputStream stream = new FileOutputStream(outputDir.toFile(), true);

		long startTimeMillis = System.currentTimeMillis();
		for (int j = 0; j < entries; j++) {

			randomNum += random.nextInt(1000);
			String repeated = new String(new char[10 - String.valueOf(j).length()]).replace("\0", incrementer);

			for (int i = 0; i < 46; i++) {
				sb.append(s.charAt(random.nextInt((s.length()))));
			}

			String finalString = randomNum + "_" + repeated + j + "_" + sb + "\n";
			byte[] b = finalString.getBytes();
			stream.write(b);

			b = null;
			sb.replace(0, 46, "");

		}
		long diff = System.currentTimeMillis() - startTimeMillis;
		System.out.println("Throughput fare per record: "+ entries/diff);
		stream.close();
		System.out.println("Generated Records : " + entries);
		Instant end = Instant.now();
		long time = Duration.between(start, end).toMillis();
		System.out.println("Time of Execution (in ms): " + time);
	}

}
