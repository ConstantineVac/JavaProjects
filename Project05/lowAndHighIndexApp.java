package Project05;

import java.util.Scanner;

public class lowAndHighIndexApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exists = false;
        int key;
        int[] arr = {0, 1, 4, 4, 4, 6, 7, 8, 8, 8, 8, 8};

        System.out.println("Please enter a number (int: 1-9):");
        key = sc.nextInt();
        exists = isKeyExistant(key, arr);

        if (exists == true) {
            int[] lowHighIndex = getLowAndHighIndexOf(arr, key);
            System.out.println("Low Index: " + lowHighIndex[0]);
            System.out.println("High Index: " + lowHighIndex[1]);
        } else {
            System.out.println("Key does not exist in the array.");
        }
    }

    /**
     * Checks if the given key is part of the given array
     * @param key   user int that needs to be found inside the array
     * @param arr   the array we will search the key for.
     * @return      a boolean value that confirms whether the key exists or not.
     */
    public static boolean isKeyExistant (int key, int[] arr) {
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            if (key == arr[i]) {
                found = true;
               break;
            } else {
                found = false;
            }
        }
        return found;
    }

    /**
     * A method that searches the array for the key and finds the lowest position that the key exists and the highest
     * @param arr   the source array to search
     * @param key   key value to search for
     * @return      an array of 2 positions, low and high
     */
    public static int[] getLowAndHighIndexOf(int[] arr, int key) {
        int[] lowHigh = new int[2];
        int lowIndex = -1;
        int highIndex = -1;

        for (int i = 0; i < arr.length; i++) {
            if (key == arr[i]) {
                if (lowIndex == -1) {
                    lowIndex = i;
                }
                highIndex = i;
            }
        }

        lowHigh[0] = lowIndex;
        lowHigh[1] = highIndex;

        return lowHigh;
    }
}
