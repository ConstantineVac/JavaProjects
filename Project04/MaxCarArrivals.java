package Project04;

import java.util.Arrays;
import java.util.Comparator;

public class MaxCarArrivals {

    public static void main(String[] args) {
        int[][] arr = {{1012, 1136}, {1317, 1417}, {1015, 1020}};
        int[][] transformed;

        transformed = transformArray(arr);
        sortByTime(transformed);

        for (int[] row : transformed) {
            System.out.println(row[0] + " ");
            System.out.println(row[1]);
        }

        System.out.println("Max Concurrent Cars in Garage: " + getMaxConcurrentCars(transformed));
    }

    /**
     * Transforms the initial 2D Array into a x2 2D array. One row for departure and one for arrival.
     * The second column represents arrivals and departures with 1: arrival and 0: departure.
     * @param arr a 2D array containing time of arrival and time of departure
     * @return a x2 2D array.
     */
    public static int[][] transformArray (int[][] arr) {
        int[][] transformed = new int[arr.length*2][2];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                transformed[i*2][0] = arr[i][0];
                transformed[i*2][1] = 1;
                transformed[i*2+1][0] = arr[i][1];
                transformed[i*2+1][1] = 0;
            }
        }
        return transformed;
    }

    /**
     * Sorts the transformed array in ASC order
     * by the time of car arrival or departure.
     * Utilizes Comparator for sorting the 1st Column.
     *
     * @param arr the transformed arr.
     */
    public static void sortByTime(int[][] arr) {
        Arrays.sort(arr, Comparator.comparing((int[] a) -> a[0]));
    }

    /**
     * Returns number of cars parked
     * during the same time interval.
     *
     * @param arr source array with arrivals and departures.
     * @return concurrent parked cars.
     */
    public static int getMaxConcurrentCars(int[][] arr){
        int count = 0;
        int maxCount = 0;

        for (int[] ints : arr){
            if (ints[1] == 1){
                count++;
                if (count > maxCount) maxCount = count;
            } else {
                count --;
            }
        }

        return maxCount;
    }
}
