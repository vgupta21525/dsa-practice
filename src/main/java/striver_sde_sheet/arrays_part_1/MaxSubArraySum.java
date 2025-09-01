package striver_sde_sheet.arrays_part_1;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
* Problem: Given an array of integers, find the sub array with the maximum sum of elements and then return that sum.
* Variation: Print the sub array with the maximum sum.
*
* Leetcode Link: https://leetcode.com/problems/maximum-subarray/
*/
public class MaxSubArraySum {

    /*
    * Approach: Iterate over all possible start and end points of a sub array and then calculate the sum of the sub
    * array with given start and end points. Return the maximum sum obtained at the end of iteration.
    *
    * Complexity Analysis
    * n = size of the input array
    * Time Complexity: O(n ^ 3)
    * Space Complexity: O(1)
    */
    public int maxSubArrayBruteForce(int[] nums) {
        int maxSubArraySum = Integer.MIN_VALUE;
        for (int leftIndex = 0; leftIndex < nums.length; leftIndex++) {
            for (int rightIndex = leftIndex; rightIndex < nums.length; rightIndex++) {
                int subArraySum = 0;
                for (int index = leftIndex; index <= rightIndex; index++) {
                    subArraySum += nums[index];
                }

                maxSubArraySum = Math.max(maxSubArraySum, subArraySum);
            }
        }

        return maxSubArraySum;
    }

    /*
    * Approach: We can observe that to get the sum of the current sub array [leftIndex...rightIndex], we need to add
    * the current element (rightIndex) to the sum of previous sub array [leftIndex...rightIndex - 1]. We can use this
    * fact to eliminate the innermost loop in brute force approach.
    *
    * Complexity Analysis
    * n = size of the input array
    * Time Complexity: O(n ^ 2)
    * Space Complexity: O(1)
    */
    public int maxSubArrayBetter(int[] nums) {
        int maxSubArraySum = Integer.MIN_VALUE;
        for (int leftIndex = 0; leftIndex < nums.length; leftIndex++) {
            int subArraySum = 0;
            for (int rightIndex = leftIndex; rightIndex < nums.length; rightIndex++) {
                subArraySum += nums[rightIndex];
                maxSubArraySum = Math.max(maxSubArraySum, subArraySum);
            }
        }

        return maxSubArraySum;
    }

    /*
    * Approach: We can observe that as soon as the sum of a sub array becomes negative, we can discard that sub array
    * and start fresh from the next index. This is because continuing with the current sub array will always reduce the
    * answer. Using this approach, we can eliminate another loop from the brute force approach.
    *
    * Complexity Analysis
     * n = size of the input array
     * Time Complexity: O(n)
     * Space Complexity: O(1)
    */
    public int maxSubArrayOptimal(int[] nums) {
        int maxSubArraySum = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int num : nums) {
            currentSum += num;
            maxSubArraySum = Math.max(currentSum, maxSubArraySum);
            if (currentSum < 0) {
                currentSum = 0;
            }
        }

        return maxSubArraySum;
    }

    /*
    * Variation: Print the sub array with the maximum sum.
    * Approach: We will use the optimal approach and store the starting and ending indices of the sub array.
    *
    * Complexity Analysis
    * n = size of the input array
    * Time Complexity: O(n)
    * Space Complexity: O(1)
    */
    public void maxSubArrayPrint(int[] nums) {
        int maxSubArraySum = Integer.MIN_VALUE;
        int startIndex = 0;
        int endIndex = 0;
        int currentSum = 0;
        for (int index = 0; index < nums.length; index++) {
            currentSum += nums[index];
            if (currentSum > maxSubArraySum) {
                maxSubArraySum = currentSum;
                endIndex = index + 1;
            }
            if (currentSum < 0) {
                currentSum = 0;
                startIndex = index + 1;
            }
        }
        printArray(nums, startIndex, endIndex);
    }

    private void printArray(int[] nums, int startIndex, int endIndex) {
        IntStream.range(startIndex, endIndex).forEach(index -> System.out.println(nums[index] + " "));
        System.out.println();
    }

    public static void main(String[] args) {
        int[] input1 = {1};
        int[] input2 = {5,4,-1,7,8};
        int[] input3 = {-2,1,-3,4,-1,2,1,-5,4};

        MaxSubArraySum solution = new MaxSubArraySum();
        int output1 = solution.maxSubArrayBruteForce(input1);
        int output2 = solution.maxSubArrayBetter(input2);
        int output3 = solution.maxSubArrayOptimal(input3);
        solution.maxSubArrayPrint(input3);
    }

}
