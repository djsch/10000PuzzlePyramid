package me.djsch.puzzlePyramid;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Set;

public class PyramidFileReaderWriter {
    // Read all clues from a File containing a set of clues and return them
    // in an ArrayList.
    public static ArrayList<String> readCellFile(String filePath) {
        ArrayList<String> clues = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                clues.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return clues;
    }

    // Read all words from a given file and return them in a Set.
    public static Set<String> readWordsFile(String filePath) {
        Set<String> validWords = new HashSet<>();
        try {
            File file = new File(filePath);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                validWords.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading from file: ");
            System.out.println(filePath);
            e.printStackTrace();
        }

        return validWords;
    }

    // Write the given words into a file at the given path.
    public static void writeWordsFile(ArrayList<String> words, String filePath) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            for (String word : words)
                myWriter.write(word + '\n');
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the anagrams file.");
            e.printStackTrace();
        }
    }
}
