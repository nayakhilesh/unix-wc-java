//Akhilesh Nayak
//akhilesh@seas.upenn.edu
//CIS 121
//2/10/2012

package edu.upenn.cis.cis121.homework3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * An implementation of the UNIX word count utility wc.
 * 
 * @author Julia Stoyanovich (jstoy@cis.upenn.edu)
 * 
 * @author Akhilesh Nayak (akhilesh@seas.upenn.edu)
 * 
 * CIS 121, Spring 2012
 * 
 */

public class WordCount {

	/**
	 * Static method that processes the input.
	 * @param isr input stream reader to use to read input file(s)
	 * @param isr2 a different input stream reader to use to read the same input file
	 * @return int[] output integer array of size 3 specifying number of lines, words, and characters in that order
	 * @throws IOException
	 **/
	public static int[] processInput(InputStreamReader isr, InputStreamReader isr2) throws IOException {
        
		int numLines = 0, numWords = 0, numCharacters = 0, currentCharacter = 0;
		int[] arr = new int[3]; //used to return the output
		String string;
		BufferedReader bufferedReader = new BufferedReader(isr);
		
		string = bufferedReader.readLine();
			
		while(string != null) {	
			
			numLines++;
			String[] word = string.split(" ");
				
			for (int i=0;i<word.length;i++) {
				if (word[i].length() > 0) {
					numWords++;
				}
			}
				
			string = bufferedReader.readLine();
		}
		bufferedReader.close();
		
		currentCharacter = isr2.read();
		
		while(currentCharacter != -1)
		{
			numCharacters++;
			currentCharacter = isr2.read();
		}
	
		arr[0] = numLines;
		arr[1] = numWords;
		arr[2] = numCharacters;
		
		return (arr);
	}

    /**
     * Program that takes a list of filenames as a command-line argument.
     * For each file, program outputs number of lines, words, and characters
     * in the file. If no filename is provided, reports that a filename argument is required
     **/
	public static void main(String args[]) {
        
		FileReader fileReader, fileReader2;
		int[] total = {0,0,0};
		int[] arr;
		
		if (args.length == 0) {
			System.out.println("Filename argument is required");
		}
		else {
			
			for(int i=0;i<args.length;i++) {
				
				try {
					fileReader = new FileReader(args[i]);
					fileReader2 = new FileReader(args[i]);
					arr = processInput(fileReader,fileReader2);
					total[0] += arr[0];
					total[1] += arr[1];
					total[2] += arr[2];
					System.out.println(Integer.toString(arr[0]) + "\t" + Integer.toString(arr[1]) + "\t" + Integer.toString(arr[2]) + "\t" + args[i]);
				} 
				catch (FileNotFoundException e) {
					System.out.println("WordCount: " + args[i] + ": No such file or directory");
				} 
				catch (IOException e) {
					System.out.println("Couldn't read file");
				}
				
			}
			if (args.length > 1) { //if the number of arguments was greater than 1 we need to print the total values
				System.out.println((Integer.toString(total[0]) + "\t" + Integer.toString(total[1]) + "\t" + Integer.toString(total[2]) + "\t" + "total"));
			}
		}
	}

}
