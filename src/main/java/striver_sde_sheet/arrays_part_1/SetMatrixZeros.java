package striver_sde_sheet.arrays_part_1;

import java.util.HashSet;
import java.util.Set;

/*
* Problem: Given a 2D integer matrix, update the matrix such that all the elements of a row should be 0 if any
* element in row is 0 and all elements of a column should be 0 if any element in column is 0.
*
* Leetcode Link: https://leetcode.com/problems/set-matrix-zeroes/
*/
public class SetMatrixZeros {

    /*
    * Approach: Create two sets for storing rows and columns indices where a zero appears. Iterate over all
    * the elements in the matrix and update the two sets accordingly. After that, iterate over the two sets
    * and update the original matrix accordingly.
    *
    * Complexity Analysis
    * m = Number of rows
    * n = Number of columns
    * Time Complexity: O(m * n)
    * Space Complexity: O(m + n)
    */
    public void setZerosBruteForce(int[][] matrix) {
        final int rows = matrix.length;
        final int cols = matrix[0].length;
        Set<Integer> rowsWithZero = new HashSet<>();
        Set<Integer> colsWithZero = new HashSet<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 0) {
                    rowsWithZero.add(row);
                    colsWithZero.add(col);
                }
            }
        }

        for (int row: rowsWithZero) {
            fillRow(matrix, row);
        }
        for (int col: colsWithZero) {
            fillCol(matrix, col);
        }
    }

    /*
    * Approach: Instead of using separate sets for tracking the rows and columns with zero, use the first row
    * and column of the matrix. Use the first row to track columns with zero and first column to track rows
    * with zero. Use separate boolean variables to track zeros in the first row/column. Iterate over the matrix
    * and update the matrix accordingly. After that, iterate over the first row and column and update the rest
    * of the matrix. Finally, update the first row and column based on the boolean variables.
    *
    * Complexity Analysis
    * m = Number of rows
    * n = Number of columns
    * Time Complexity: O(m * n)
    * Space Complexity: O(1)
    */
    public void setZeroesOptimal(int[][] matrix) {
        final int rows = matrix.length;
        final int cols = matrix[0].length;
        boolean zeroInFirstRow = false;
        boolean zeroInFirstCol = false;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                    if (row == 0) {
                        zeroInFirstRow = true;
                    }
                    if (col == 0) {
                        zeroInFirstCol = true;
                    }
                }
            }
        }

        for (int row = 1; row < rows; row++) {
            if (matrix[row][0] == 0) {
                fillRow(matrix, row);
            }
        }
        for (int col = 1; col < cols; col++) {
            if (matrix[0][col] == 0) {
                fillCol(matrix, col);
            }
        }
        if (zeroInFirstRow) {
            fillRow(matrix, 0);
        }
        if (zeroInFirstCol) {
            fillCol(matrix, 0);
        }
    }

    private void fillRow(int[][] matrix, int row) {
        final int cols = matrix[row].length;
        for (int col = 0; col < cols; col++) {
            matrix[row][col] = 0;
        }
    }

    private void fillCol(int[][] matrix, int col) {
        final int rows = matrix.length;
        for (int row = 0; row < rows; row++) {
            matrix[row][col] = 0;
        }
    }

    public static void main(String[] args) {
        int[][] input1 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] input2 = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};

        SetMatrixZeros solution = new SetMatrixZeros();
        solution.setZerosBruteForce(input1);
        solution.setZeroesOptimal(input2);
    }

}
