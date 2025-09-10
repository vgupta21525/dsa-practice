package striver_sde_sheet.arrays_part_2;

import java.util.Arrays;

/*
* Problem: Given two sorted arrays merge the two arrays together into a single sorted array.
* Variation 1: You will be given the two arrays and their lengths, one array would have the capacity to store elements
* from both the arrays. You will have to store the merged sorted array in the first input array.
* Variation 2: You will be given two arrays, and you will have to split the final sorted array into the two inputs - one
* array would store the first half and the other would store the second half.
*
* Leetcode Link: https://leetcode.com/problems/merge-sorted-array/
*/
public class MergeTwoSortedArrays {

    /*
    * Approach: We will create a third array with the size of both of the arrays. We will iterate over both the arrays
    * using two pointers and assign the element with the lower value to the result. Finally, we will copy the result
    * into the input array. This approach works for both the variations.
    *
    * Complexity Analysis
    * m = size of the first array
    * n = size of the second array
    * Time Complexity: O(m + n)
    * Space Complexity: O(m + n)
    */
    public void mergeBruteForce(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m + n];
        int index1 = 0;
        int index2 = 0;

        while (index1 < m && index2 < n) {
            if (nums1[index1] < nums2[index2]) {
                result[index1 + index2] = nums1[index1];
                index1++;
            }
            else {
                result[index1 + index2] = nums2[index2];
                index2++;
            }
        }
        while (index1 < m) {
            result[index1 + index2] = nums1[index1];
            index1++;
        }
        while (index2 < n) {
            result[index1 + index2] = nums2[index2];
            index2++;
        }

        System.arraycopy(result, 0, nums1, 0, m + n);
    }

    /*
    * Approach: We will merge the arrays from the last element and keep assigning the larger of the two elements
    * to the back of the first array. This approach works only for variation 1.
    *
    * Complexity Analysis
    * m = size of the first array
    * n = size of the second array
    * Time Complexity: O(m + n)
    * Space Complexity: O(1)
    */
    public void mergeOptimalVariation1(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1;
        int index2 = n - 1;

        while (index1 >= 0 && index2 >= 0) {
            if (nums1[index1] > nums2[index2]) {
                nums1[index1 + index2 + 1] = nums1[index1];
                index1--;
            }
            else {
                nums1[index1 + index2 + 1] = nums2[index2];
                index2--;
            }
        }
        while (index1 >= 0) {
            nums1[index1 + index2 + 1] = nums1[index1];
            index1--;
        }
        while (index2 >= 0) {
            nums1[index1 + index2 + 1] = nums2[index2];
            index2--;
        }
    }

    /*
    * Approach: We will use two pointers one moving from end of first array to start, and the other moving from start
    * of second array to end. If the last element of first array is greater than the first element of second array, we
    * will swap them. This will put all the smaller elements in first array and the larger elements in the second array.
    * At end, we will sort both the arrays. This approach works for variation 2.
    *
    * Complexity Analysis
    * m = size of the first array
    * n = size of the second array
    * Time Complexity: O(min(m, n) + (m * log m) + (n * log n))
    * Space Complexity: O(1)
    */
    public void mergeOptimalVariation2(int[] nums1, int[] nums2) {
        int left = nums1.length - 1;
        int right = 0;

        while (left >= 0 && right < nums2.length) {
            if (nums1[left] > nums2[right]) {
                swapNums(nums1, left, nums2, right);
                left--;
                right++;
            }
            else {
                break;
            }
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
    }

    /*
    * Approach: We will consider the two arrays to be a single continuous array and use shell sort on the entire
    * combined array to merge the two arrays. For the shell sort algorithm, we will use the initial gap as the half
    * of the combined array size and then keep on dividing it by 2 in each iteration
    *
    * Complexity Analysis
    * n = size of the first array
    * m = size of the second array
    * Time Complexity: O((n + m) * log (n + m))
    * Space Complexity: O(1)
    */
    public void mergeOptimal(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int totalSize = m + n;
        int gap = (totalSize / 2) + (totalSize % 2);
        while (gap > 0) {
            int left = 0;
            int right = left + gap;
            while (right < totalSize) {
                if (left < m && right < m) {
                    swapNums(nums1, left, nums1, right);
                }
                else if (left < m) {
                    swapNums(nums1, left, nums2, right - m);
                }
                else {
                    swapNums(nums1, left - m, nums2, right - m);
                }
                left++;
                right++;
            }

            if (gap == 1) {
                break;
            }
            gap = (gap / 2) + (gap % 2);
        }
    }

    private void swapNums(int[] arr1, int index1, int[] arr2, int index2) {
        int tmp = arr1[index1];
        arr1[index1] = arr2[index2];
        arr2[index2] = tmp;
    }

    public static void main(String[] args) {
        int[] input1Nums1 = {1, 4, 8, 10, 0, 0, 0};
        int[] input1Nums2 = {2, 3, 9};
        int[] input2Nums1 = {1, 4, 8, 10};
        int[] input2Nums2 = {2, 3, 9};
        MergeTwoSortedArrays solution = new MergeTwoSortedArrays();
        solution.mergeBruteForce(Arrays.copyOf(input1Nums1, 7), 4, Arrays.copyOf(input1Nums2, 3), 3);
        solution.mergeOptimalVariation1(Arrays.copyOf(input1Nums1, 7), 4, Arrays.copyOf(input1Nums2, 3), 3);
        solution.mergeOptimalVariation2(Arrays.copyOf(input2Nums1, 4), Arrays.copyOf(input2Nums2, 3));
        solution.mergeOptimal(Arrays.copyOf(input2Nums1, 4), Arrays.copyOf(input2Nums2, 3));
    }
}
