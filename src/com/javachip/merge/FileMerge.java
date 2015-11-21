package com.javachip.merge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class FileMerge {

	// To store the lines that are already in file 1
	static HashSet<String> file1Set = new HashSet<String>();
	// Lists of file contents by line
	static ArrayList<String> file2List = new ArrayList<String>();
	static ArrayList<String> file1List = new ArrayList<String>();
	// Repeated lines
	static ArrayList<String> repeats = new ArrayList<String>();
	// Merged List
	static ArrayList<String> results = new ArrayList<String>();
	
	public static void mergeFilesList(File parent, String name1, String name2) throws IOException {

		File file1 = new File(parent, name1);
		File file2 = new File(parent, name2);

		BufferedReader br = new BufferedReader(new FileReader(file1));
		BufferedReader br2 = new BufferedReader(new FileReader(file2));

		String inline = "";

		try {
			while ((inline = br.readLine()) != null) {
				inline.trim();
				file1List.add(inline);
				file1Set.add(inline);
			}
			while ((inline = br2.readLine()) != null) {
				inline.trim();
				if (file1Set.contains(inline)) {
					repeats.add(inline);
				} else {
					file2List.add(inline);
				}
			}
			results.addAll(file1List);
			results.addAll(file2List);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
			br2.close();
		}
	}

	public static void saveMerge(File parentDir, String fileName, boolean keepRepeats)
			throws FileNotFoundException {
		File mergeFile = new File(parentDir, fileName);
		if (mergeFile.exists()) {
			System.out.println("File already exists, please choose a different file name. Canceling merge.");
			System.exit(0);
		}
		
		if (keepRepeats) 
			results.addAll(repeats);
			
		PrintWriter pw = new PrintWriter(mergeFile);
		Iterator<String> iterator = results.iterator();
		while (iterator.hasNext()) {
			pw.println(iterator.next());
		}
		pw.close();
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
			mergeFilesList(mergeTestFiles, "Local.txt", "Remote.txt");
			saveMerge(mergeTestFiles, "MergedFileTest2", true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
