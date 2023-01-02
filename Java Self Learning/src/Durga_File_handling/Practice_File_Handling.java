package Durga_File_handling;

import java.io.*;
import java.util.Scanner;

public class Practice_File_Handling extends Thread {
    public void run() {
        File f = new File("D:\\Vs Code Projects\\Java-Camp\\src");
        File[] fileArray = f.listFiles();
        System.out.println("\n\n List of Files in src Directory :");
        assert fileArray != null;
        for (File file : fileArray) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(file);
        }
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        File file = new File("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\Practice_Directory");
        file.mkdir();
        FileWriter fileWriter = new FileWriter("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\Practice_Directory\\TestFile_1");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try (bufferedWriter) {
            System.out.print("Enter Your Name : ");
            bufferedWriter.write(sc.nextLine());
            bufferedWriter.newLine();
            System.out.print("\nenter your Age : ");
            bufferedWriter.write(sc.nextLine());
            bufferedWriter.newLine();
            System.out.print("\nenter your Message to world : ");
            String message = sc.nextLine();
            int i = 3;
            while (i-- >= 0) {
                bufferedWriter.write(message);
                System.out.println(message);
                bufferedWriter.newLine();
                Thread.sleep(500);
            }
            Thread t = new Thread(new Practice_File_Handling());
            t.start();
            t.join();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Do you want to read Files ?");
        String a = sc.nextLine();
        if (a.equals("yes")) {
            FileReader fileReader = new FileReader("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\Practice_Directory\\TestFile_1");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str = bufferedReader.readLine();
            while (str != null) {
                System.out.println(str);
                str = bufferedReader.readLine();
            }
            bufferedReader.close();
        } else {
            System.out.println("Thank you for choosing us !");
        }

        sc.close();
    }
}
