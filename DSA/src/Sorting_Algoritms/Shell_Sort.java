package Sorting_Algoritms;

public class Shell_Sort {
    public static void shellSort(int[] arr, int l, int h) {
        int n = h - l;
        for (int gap = 3 * n + 1; gap > 0; gap /= 3) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = temp;
            }
        }
    }
}
