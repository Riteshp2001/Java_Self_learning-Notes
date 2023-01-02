package Durga_File_handling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class File_writer {
    /*
    Constructor of FileWriter class

    ****Overwrite mode****
    FileWriter fw = new FileWriter(String fileName)
    FileWriter fw = new FileWriter(File file)
    The above constructors are ment for overwriting the existing file data.

    ****Append mode****
    FileWriter fw = new FileWriter(String fileName, boolean append)
    FileWriter fw = new FileWriter(File file, boolean append)
    The above constructors are ment for appending the data to the existing file data and not hampering already existing data.
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        File f = new File("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\File_Operations_Directory");
        if (!f.isDirectory()) {
            f.mkdir();//creating a directory
        }
        FileWriter fw = new FileWriter("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\File_Operations_Directory\\File1.txt");
        //if we did-int create the directory then this statement would have thrown FileNotFoundException
        //if the specified file is not Available then all use of above-mentioned constructors will Create a new file
        //Important*******
        //Filewriter Doesn't create a Directory but does create file if not available in the specified path directory.
        fw.write("Hello World ");//writing data to the file using String
        fw.write(65);//writing data to the file using int value
        fw.write('A');//writing data to the file using char value
        char[] c = new char[]{'a', 'b', 'c', 'd'};
        fw.write(c);//writing data to the file using char array
        fw.write(' ');
        fw.write(c, 1, 3);//we can also specify from which Char array position we need to write data into txt file
        fw.write(10);
        fw.write("hello World", 3, 6);//Same can be done to String
        fw.flush();
        fw.close();
    }

//    Note:  The main problem with filewriter is we have to insert line seperator(\n) manually cause in some systems
//           OS doesn't understand \n in some systems
}
