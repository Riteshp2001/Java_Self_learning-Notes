package com.Team_Raji_LIFI_2022;

import java.util.Arrays;

public class _Main_Class_Transmitter extends Transciever {
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        //Creating object of Transmitter
//        StopwatchCPU timer = new StopwatchCPU();
//        Transmitter t = new Transmitter();
//        t.transmit();
//        //Making Main Thread sleep for 1 second
//        Thread.sleep(1000);
//        System.out.println(timer.elapsedTime());
        System.out.println();
    }

    static int[] bubbleSort(int[] arr, int i) {
        if (i < 0) return arr;
        for (int j = 0; j < arr.length; j++) {
            if (arr[i] > arr[j]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        return bubbleSort(arr, arr.length - 1);
    }
}
