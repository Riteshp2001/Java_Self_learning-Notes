package Durga_File_handling;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class File_reader {
    /*
    Constructors in File Reader
    FileReader fr = new Filereader(String name);
    FileReader fr = new Filereader(File file);
     */
    public static void main(String[] args) throws IOException {
        //Methods
        //1. int read() - it attempts to read next character form the file and returns the unicode value
        //                if next character not available then this method returns -1;
        //2. int read(char[] ch) - it attempts to read enough characters array and returns number of characters copied from the file
        //3. void close();

        File f = new File("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\File_Operations_Directory\\File1.txt");
        FileReader fr = new FileReader(f);
//        or
        fr = new FileReader("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\File_Operations_Directory\\File1.txt");

        writeData("hello i am Ritesh Pandit");//writing data into file before reading

        int i = fr.read();
        int size = 0;
        while (i != -1) {
            System.out.print((char) i);
            i = fr.read();
            size++;
        }
        System.out.println();
        char[] ch = new char[size];
        //or if the number of character are within int range we can also write
        ch = new char[(int) f.length()];
//        fr.reset();
        //or
        fr = new FileReader(f);//if we don't use this null will be printed cause read method position is at last -1 index
        fr.read(ch);
        for (char c : ch) {
            System.out.print(c);//its printin Null because we need to reset the position of read method after using first for loop
        }

        fr.close();//Closing resources
    }

    private static void writeData(String str) {
        try (FileWriter fileWriter = new FileWriter("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\File_Operations_Directory\\File1.txt");) {
            fileWriter.write(str);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
