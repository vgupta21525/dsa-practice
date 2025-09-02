package striver_sde_sheet.arrays_part_2;

/*
* Problem: Given an n x n 2D integer matrix, rotate the matrix in a clockwise manner.
*
* Leetcode Link: https://leetcode.com/problems/rotate-image/
*/
public class RotateMatrix {

    /*
    * Approach: Create another 2D matrix of the same size and iteratively populate the new matrix from the original
    * input in a rotated fashion.
    *
    * Complexity Analysis
    * n = number of rows/columns in the matrix
    * Time Complexity: O(n ^ 2)
    * Space Complexity: O(n ^ 2)
    */
    public void rotateBruteForce(int[][] matrix) {
        int[][] rotatedMatrix = new int[matrix.length][matrix.length];
        for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
            int targetColIndex = matrix.length - rowIndex - 1;
            for (int colIndex = 0; colIndex < matrix.length; colIndex++) {
                rotatedMatrix[colIndex][targetColIndex] = matrix[rowIndex][colIndex];
            }
        }

        System.arraycopy(rotatedMatrix, 0, matrix, 0, matrix.length);
    }

    /*
    * Approach: We can observe that the rotated matrix can be obtained by combining two operations - first we take
    * the transpose of the matrix, and then we reverse each individual row.
    *
    * Complexity Analysis
    * n = number of rows/columns in the matrix
    * Time Complexity: O(n ^ 2)
    * Space Complexity: O(n ^ 2) if result matrix to be allocated, O(1) otherwise
    */
    public void rotateOptimal(int[][] matrix) {
        transposeMatrix(matrix);
        for (int[] row : matrix) {
            reverseArray(row);
        }
    }

    private void transposeMatrix(int[][] matrix) {
        for (int rowIndex = 0; rowIndex < matrix.length - 1; rowIndex++) {
            for (int colIndex = rowIndex + 1; colIndex < matrix.length; colIndex++) {
                swapNums(matrix[rowIndex], colIndex, matrix[colIndex], rowIndex);
            }
        }
    }

    private void reverseArray(int[] array) {
        int midIndex = array.length / 2;
        for (int index = 0; index < midIndex; index++) {
            swapNums(array, index, array, array.length - 1 - index);
        }
    }

    private void swapNums(int[] src, int srcIndex, int[] target, int targetIndex) {
        int tmp = src[srcIndex];
        src[srcIndex] = target[targetIndex];
        target[targetIndex] = tmp;
    }

    public static void main(String[] args) {
        int[][] input1 = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        int[][] input2 = {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}
        };

        RotateMatrix solution = new RotateMatrix();
        solution.rotateBruteForce(input1);
        solution.rotateOptimal(input2);
    }
}
