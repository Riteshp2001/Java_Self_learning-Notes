package Durga_File_handling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Buffered_writer {

    //Usage of File writer and reader is not recommended because
    //1. We have to insert '\n' manually which is varied from system to system while writing data into file using FileWriter
    //2. By using File reader we can read data and store character by character which is not convineant to programmer

    //To overcome these problems we should go for buffered writer and buffered reader

    /*
        Constructors in Buffered writer
        BufferedWriter bw = new BufferedWriter(Writer w);
        BufferedWriter bw = new BufferedWriter(Writer w, int bufferSize);//most of cases we don't use this
         */
    //Note:
    //buffered writer cant communicate directly with the file it can communicate via some writer object

    /*
    Methods
    void write(int ch);
    void write(String str);
    void write(char[] ch);
    void flush;
    void close;
    void newLine();//this is new method in BufferedWriter which introduces newline rest all methods are same as Filewriter
     */
    public static void main(String[] args) throws IOException {


        //BufferedWriter bw = new BufferedWriter("abc.txt"); INVALID
        //BufferedWriter bw = new BufferedWriter(new File("abc.txt")); INVALID
        //BufferedWriter bw = new BufferedWriter(new FileWriter("abc.txt")); VALID - cause buffered-writer can communicate  with filewriter and fw can communicate with file
        //BufferedWriter bw = new BufferedWriter(new BufferedWriter(new FileWriter("abc.txt"))); VALID - two level buffering
        FileWriter fw = new FileWriter("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\File_Operations_Directory\\File1.txt");
        BufferedWriter bw = new BufferedWriter(fw);
//        bw = new BufferedWriter(fw,10);
//        bw = new BufferedWriter(new BufferedWriter(fw));
        bw.write("Hello World ");//writing data to the file using String

        bw.newLine();

        bw.write(65);//writing data to the file using int value
        bw.write('A');//writing data to the file using char value

        bw.newLine();

        char[] c = new char[]{'a', 'b', 'c', 'd'};
        bw.write(c);//writing data to the file using char array

//        bw.write('\n'); instead we write
        bw.newLine();

        c = new char[]{'p', 'a', 'n', 'd', 'i', 't', 'i'};
        bw.write(c, 0, c.length - 1);//we can also specify from which Char array position we need to write data into txt file
        bw.write(10);
        String a = "hello World";
        bw.write(a, 6, 5);//Same can be done to String
        bw.flush();
        bw.close();//recommended as bw is the encapsulating element of fw so if i close bw automatically fw closes
//        fw.close();//not recommended
//        fw.close();bw.close();//not recommended when used in combination

        //Diagram

//                                         👇text Area
//                                           |     |
        //👇BufferedWriter                   |     |
//        -------------------- 👇FileWriter  |     |
//                            -------------- |     |
//                            -------------- |     |
//        --------------------               |     |

    }
}
