package Sorting_Algoritms;

public class Selection_Sort {
    public static void selectionSort(int[] arr, int l, int h) {
        for (int i = l; i < h; i++) {
            int min = i;
            for (int j = 0; j < h; j++) {
                if (arr[i] > arr[j]) {
                    min = j;
                }
            }
        }
    }
}
//time complexity of selection sort is O(n^2)
//space complexity of selection sort is O(1)
//it is an unstable sorting algorithm
