package Project01;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class NumbersApp {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\wwwdr\\Documents\\JavaProjects\\Project01\\49nums.txt";

        int[] numbers = readIntegersFromFile(filePath);
        Arrays.sort(numbers);

        // Create groups of 6 numbers
        int[][] groups = createGroups(numbers, 6);

        // Filter and print the groups
        filterAndPrintGroups(groups);
    }

    /**
     *  Method that is responsible for reading numbers from a txt file.
     * @param filePath  a string to the exact file path.
     * @return  an array of numbers read.
     */
    public static int[] readIntegersFromFile(String filePath) {
        int[] numbers = new int[49];
        int index = 0;

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();

                if (number == -1) {
                    break; // Exit the loop if -1 is encountered
                }

                numbers[index] = number;
                index++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Create a new array with the exact size of numbers read
        int[] result = new int[index];
        System.arraycopy(numbers, 0, result, 0, index);

        return result;
    }

    /**
     * responsible for splitting the original array into groups of the specified size and organizing them into a 2D array for further processing or analysis.
     * @param numbers array of numbers from text file
     * @param groupSize an int number representing the size of the group, in our case 6
     * @return  a 2D array
     */
    public static int[][] createGroups(int[] numbers, int groupSize) {
        int totalGroups = numbers.length / groupSize;
        int[][] groups = new int[totalGroups][groupSize];

        for (int i = 0; i < totalGroups; i++) {
            for (int j = 0; j < groupSize; j++) {
                groups[i][j] = numbers[i * groupSize + j];
            }
        }

        return groups;
    }

    /**
     * This method filters the groups based on the provided criteria and prints the valid groups.
     * @param groups all allowed groups for export and print
     */
    public static void filterAndPrintGroups(int[][] groups) {
        String writeFilePath = "C:\\Users\\wwwdr\\Documents\\JavaProjects\\Project01\\eligible_groups.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFilePath))) {
            int count = 0;
            for (int[] group : groups) {
                if (isGroupValid(group)) {
                    writer.write(Arrays.toString(group));
                    writer.newLine();
                    count++;
                }
            }
            System.out.println("Eligible groups have been written to the file: " + writeFilePath);
            System.out.println("Total eligible groups written: " + count);
        } catch (IOException e) {
            System.out.println("Error writing the file: " + e.getMessage());
        }
    }

    /**
     * This method checks if a group meets all the criteria by calling the respective helper methods.
     * @param group  a generated group of 6 numbers for evaluation
     * @return a boolean which represents whether the group is valid or not
     */
    public static boolean isGroupValid(int[] group) {
        return !hasExceededMaxEvenNumbers(group, 4) &&
                !hasExceededMaxOddNumbers(group, 4) &&
                !hasExceededMaxContiguousNumbers(group, 2) &&
                !hasExceededMaxSameEndingNumbers(group, 3) &&
                !hasExceededMaxSameDecadeNumbers(group, 3);
    }

    /**
     * This method counts the number of even numbers in a group and checks if it exceeds the maximum allowed.
     * @param group a generated group of 6 numbers for evaluation
     * @param maxEvenNumbers how many numbers of the same trait to allow
     * @return a boolean which represents whether the condition is met or not
     */
    public static boolean hasExceededMaxEvenNumbers(int[] group, int maxEvenNumbers) {
        int count = 0;
        for (int number : group) {
            if (number % 2 == 0) {
                count++;
            }
        }
        return count > maxEvenNumbers;
    }

    /**
     * This method counts the number of odd numbers in a group and checks if it exceeds the maximum allowed.
     * @param group a generated group of 6 numbers for evaluation
     * @param maxOddNumbers how many numbers of the same trait to allow
     * @return a boolean which represents whether the condition is met or not
     */
    public static boolean hasExceededMaxOddNumbers(int[] group, int maxOddNumbers) {
        int count = 0;
        for (int number : group) {
            if (number % 2 != 0) {
                count++;
            }
        }
        return count > maxOddNumbers;
    }

    /**
     * This method checks if a group has more than the maximum allowed contiguous numbers (e.g., 23, 24, 25).
     * @param group a generated group of 6 numbers for evaluation
     * @param maxContiguousNumbers how many numbers of the same trait to allow
     * @return a boolean which represents whether the condition is met or not
     */
    public static boolean hasExceededMaxContiguousNumbers(int[] group, int maxContiguousNumbers) {
        int count = 1;
        for (int i = 1; i < group.length; i++) {
            if (group[i] == group[i - 1] + 1) {
                count++;
                if (count > maxContiguousNumbers) {
                    return true;
                }
            } else {
                count = 1;
            }
        }
        return false;
    }

    /**
     * This method checks if a group has more than the maximum allowed numbers that end with the same digit (e.g., 23, 33, 43).
     * @param group a generated group of 6 numbers for evaluation
     * @param maxSameEndingNumbers how many numbers of the same trait to allow
     * @return a boolean which represents whether the condition is met or not
     */
    public static boolean hasExceededMaxSameEndingNumbers(int[] group, int maxSameEndingNumbers) {
        for (int i = 0; i < group.length - 1; i++) {
            if (getEndingDigit(group[i]) == getEndingDigit(group[i + 1])) {
                int count = 2;
                for (int j = i + 2; j < group.length; j++) {
                    if (getEndingDigit(group[j]) == getEndingDigit(group[i])) {
                        count++;
                        if (count > maxSameEndingNumbers) {
                            return true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method checks if a group has more than the maximum allowed numbers in the same decade (e.g., 23, 24, 25).
     * @param group a generated group of 6 numbers for evaluation
     * @param maxSameDecadeNumbers how many numbers of the same trait to allow
     * @return a boolean which represents whether the condition is met or not
     */
    public static boolean hasExceededMaxSameDecadeNumbers(int[] group, int maxSameDecadeNumbers) {
        for (int i = 0; i < group.length - 1; i++) {
            if (getDecade(group[i]) == getDecade(group[i + 1])) {
                int count = 2;
                for (int j = i + 2; j < group.length; j++) {
                    if (getDecade(group[j]) == getDecade(group[i])) {
                        count++;
                        if (count > maxSameDecadeNumbers) {
                            return true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method returns the ending digit of a number.
     * @param number an int number for evaluation
     * @return int number representing the ending digit (e.g 23 will return 3)
     */
    public static int getEndingDigit(int number) {
        return number % 10;
    }

    /**
     * This method returns the decade of a number (the tens digit)
     * @param number an int number for evaluation
     * @return  int number representing the decade (e.g 23 will return 2)
     */
    public static int getDecade(int number) {
        return number / 10;
    }

}