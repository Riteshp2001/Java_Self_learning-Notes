package Durga_File_handling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Merge_2_File_into_3rd {
    public static void main(String[] args) throws IOException {

        //Problem 1 : print line by line
//        PrintWriter pw = new PrintWriter("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\TestMerging\\test3.txt");
//        FileReader fr = new FileReader("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\TestMerging\\test1.txt");
//        BufferedReader br = new BufferedReader(fr);
//        String line = br.readLine();
//        while(line != null){
//            pw.println(line);
//            line = br.readLine();
//        }
//        fr = new FileReader("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\TestMerging\\test2.txt");
//        br = new BufferedReader(fr);
//        line = br.readLine();
//        while(line != null){
//            pw.println(line);
//            line = br.readLine();
//        }

//        //Problem 2 : Print Alternatively
//        PrintWriter pw = new PrintWriter("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\TestMerging\\test3.txt");
//        FileReader fr = new FileReader("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\TestMerging\\test1.txt");
//        BufferedReader br = new BufferedReader(fr);
//        FileReader fr1 = new FileReader("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\TestMerging\\test2.txt");
//        BufferedReader br1 = new BufferedReader(fr1);
//        String line = br.readLine();
//        String line1 = br1.readLine();
//        while(true){
//            if(line != null)
//                pw.println(line);
//            if(line1 != null)
//                pw.println(line1);
//            if(line == null && line1 == null)break;
//            line = br.readLine();
//            line1 = br1.readLine();
//        }

        //Problem 3 : Exclude numbers / print unique numbers which are present in test2 and rest print in test3
        PrintWriter pw = new PrintWriter("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\TestMerging\\test3.txt");
        FileReader fr = new FileReader("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\TestMerging\\test1.txt");
        BufferedReader br = new BufferedReader(fr);
        FileReader fr1 = new FileReader("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\TestMerging\\test2.txt");

        String line = br.readLine();
        while (line != null) {
            boolean check = false;
            BufferedReader br1 = new BufferedReader(fr1);
            String line1 = br1.readLine();
            while (line1 != null) {
                if (line.equals(line1)) {
                    check = true;
                    break;
                }
                line1 = br1.readLine();
            }
            if (!check) pw.println(line);
            line = br.readLine();
        }

        pw.close();
        br.close();

    }
}