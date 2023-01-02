package com.Team_Raji_LIFI_2022;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;


public class Reciever extends Transciever {
    File f;

    public Reciever() {
        f = new File("D:\\Vs Code Projects\\Java-Camp\\DSA\\src\\com\\Team_Raji_LIFI_2022\\StringtoBinary.txt");
    }

    //Method to convert Binary to String
    public static String binaryToText(String binary) {
        return Arrays.stream(binary.split("(?<=\\G.{8})"))/* regex to split the bits array by 8*/
                .parallel()
                .map(eightBits -> (char) Integer.parseInt(eightBits, 2))
                .collect(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append
                ).toString();
    }

    //Main Method for Receiver
    public void receive() throws Exception {
        System.out.println("Does File with File name - StringtoBinary.txt Exists? :" + is_present(f));
        System.out.println("Reading Saved Binary Fle");
        int j;
        FileInputStream f_i = new FileInputStream("D:\\Vs Code Projects\\Java-Camp\\DSA\\src\\com\\Team_Raji_LIFI_2022\\StringtoBinary.txt");
        //Reading Binary String from File
        String s = "";
        do {
            j = f_i.read();
            if (j != -1) {
                s += (char) j;
            }
        } while (j != -1);

        //Checking CRC Checksum verification
        String[] ans = s.split(" ");//Binary Data and CRC
        String CRC = ans[1];//Attached CRC//1378
        String received = (CRC_Checksum(binaryToText(ans[0])));//1378
        System.out.println("CRC Checksum of Received Binary Data: " + received);
        if (CRC.equals(received)) {
            System.out.println("CRC Checksum Verified");
            System.out.println("Data Received Successfully");
        } else {
            System.out.println("CRC Checksum Not Verified");
            System.out.println("Data Received Unsuccessfully");
        }
        System.out.println("\n///////////////////////////////////////////////////////\n");
        threadSleep("Converting Binary to Text", 2000 / 3);
        //Printing end result
        System.out.println("\nFinal Binary Received Data is : " + ans[0]);
        System.out.println("Interpreted Data at Receiver End is : " + binaryToText(ans[0]));
        System.out.println();
        f_i.close();
    }

    public boolean is_present(File f) {
        //returns true if file exists
        return f.exists();
    }
}
