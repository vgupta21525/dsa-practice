package striver_sde_sheet.arrays_part_2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
* Problem: Given an array of integers of size n + 1 with array values ranging from 1 to n. There is one number that is
* duplicated one or more times in the array. Find the duplicate number.
*
* Leetcode Link: https://leetcode.com/problems/find-the-duplicate-number/
*/
public class FindDuplicateNumber {

    /*
    * Approach: We will sort the array and then traverse over the sorted array to find the duplicate number.
    *
    * Complexity Analysis
    * n = size of the input array
    * Time Complexity: O(n * log n + n)
    * Space Complexity: O(1)
    */
    public int findDuplicateBruteForce(int[] nums) {
        Arrays.sort(nums);
        for (int index = 1; index < nums.length; index++) {
            if (nums[index] == nums[index - 1]) {
                return nums[index];
            }
        }

        return -1;
    }

    /*
    * Approach: We will traverse over the array and keep adding all the elements to a hash set. As soon as we find an
    * element that exists in the set, we return that element as it is the duplicate one.
    *
    * Complexity Analysis
    * n = size of the input array
    * Time Complexity: O(n)
    * Space Complexity: O(n)
    */
    public int findDuplicateBetter(int[] nums) {
        Set<Integer> arrayValues = new HashSet<>();
        for (int num: nums) {
            if (arrayValues.contains(num)) {
                return num;
            }
            arrayValues.add(num);
        }

        return -1;
    }

    /*
    * Approach: We will try to sort the array in-place by assigning i to the ith index in the array. If we encounter
    * the number at ith index is already i, we return it as it is the duplicate number.
    *
    * Complexity Analysis
    * n = size of the input array
    * Time Complexity: O(n) as we go to an index at most 2 times
    * Space Complexity: O(1)
    */
    public int findDuplicateOptimal1(int[] nums) {
        int index = 0;
        while (index < nums.length) {
            if (nums[index] == index + 1) {
                index++;
            }
            else if (nums[nums[index] - 1] == nums[index]) {
                return nums[index];
            }
            else {
                swap(nums, index, nums[index] - 1);
            }
        }

        return -1;
    }

    /*
     * Approach: We will use the slow and fast pointer technique to find the cycle in the current array. When we find
     * the cycle, we will make the slow pointer point to the first element, and now again move the pointers, this time
     * one element at a time. When we find they are same, we return the element. This works because the start of the
     * cycle is the duplicate element and both the pointers will point to the start at the end of second loop.
     *
     * Complexity Analysis
     * n = size of the input array
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int findDuplicateOptimal2(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    private void swap(int[] array, int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    public static void main(String[] args) {
        int[] input = {1, 3, 4, 2, 2};
        FindDuplicateNumber solution = new FindDuplicateNumber();
        System.out.println(solution.findDuplicateBruteForce(input));
        System.out.println(solution.findDuplicateBetter(input));
        System.out.println(solution.findDuplicateOptimal1(input));
        System.out.println(solution.findDuplicateOptimal2(input));
    }

}
