package edu.howardcc.cmsy167.simplemap;
/**
 * Driver - A driver class that implements the methods of the CustomBinaryTreeSimpleMap class
 * Copyright 2023 Howard Community College
 * @author Ryan Giannamore
 * @version 1.0
 */

// Import libraries
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
public class Driver{
	
	// Main method
	public static void main(String[]args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Copyright 2023 Howard Community College");
		System.out.println();
		
		BufferedReader text = enterPath(scanner);
		ArrayList<String> SentenceList = formatToLines(text);
		ArrayList<String> formatted = wordList(SentenceList);
		List<Line> lineList = toLineList(SentenceList);
		SimpleMap<String, Integer> map = mapToIndex(formatted);
		menuChoice(scanner, map, formatted,lineList);
		scanner.close();
	}
	
	// menu to select which methods to use
	public static void menuChoice(Scanner scanner, SimpleMap<String, Integer> map, ArrayList<String> sentenceList, List<Line> LineList) {
		int response = 0;
		while (response != 4) {
			response = validateMenuChoice(scanner);
			switch(response) {
			case 1:
				searchFromIndex(scanner, map, LineList);
				break;
			case 2:
				clearMap(map);
				break;
			case 3:
				addToIndex(scanner, map);
				break;
			case 4:
				System.out.println("Good Bye.");
				break;
				
			}
			
		}

	}
	
	public static int validateMenuChoice(Scanner scanner) {
		int choice= 0;
		
		do {
			try {
				System.out.println("What would you like to do?(Select the number of your choice) \n1.) Search lines by key \n2.) Clear map \n3.) Create Custom Map \n4) Exit");
				choice = scanner.nextInt();
				scanner.nextLine();
				if(choice < 1 || choice > 4) {
					System.out.println("You may only choose between options 1-4");
				}
			}catch(InputMismatchException ime) {
				System.out.println("Input must be a the number of any of the above options from 1-4");
			}

		}while (choice < 1 || choice > 4);
		return choice;
	}
	
	// Methods for importing file and outputting content to lines
	public static BufferedReader enterPath (Scanner scanner) {

		String searchText="";
		BufferedReader read = null;
		boolean validate = false;
		while (validate != true) {
			System.out.println("Enter path for a text file to search:");
			searchText = scanner.nextLine();
			
			validate = validateFilePath("../"+searchText);
			
			try {
				read = new BufferedReader(new FileReader("../"+searchText));
				
			}catch(IOException e){
				System.out.println("File not found. Please enter valid file.");
			}
			

		}
		
		return read;
	}
	public static boolean validateFilePath(String input) {
		boolean status;
		File f = new File(input);
		if(f.exists()) {
			status = true;
		}else {
			status = false;
		}
		return status;
	}
	
	// methods to add file contents to array
	public static ArrayList<String> formatToLines (BufferedReader input) throws IOException{
		String line = input.readLine();
		ArrayList<String> lines = new ArrayList<String>();
		while (line != null) {
			lines.add(line);
			line = input.readLine();
		}
		for (int i = 0 ; i<lines.size(); i++) {
		    String sentence = lines.get(i);
		    sentence= sentence.trim();
		    lines.set(i, sentence);
		}
		return lines;
	}
	public static ArrayList<String>wordList(ArrayList<String> sentenceList){
		String [] tokens;
		ArrayList<String> formattedArrayList= new ArrayList<String>();

		for (String i : sentenceList) {
			String formatted = i.replaceAll("[^a-zA-Z0-9\\s]", "");
			tokens = formatted.split(" ");
			for(String token : tokens) {
				formattedArrayList.add(token);
			}
			
		}
		
		return formattedArrayList;
	}
	
	// method to output Lines to line object
	public static List<Line> toLineList(ArrayList<String> sentences){
		List<Line> lines = new ArrayList<>();
		int lineNumber = 1;
		for(String sentence : sentences) {
			lines.add(new Line(lineNumber, sentence));
			lineNumber++;
		}
		return lines;
	}
	
	// method to output words to map
	public static SimpleMap<String, Integer> mapToIndex(ArrayList<String> input) {
		SimpleMap<String, Integer> index = new CustomBinaryTreeSimpleMap<>();
		int key = 0;
		for (String i : input) {
			key++;
			index.put(i, key);
			
		}
		return index;
	}
	
	// methods to activate all methods of the custom binary trees.
	public static void searchFromIndex(Scanner scanner,SimpleMap<String, Integer> map, List<Line> lineList) {
		String response="";
		do {
			System.out.println("Please enter search term (blank to exit):");
			response= scanner.nextLine();

			
			boolean success = map.containsKey(response);

			
			if(success && !response.isEmpty()) {
				for(Line line : lineList) {
					if(line.getContent().matches("(?i).*\\b"+response+"\\b.*")) {
						System.out.printf("%d: %s%n", line.getLineNumber(), line.getContent());
					}
				}
			}
			if(!success) {
				System.out.println("Term Not Found.");
			}
		}while(!response.isEmpty());
	}
	
	public static void clearMap(SimpleMap<String, Integer> map) {
		map.clear();
		boolean empty = map.isEmpty();
		if(empty) {
			System.out.println("Map Cleared.");
		}
		
	}
	public static void addToIndex(Scanner scanner, SimpleMap<String, Integer>map) {
		String response1 ="";
		String response2="";
		
		do {
			System.out.println("Add content to the map(blank to stop)");
			System.out.println();
			System.out.println("Enter the content you wish to add to the map:");
			String content = scanner.nextLine();

			System.out.println("Enter the index in the map that you would like to replace:");

			if(scanner.hasNextInt()) {
				int index = scanner.nextInt();
				scanner.nextLine();
				map.put(content, index);
			}else {
				System.out.println("Input must be an integer.");
				scanner.nextLine();
			}
			System.out.println("Continue? (hit any key to continue or blank to exit)");
			response1 = scanner.nextLine();
		}while(!response1.isEmpty());
		
		boolean success = false;
		
		System.out.println("Search something you put in the map");
		System.out.println();
		System.out.println("Enter Key:");
		response2 =  scanner.nextLine().trim();
		
		
		success = map.containsKey(response2);
		if(success) {
			int index = map.get(response2);
			System.out.printf("The index of your input is %d%n", index);
		}else {
			System.out.println("Item Not Found");
		}
	

	}

}