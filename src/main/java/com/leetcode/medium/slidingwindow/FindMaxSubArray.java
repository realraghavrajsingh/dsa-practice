package com.leetcode.medium.slidingwindow;

import java.util.ArrayList;

public class FindMaxSubArray {

    // Function to find the subarray with the given sum
    static ArrayList<Integer> findSubarrayWithSum(int[] arr, int n, int targetSum) {
        int start = 0;
        int currentSum = 0;
        int end = 0;
        ArrayList<Integer> result = new ArrayList<>();

        while (end < n) {
            currentSum += arr[end];

            while (currentSum > targetSum && start < end) {
                currentSum -= arr[start];
                start++;
            }

            if (currentSum == targetSum) {
                result.add(start + 1);
                result.add(end + 1);
                return result;
            }
            end++;
        }

        result.add(-1);
        return result;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 7, 5 };
        int sum = 12;
        int n = arr.length;
        ArrayList<Integer> res = findSubarrayWithSum(arr, n, sum);
        if (res.get(0) == -1) {
            System.out.println("No subarray found");
        } else {
            System.out.println("Sum found between indexes " + res.get(0) + " and " + res.get(1));
        }
    }
}
