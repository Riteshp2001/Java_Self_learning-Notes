package File_Handling_anudip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class File_CreationandOutput {
    public static void main(String[] args) throws IOException {//Need to handle Exception
        int i = 0;
        File f = new File("D:\\Vs Code Projects\\Java-Camp\\src\\File_handling\\StringtoBinary.txt");

        FileOutputStream f_out;

        f_out = new FileOutputStream(f, true);

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Your Words: ");
        String test = sc.nextLine();

        System.out.println("Length of String is :" + test.length() + "\n");

        while (i < test.length()) {
            f_out.write(test.charAt(i));
            i++;
        }
        f_out.close();

//        f.delete();//deletes file in memory first and displays False
        System.out.println("Does File Exists :" + f.exists());

        int j;
        FileInputStream f_i = new FileInputStream("D:\\Vs Code Projects\\Java-Camp\\src\\File_handling\\StringtoBinary.txt");

        do {
            j = f_i.read();
            if (j != -1) {
                System.out.print((char) j + " ");
            }
        } while (j != -1);

        System.out.println();
        f_i.close();
        new FileOutputStream("D:\\Vs Code Projects\\Java-Camp\\DSA\\src\\com\\Team_Raji_LIFI_2022\\StringtoBinary.txt").close();//if u don't want to store data uncomment this
    }
}
