package Sorting_Algoritms;

import java.util.Arrays;
//quicksort using ArrayList


public class Quick_Sort {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int partition(int[] arr, int s, int e) {
        int pivotpoint = arr[s];
        int count = 0;
        for (int i = s + 1; i <= e; i++) {
            if (arr[i] < pivotpoint) count++;
        }
        int pivot = s + count;
        swap(arr, s, pivot);
        int i = s;
        int j = e;
        while (i < pivot && j > pivot) {
            while (arr[i] < pivotpoint) i++;
            while (arr[j] > pivotpoint) j--;
            if (i < pivot && j > pivot) swap(arr, i, j);
        }
        return pivot;
    }

    public static void quickSort(int[] arr, int s, int e) {
        if (s >= e) return;
        int pivot = partition(arr, s, e);
        quickSort(arr, s, pivot - 1);
        quickSort(arr, pivot + 1, e);
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 6, 4, 9, 7, 5};
//        quickSort(arr,0,arr.length-1);s
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static int kthLargest(int[] arr, int k) {
        int l = 0;
        int h = arr.length - 1;
        k = k;//largest element
        while (l < h) {
            int j = partition(arr, l, h);
            if (j < k) l = j + 1;
            if (j > k) h = j - 1;
            else return arr[k];
        }
        return arr[k];
    }

    public static void sort(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        int pointeri = i;
        int pointerj = j;
        while (i <= j) {
            System.out.println(Arrays.toString(arr));
            if (arr[i] > arr[j]) swap(arr, i++, j--);
            else {
                i++;
                j--;
            }
            while (pointeri <= i) {
                if (arr[pointeri] >= arr[i]) {
                    swap(arr, pointeri, i);
                    pointeri++;
                }
                pointeri++;
            }
            while (pointerj >= j) {
                if (arr[pointerj] <= arr[j]) {
                    swap(arr, pointerj, j);
                    pointerj--;
                }
                pointerj--;
            }
            pointeri = 0;
            pointerj = arr.length - 1;
        }
    }
}
