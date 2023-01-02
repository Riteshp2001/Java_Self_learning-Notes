package Heaps;

public class Heapify {
    static int[] arr;
    int idx = -1;

    Heapify(int n) {
        arr = new int[n];
    }

    public void swimToOriginalLocation(int n) {
        while (2 * n <= idx) {
            int j = 2 * n;
            if (j < idx && less(arr[j], arr[j + 1])) j++;
            if (!less(arr[n], arr[j])) break;
            swap(arr, n, j);
            n = j;
        }
    }

    public void insertHeapVal(int n) {
        arr[++idx] = n;
        swimToOriginalLocation(idx);
    }

    public void sinkDownToOriginalLocation(int n) {
        while (n <= idx) {
            if (n < idx && less(arr[2 * n], arr[2 * n + 1])) {
                swap(arr, n, 2 * n);
                n = 2 * n;
            } else {
                swap(arr, n, 2 * n + 1);
                n = 2 * n + 1;
            }
            if (n > idx) break;
            if (arr[n] > arr[2 * n] && arr[n] > arr[2 * n + 1]) break;
        }
    }

    public int removeMax() {
        int ans = arr[0];
        swap(arr, 0, idx--);
        sinkDownToOriginalLocation(1);
        arr[idx + 1] = 0;
        return ans;
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public boolean less(int a, int b) {
        return a > b;
    }

}
