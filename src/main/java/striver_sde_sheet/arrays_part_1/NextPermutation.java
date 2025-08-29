package striver_sde_sheet.arrays_part_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/*
* Problem: Given an array of integers, modify the array in-place to the next permutation of the array in the
* lexicographical order. If no next permutation exists, return the lexicographically smallest permutation.
*
* Leetcode Link: https://leetcode.com/problems/next-permutation/
*/
public class NextPermutation {

    /*
    * Approach: Generate all possible permutations of the array, find the given permutation in the list of permutations,
    * and return the next permutation in the list.
    *
    * Complexity Analysis
    * n = size of the input array
    * Time Complexity: O(n * n!)
    * Space Complexity: O(n * n!)
    */
    public void nextPermutationBruteForce(int[] nums) {
        final int[] input = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);
        List<int[]> perms = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        findPermutations(nums, perms, used, new int[nums.length], 0);
        int nextPermIndex = IntStream.range(0, perms.size())
                .filter(index -> Arrays.equals(perms.get(index), input))
                .findFirst()
                .orElse(-1);
        nextPermIndex = nextPermIndex == perms.size() - 1 ? 0 : ++nextPermIndex;
        System.arraycopy(perms.get(nextPermIndex), 0, nums, 0, nums.length);
    }

    /*
    * Approach: Find the first index from right where elements are in increasing order (breaking point). If all the
    * elements are in non-increasing order there is no next permutation and we just reverse the given array. If we find
    * the breaking point we swap the element at breaking point with the next greater element to the right. After that,
    * we reverse the right half of the array after breaking point.
    *
    * Complexity Analysis
    * n = size of the input array
    * Time Complexity: O(n)
    * Space Complexity: O(n) considering the result list, O(1) otherwise
    */
    public void nextPermutationOptimal(int[] nums) {
        int breakPointIndex = -1;
        for (int index = nums.length - 1; index > 0; index--) {
            if (nums[index - 1] < nums[index]) {
                breakPointIndex = index - 1;
                break;
            }
        }
        if (breakPointIndex == -1) {
            reverseArray(nums, 0);
            return;
        }

        int swapIndex = breakPointIndex + 1;
        for (int index = breakPointIndex + 1; index < nums.length; index++) {
            if (nums[index] > nums[breakPointIndex] && nums[swapIndex] >= nums[index]) {
                swapIndex = index;
            }
        }
        swapNums(nums, breakPointIndex, swapIndex);
        reverseArray(nums, breakPointIndex + 1);
    }

    private void findPermutations(int[] nums, List<int[]> perms, boolean[] used, int[] perm, int permIndex) {
        if (permIndex == perm.length) {
            perms.add(Arrays.copyOf(perm, perm.length));
            return;
        }

        for (int index = 0; index < nums.length; index++) {
            if (used[index]) {
                continue;
            }
            if (index > 0 && nums[index] == nums[index - 1] && !used[index - 1]) {
                continue;
            }

            used[index] = true;
            perm[permIndex] = nums[index];
            findPermutations(nums, perms, used, perm, permIndex + 1);
            used[index] = false;
        }
    }

    private void swapNums(int[] nums, int index1, int index2) {
        int tmpValue = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmpValue;
    }

    private void reverseArray(int[] nums, int startIndex) {
        int midIndex = (startIndex + nums.length) / 2;
        for (int offset = 0; offset < midIndex - startIndex; offset++) {
            swapNums(nums, startIndex + offset, nums.length - 1 - offset);
        }
    }

    private void printArray(int[] nums) {
        Arrays.stream(nums).forEach(num -> System.out.print(num + " "));
        System.out.println();
    }

    public static void main(String[] args) {
        int[] input1 = {5, 1, 4, 6, 3, 2};
        int[] input2 = {2, 3, 1, 3, 3};

        NextPermutation solution = new NextPermutation();
        solution.nextPermutationBruteForce(input1);
        solution.nextPermutationOptimal(input2);
    }
}
