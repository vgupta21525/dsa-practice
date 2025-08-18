package striver_sde_sheet.arrays_part_1;

import java.util.ArrayList;
import java.util.List;

/*
* Problem: I implemented two variations of this problem. I will explain both of them below.
* Variation 1: Given two integers row, and column, return the element at that position in the Pascal's Triangle.
* Variation 2: Given an integer numRows, return a list of lists representing the first numRows rows of the
* Pascal's Triangle.
*
* Leetcode Link: https://leetcode.com/problems/pascals-triangle/
*/
public class PascalsTriangle {

    /*
    * Approach (Version 1): Using the fact that the Pascal's triangle can be created using the combination formula
    * nCr, we can just calculate this value for the given input (n = row - 1, r = column - 1).
    *
    * Complexity Analysis
    * n = column
    * Time Complexity: O(n)
    * Space Complexity: O(1)
    */
    public int pascalTriangleElement(int row, int column) {
        long element = 1;
        for (int num = 1; num < column; num++) {
            element *= (row - num);
            element /= row;
        }

        return (int) element;
    }

    /*
    * Approach (Version 2): Iterate through all the rows from 1 to numRows and create the current row based on the
    * previous row from the triangle. Add a base condition for first row to avoid Runtime Error.
    *
    * Complexity Analysis
    * n = numRows
    * Time Complexity: O(n ^ 2)
    * Space Complexity: O(n ^ 2) considering the result list, O(1) otherwise
    */
    public List<List<Integer>> generatePascalTriangle(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int row = 1; row <= numRows; row++) {
            List<Integer> currentRow = new ArrayList<>();
            currentRow.add(1);
            if (triangle.isEmpty()) {
                triangle.add(currentRow);
                continue;
            }

            List<Integer> prevRow = triangle.get(row - 2);
            for (int prevRowIndex = 1; prevRowIndex < prevRow.size(); prevRowIndex++) {
                currentRow.add(prevRow.get(prevRowIndex - 1) + prevRow.get(prevRowIndex));
            }

            currentRow.add(1);
            triangle.add(currentRow);
        }

        return triangle;
    }

}
