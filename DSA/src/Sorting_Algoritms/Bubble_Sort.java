package Sorting_Algoritms;

public class Bubble_Sort {
    public static void bubbleSort(int arr[], int l, int h) {
        for (int i = l; i < h; i++) {
            for (int j = i + 1; j < h; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
//time complexity of binary sort is O(n^2)
//space complexity of binary sort is O(1)
//it is an unstable sorting algorithm