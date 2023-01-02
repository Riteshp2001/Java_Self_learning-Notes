package DP.KnapSack;


public class _KnapSack_SubsetSum {
    //Recursion
    public static boolean subSetSum_Rec(int[] arr, int sum, int n) {
        if (sum > 0 && n == 0) return false;
        if (sum == 0) return true;
        if (arr[n] > sum) {
            return subSetSum_Rec(arr, sum, n - 1);
        }
        return subSetSum_Rec(arr, sum - arr[n], n - 1) || subSetSum_Rec(arr, sum, n - 1);
    }

    //Recursion + Memoization
    static boolean[][] t = new boolean[10001][10001];

    public static boolean subSetSum_RecMemo(int[] arr, int sum, int n) {
        if (sum > 0 || n == 0) return false;
        if (arr[n] > sum) return false;
        if (t[n][sum]) return t[n][sum];
        else {
            if (subSetSum_RecMemo(arr, sum - arr[n], n - 1) || subSetSum_RecMemo(arr, sum, n - 1)) {
                return t[n][sum] = true;
            } else {
                return t[n][sum] = false;
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 1, 7, 5, 9};
        int sum = 8;
        System.out.println(subSetSum_RecMemo(arr, sum, arr.length - 1));
    }
}
