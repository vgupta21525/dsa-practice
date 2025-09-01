package striver_sde_sheet.arrays_part_1;

/*
* Problem: Given an array of integers where each element denotes the price of a stock on a particular day, find the
* maximum profit that can be obtained by buying a stock on a day and selling it on a future day.
*
* Leetcode Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
*/
public class StockBuyAndSell {

    /*
    * Approach: We can consider every possible buying and selling price combination and then find the maximum profit
    * that can be obtained from all the combinations.
    *
    * Complexity Analysis
    * n = size of the input array
    * Time Complexity: O(n ^ 2)
    * Space Complexity: O(1)
    */
    public int maxProfitBruteForce(int[] prices) {
        int maxProfit = 0;
        for (int buyingIndex = 0; buyingIndex < prices.length; buyingIndex++) {
            for (int sellingIndex = buyingIndex + 1; sellingIndex < prices.length; sellingIndex++) {
                maxProfit = Math.max(maxProfit, prices[sellingIndex] - prices[buyingIndex]);
            }
        }

        return maxProfit;
    }

    /*
    * Approach: Traverse the array and keep track of the lowest price found so far. Then updated the maximum profit
    * based on the lowest price so far and the current selling price.
    *
    * Complexity Analysis
    * n = size of the input array
    * Time Complexity: O(n)
    * Space Complexity: O(1)
    */
    public int maxProfitOptimal(int[] prices) {
        int buyingIndex = 0;
        int sellingIndex = 0;
        int maxProfit = 0;
        while (sellingIndex < prices.length) {
            if (prices[buyingIndex] > prices[sellingIndex]) {
                buyingIndex = sellingIndex;
            }
            maxProfit = Math.max(maxProfit, prices[sellingIndex] - prices[buyingIndex]);
            sellingIndex++;
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] input1 = {7,6,4,3,1};
        int[] input2 = {7,1,5,3,6,4};
        StockBuyAndSell solution = new StockBuyAndSell();

        int output1 = solution.maxProfitBruteForce(input1);
        int output2 = solution.maxProfitOptimal(input2);
    }
}
