package DP.KnapSack;

import java.util.Arrays;

public class _01KnapSack {
    //Normal Recursion -> TC(2^N)
    static int KnapSack(int W, int wt[], int val[], int n) {
        // Base Case
        if (n == 0 || W == 0)
            return 0;

        // If weight of the nth item is more than Knapsack capacity W, then this item cannot be included in the optimal solution
        if (wt[n - 1] > W)
            return KnapSack(W, wt, val, n - 1);

            // Return the maximum of two cases:
            // (1) nth item included
            // (2) not included
        else
            return Math.max(val[n - 1] + KnapSack(W - wt[n - 1], wt, val, n - 1), KnapSack(W, wt, val, n - 1));
    }

    //Recursion + Memoization -> TC(N^2)
    static final int[][] t = new int[1000][1000];//Initializing Memory Array

    static void fillArray(int[][] t) {//filling every position in array with -1
        for (int i = 0; i < t.length; i++)
            Arrays.fill(t[i], -1);
    }

    public static int KnapSack_Memoization_Recursion(int W, int wt[], int val[], int n) {
        if (W == 0 || n == 0) return 0;

        if (t[n][W] != Integer.MIN_VALUE) return t[n][W];

        if (wt[n] > W) return t[n][W] = KnapSack_Memoization_Recursion(W, wt, val, n - 1);

        return t[n][W] = Math.max(val[n] + KnapSack_Memoization_Recursion(W - wt[n - 1], wt, val, n - 1), KnapSack_Memoization_Recursion(W, wt, val, n - 1));
    }

    //Memoization -> Top-Down Approach === True DP problem
    //TC(N^2)
    static int KnapSack_Memoization(int W, int wt[], int val[], int n) {
        int i, w;
        int[][] K = new int[n + 1][W + 1];

        //building table K[][] in bottom up manner.
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                //base case
                if (i == 0 || w == 0)
                    K[i][w] = 0;

                    //if weight of current item is more than Knapsack capacity W
                    //then this item cannot be included in the optimal solution.
                else if (wt[i - 1] <= w)
                    K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                    //else updating K[i][w] as K[i-1][w].
                else
                    K[i][w] = K[i - 1][w];
            }
        }
        //returning the result.
        return K[n][W];
    }

    public static void main(String[] args) {
        int[] wt = {1, 2, 3};
        int[] val = {4, 5, 1};
        int W = 3;
        fillArray(t);
        System.out.println(KnapSack_Memoization_Recursion(W, val, wt, wt.length - 1));
    }
}
