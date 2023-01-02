package Durga_File_handling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Print_writer {
    //it is the most enhanced Writer to write character data to the file
    //main advantage of printer over filewriter and buffered writer is we can write any type of primitive data directly to the file
    //also we dont need to explicitly use newLine method instead we use
    //pw.println() to print the data and add a new line

    /*
    Constructors in Print Writer
    PrintWriter pr = new PrintWriter(String name);
    PrintWriter pr = new PrintWriter(File file);
    PrintWriter pr = new PrintWriter(Writer w);
     */

    /*
    Methods in Print Writer
    write(int c)
    write(char[] ch)
    write(String s);
    flush()
    close()

    print(char ch)
    print(int i);
    print(double d)
    print(boolean b)
    print(String s)
     */
    public static void main(String[] args) throws IOException {
        File f = new File("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\File_Operations_Directory\\File1.txt");
        FileWriter fileWriter = new FileWriter("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\File_Operations_Directory\\File1.txt");

        PrintWriter pw = new PrintWriter(fileWriter, true);
        //or
        pw = new PrintWriter("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\File_Operations_Directory\\File1.txt");
        //or
        pw = new PrintWriter(f);

        pw.write('A');
        pw.write(100);
        pw.write("String");

        //In case of write the corresponding character will be added to the file but in case of print
        // the int value or any other primtive type will be added to file directly
        //Example : pw.write(100) = D
        //          pw.print(100) = 100

        pw.println(100);
        pw.print(10.8989289839);
        pw.println(true);
        pw.print(2471847);
        pw.println('F');
        pw.println("String");
        pw.flush();
        pw.close();

    }

    //Most enhanced writer to write character data to the file is PrintWriter
    //Most enhanced reader to read character data to the file is BufferedReader
}
