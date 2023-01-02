package Sorting_Algoritms;

import java.util.Arrays;

public class Merge_Sort {
    private static final int CUTOFF = 7;

    public void sort(int[] arr, int l, int h) {
        if (h <= l) return;
        int mid = l + (h - l) / 2;
        sort(arr, l, mid);
        sort(arr, mid + 1, h);
        if ((arr[mid] < arr[mid + 1])) return;
        mergeSort(arr, l, h, mid);
    }

    public void mergeSort(int[] arr, int l, int h, int mid) {
        System.out.println(mid);
        int[] aux = new int[arr.length];
        for (int i = l; i <= h; i++) {
            aux[i] = arr[i];
        }
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= h; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > h) arr[k] = aux[i++];
            else if (less(aux[j], aux[i])) arr[k] = aux[j++];
            else arr[k] = aux[i++];
        }
    }

    private static boolean less(int v, int w) {
        return v < w;
    }

    public static void main(String[] args) {
        int[] arr = {7, 2, 3, 4};
        Merge_Sort m = new Merge_Sort();
        m.sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

}
//time complexity of merge sort is O(nlogn)
//space complexity of merge sort is O(n)
//worse case time complexity of merge sort is O(nlogn)
//T(n) = 2T(n/2) + cn
//where cn is the time taken to merge two sorted arrays of size n
//it is a stable sorting algorithm
