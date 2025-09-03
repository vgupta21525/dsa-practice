package striver_sde_sheet.arrays_part_2;

import java.util.*;

/*
* Problem: Given an array of arrays, where each nested array denotes the starting and ending time of an interval,
* return an array of all the non-overlapping intervals in the input.
*
* Leetcode Link: https://leetcode.com/problems/merge-intervals/
*/
public class MergeIntervals {

    /*
    * Approach: Sort the intervals in increasing order of the starting time and then traverse through the sorted
    * intervals to find overlap between the start time of current interval and end time of previous interval.
    *
    * Complexity Analysis
    * n = number of the intervals
    * Time Complexity: O(n * log n + n)
    * Space Complexity: O(n ^ 2) considering the result array, O(1) otherwise
    */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> lastInterval = new ArrayList<>(Collections.nCopies(2, 0));

        for (int[] interval: intervals) {

            if (result.isEmpty() || lastInterval.get(1) < interval[0]) {
                lastInterval.set(0, interval[0]);
                lastInterval.set(1, interval[1]);
                result.add(List.copyOf(lastInterval));
            }
            else {
                lastInterval.set(1, Math.max(lastInterval.get(1), interval[1]));
                result.set(result.size() - 1, List.copyOf(lastInterval));
            }
        }

        return result.stream()
                .map(interval -> interval.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
    }

    public static void main(String[] args) {
        int[][] input = {
                {1, 3},
                {8, 10},
                {2, 6},
                {15, 18}
        };

        MergeIntervals solution = new MergeIntervals();
        int[][] output = solution.merge(input);
    }

}
