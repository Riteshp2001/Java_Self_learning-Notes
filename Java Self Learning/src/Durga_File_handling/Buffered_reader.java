package Durga_File_handling;

import java.io.*;

public class Buffered_reader {//Most Enhanced Reader

    //We can use Buffered reader to read character data from file
    //The main advantage of Buffered Reader when compared with file reader is we can read data line by line in addition to
    //Character by character

    /*
    Constructors in Buffered Reader
    BufferedReader br = new BufferedReader(Reader r);
    BufferedReader br = new BufferedReader(Reader r,int bufferSize);
     */

    /*
    Note :
    Buffered Reader cant communicate directly with the file
    So it is required to read with some Reader Object sam like Bufferedwriter
     */
    public static void main(String[] args) throws IOException {
        //Methods
        //1. int read() - it attempts to read next character form the file and returns the unicode value
        //                if next character not available then this method returns -1;
        //2. int read(char[] ch) - it attempts to read enough characters array and returns number of characters copied from the file
        //3. void close();
        //4. String readLine() - it attempts to read next line form the file , if no nextline it returns Null
        FileReader fr = new FileReader("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\File_Operations_Directory\\File1.txt");
        BufferedReader br = new BufferedReader(fr);
        writeData();
        String stringline = br.readLine();
        while (stringline != null) {
            System.out.println(stringline);
            stringline = br.readLine();
        }

        //Whenever we are closing Buffered Reader automatically Underlying Filereader closes, and we are not required to close Explicitly
        br.close();

    }

    private static void writeData() throws IOException {
        FileWriter fw = new FileWriter("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\File_Operations_Directory\\File1.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Hello World ");

        bw.newLine();

        bw.write(65);
        bw.write('A');

        bw.newLine();

        char[] c = new char[]{'a', 'b', 'c', 'd'};
        bw.write(c);

        bw.newLine();

        c = new char[]{'p', 'a', 'n', 'd', 'i', 't', 'i'};
        bw.write(c, 0, c.length - 1);
        bw.write(10);
        String a = "hello World";
        bw.write(a, 6, 5);
        bw.flush();
        bw.close();
    }
}
