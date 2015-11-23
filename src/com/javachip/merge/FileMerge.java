package com.javachip.merge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class FileMerge {

	// To store the lines that are already in file 1
	HashSet file1Set = new HashSet();
	// Repeated lines
	LinkedList repeats = new LinkedList();
	// Merged List
	int results[];
	int resultsLast = 0;

	public void mergeFilesList(File parent, String name1, String name2) throws IOException {

		File file1 = new File(parent, name1);
		File file2 = new File(parent, name2);

		BufferedReader br = new BufferedReader(new FileReader(file1));
		BufferedReader br2 = new BufferedReader(new FileReader(file2));

		int count = 0;

		String inline = "";
		int num;

		try {
			while ((inline = br.readLine()) != null) {
				num = Integer.parseInt(inline.trim());
				count++;
				file1Set.add(num);
			}
			while ((inline = br2.readLine()) != null) {
				count++;
			}

			br.close();
			br2.close();
			br = new BufferedReader(new FileReader(file1));
			br2 = new BufferedReader(new FileReader(file2));
			results = new int[count];

			while ((inline = br.readLine()) != null) {
				num = Integer.parseInt(inline.trim());
				results[resultsLast] = num;
				resultsLast++;
			}
			while ((inline = br2.readLine()) != null) {
				num = Integer.parseInt(inline.trim());
				if (file1Set.contains(num)) {
					repeats.add(num);
				} else {
					results[resultsLast] = num;
					resultsLast++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
			br2.close();
		}
	}

	public LinkedList getRepeats() {
		return repeats;
	}

	public void setRepeats(LinkedList l) {
		repeats = l;
	}

	public void saveMerge(String fileName) throws FileNotFoundException {
		File mergeFile = new File(fileName);

		addToResult(repeats);

		sort(results, 0, results.length - 1);

		// Save merged file
		PrintWriter pw = new PrintWriter(mergeFile);
		for (int i = 0; i < results.length; i++) {
			pw.println(results[i]);
		}
		
		pw.close();
	}

	public void addToResult(LinkedList l) {
		Iterator i = l.iterator();
		try {
			while (i.hasNext()) {
				results[resultsLast] = (int) i.next();
				resultsLast++;
			}
		} catch (Exception e) {
			System.out.println("addToResult failled");
			e.printStackTrace();
		}
	}

	public static void sort(int[] numArr, int low, int hi) {
		int[] temp = new int[hi];
		int point = 0;

		temp[point] = low;
		// point++;
		temp[++point] = hi;

		while (point > -1) {
			hi = temp[point--];
			low = temp[point--];

			int hi2 = hi;
			int low2 = low;

			int temp2 = numArr[hi2];

			int y = (low2 - 1);

			for (int i = low2; i <= hi2 - 1; i++) {
				if (numArr[i] <= temp2) {
					y++;
					swap(numArr, y, i);
				}
			}
			swap(numArr, y + 1, hi2);

			int pivot = y + 1;

			// point++;
			if (pivot - 1 > low) {
				point++;
				temp[point] = low;
				temp[++point] = pivot - 1;
			}

			if (pivot + 1 < hi) {
				point++;
				temp[point] = pivot + 1;
				temp[++point] = hi;
			}

			// System.out.println("wdkw");
		} // while
	}

	public static void swap(int a[], int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String args[]) {

		// Create or find the parent folder
		System.out.println("Folder is found in: " + System.getProperty("user.dir"));
		File mergeTestFiles = new File("mergeTestFiles");
		if (!mergeTestFiles.exists()) {
			if (mergeTestFiles.mkdir()) {
				System.out.println("Directory has been created " + mergeTestFiles.getAbsolutePath());
			} else {
				System.out.println("Dir not created.");
			}
		} else {
			System.out.println("Directory already exists, continuing with execution.");
		}
		// ----------------------------------

		try {
			String fileName = Long.toString(new Date().getTime());
			FileMerge filemerge = new FileMerge();
			filemerge.mergeFilesList(mergeTestFiles, "Local.txt", "Remote.txt");
			filemerge.saveMerge(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("finished");
	}

}
