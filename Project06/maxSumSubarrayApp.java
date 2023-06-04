package Project06;

public class maxSumSubarrayApp {

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = 0;
        int globalMax = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            maxSum += arr[i];

            if (maxSum > globalMax) {
                globalMax = maxSum;
            }

            if (maxSum < 0) {
                maxSum = 0;
            }
        }

        System.out.println(globalMax);
    }
}
