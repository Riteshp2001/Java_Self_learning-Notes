package com.Team_Raji_LIFI_2022;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Transmitter extends Transciever {
    File f;

    Transmitter() {
        //Creating object of file
        f = new File("D:\\Vs Code Projects\\Java-Camp\\DSA\\src\\com\\Team_Raji_LIFI_2022\\StringtoBinary.txt");
    }

    public static String convertByteArraysToBinary(byte[] input) {

        StringBuilder result = new StringBuilder();
        for (byte b : input) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                result.append((val & 128) == 0 ? 0 : 1);      // 128 = 1000 0000
                val <<= 1;
            }
        }
        return result.toString();

    }

    public static String prettyBinary(String binary, int blockSize, String separator) {

        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < binary.length()) {
            result.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
            index += blockSize;
        }

        return String.join(separator, result);
    }

    public void transmit() throws Exception {
        int i = 0;
        //Creating object of FileOutputStream
        FileOutputStream f_out = new FileOutputStream(f, true);

        Scanner sc = new Scanner(System.in);

        //Entering Desired String to be sent to the receiver
        System.out.print("Enter Your Words: ");
        String test = sc.nextLine();//hello world
        //Compressing String using Huffman encoding method
//        test = Huffman_Encoding.buildHuffmanTree(test, "encode");
        System.out.println();
        String CRC = CRC_Checksum(test);//1678
        //Converting String to Binary
        String result = convertByteArraysToBinary(test.getBytes(StandardCharsets.UTF_8));
        threadSleep("Converting String to Binary", 1000);
        System.out.println("\n" + prettyBinary(result, 8, " "));

        //Printing Length of String
        System.out.println("Length of String is :" + test.length() + "\n");
        System.out.println("CRC Checksum of String is :" + CRC + "\n");
        result += " " + CRC;//binary data + crc
        //Writing Binary String to File
        //While Loop to write each character of the String to the file
        //here we have used .txt file to store the binary string
        while (i < result.length()) {
            f_out.write(result.charAt(i));
            i++;
        }
    }
}
