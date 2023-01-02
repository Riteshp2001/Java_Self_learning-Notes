package com.Team_Raji_LIFI_2022;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class Transciever {
    public void transmit() throws Exception {
    }

    public void receive() throws Exception {
    }

    public void threadSleep(String str, int time) throws InterruptedException {
        System.out.print(str);
        int timer = 0;
        while (timer++ <= 2) {
            System.out.print(".");
            Thread.sleep(time);
        }
    }

    //Method for CRC check of String
    String CRC_Checksum(String input) throws Exception {
        byte[] bytes = input.getBytes();
        Checksum checksum = new CRC32(); // java.util.zip.CRC32
        checksum.update(bytes, 0, bytes.length);
        return String.valueOf(checksum.getValue());
    }
}
