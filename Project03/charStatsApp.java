package Project03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The "charStatsApp" is a Java application that analyzes the character statistics of a given text file.
 * It reads the contents of the file and tracks the occurrences of each character using a 256x2 array.
 * Lastly, prints statistics for each character encountered while reading the external file.
 */
public class charStatsApp {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\wwwdr\\Documents\\JavaProjects\\Project03\\characters.txt";
        int[][] charArray = new int[256][2];

        // Initialize charArray
        for (int i = 0; i < 256; i++) {
            charArray[i][0] = i;  // Store the character (ASCII value)
            charArray[i][1] = 0;  // Initialize the counter
        }

        countCharacters(filePath, charArray);
        printCharacterCounts(charArray);

    }

    /**
     * This method reads the characters from an external txt file and stores them into a 2D Array.
     * @param filePath a string representing the Path of the characters.txt file
     * @param charArray a 2D 256 by 2 Array which will store the statistics for each character
     */
    public static void countCharacters(String filePath, int[][] charArray) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            int character;
            while ((character = reader.read()) != -1) {
                charArray[character][1]++;  // Increment the counter for the encountered character
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method responsible for printing out the statistics from the charArray
     * @param charArray a 2D 256x2 Array containing character Statistics
     */
    public static void printCharacterCounts(int[][] charArray) {
        for (int i = 0; i < 256; i++) {
            if (charArray[i][1] > 0) {
                System.out.println((char) charArray[i][0] + ": " + charArray[i][1]);
            }
        }
    }
}
