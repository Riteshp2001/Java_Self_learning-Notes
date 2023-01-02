package com.Team_Raji_LIFI_2022;

import edu.princeton.cs.algs4.StopwatchCPU;

import java.io.FileOutputStream;
import java.util.Stack;

public class _Main_Class_Receiver extends Transciever {
    public static void main(String[] args) throws Exception {
        StopwatchCPU timer = new StopwatchCPU();
        new Transciever().threadSleep("Verifying File", 1000);
        System.out.println();
        Reciever r = new Reciever();
        r.receive();
        //Making Main Thread sleep for 4 second until then data would not be erased from txt file
//        Thread.sleep(4000);
        new FileOutputStream("D:\\Vs Code Projects\\Java-Camp\\DSA\\src\\com\\Team_Raji_LIFI_2022\\StringtoBinary.txt").close();//if u don't want to store data uncomment this
        System.out.println(timer.elapsedTime());
    }
}
