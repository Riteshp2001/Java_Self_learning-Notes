package Sorting_Algoritms;

public class Insertion_Sort {

    //insertion sort
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;
            }
        }
    }
}
//time complexity of insertion sort is O(n^2)
//space complexity of insertion sort is O(1)
//it is a stable sorting algorithm


