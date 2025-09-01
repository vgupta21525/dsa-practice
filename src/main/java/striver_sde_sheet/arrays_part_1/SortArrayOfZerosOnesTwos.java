package striver_sde_sheet.arrays_part_1;

import java.util.Arrays;

/*
* Problem: Given an array of integers containing only three distinct values - 0, 1, and 2, sort the array in
* increasing order.
*
* Leetcode Link: https://leetcode.com/problems/sort-colors/
*/
public class SortArrayOfZerosOnesTwos {

    /*
    * Approach: We can use the in-built sort function to sort the array.
    *
    * Complexity Analysis
    * n = size of the input array
    * Time Complexity: O(n * log n)
    * Space Complexity: O(1)
    */
    public void sortArrayBruteForce(int[] nums) {
        Arrays.sort(nums);
    }

    /*
    * Approach: We cna store the frequency of 0, 1, and 2 in the input array and then during a second traversal,
    * we can update the array according to the frequencies.
    *
    * Complexity Analysis
    * n = size of the input array
    * Time Complexity: O(n)
    * Space Complexity: O(1)
    */
    public void sortArrayBetter(int[] nums) {
        int zeros = 0;
        int ones = 0;
        for (int num: nums) {
            switch (num) {
                case 0:
                    zeros++;
                    break;
                case 1:
                    ones++;
                    break;
            }
        }

        for (int index = 0; index < zeros; index++) {
            nums[index] = 0;
        }
        for (int index = zeros; index < (ones + zeros); index++) {
            nums[index] = 1;
        }
        for (int index = ones + zeros; index < nums.length; index++) {
            nums[index] = 2;
        }
    }

    /*
    * Approach: We can use two pointers to point to the index where the next 0 and 2 element should go. That means,
    * anything between the two pointers is the unsorted section. We swap elements from the unsorted section to either
    * the beginning or the end and that leaves the middle portion to have only 1.
    *
    * Complexity Analysis
    * n = size of the input array
    * Time Complexity: O(n)
    * Space Complexity: O(1)
    */
    public void sortArrayOptimal(int[] nums) {
        int nextZeroIndex = 0;
        int nextTwoIndex = nums.length - 1;
        int currentIndex = 0;
        while (currentIndex <= nextTwoIndex) {
            if (nums[currentIndex] == 0) {
                swapNums(nums, currentIndex, nextZeroIndex);
                nextZeroIndex++;
                currentIndex++;
            }
            else if (nums[currentIndex] == 2) {
                swapNums(nums, currentIndex, nextTwoIndex);
                nextTwoIndex--;
            }
            else {
                currentIndex++;
            }
        }
    }

    private void swapNums(int[] nums, int index1, int index2) {
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }

    public static void main(String[] args) {
        int[] input1 = {2, 0, 1};
        int[] input2 = {0, 2, 1, 2, 1, 2, 0, 1, 2, 0};
        int[] input3 = {1, 2, 0, 2, 2, 1, 0, 0, 2, 1, 1, 0, 2, 0};

        SortArrayOfZerosOnesTwos solution = new SortArrayOfZerosOnesTwos();
        solution.sortArrayBruteForce(input1);
        solution.sortArrayBetter(input2);
        solution.sortArrayOptimal(input3);
    }
}
