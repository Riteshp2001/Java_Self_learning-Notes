package File_Handling_anudip;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class File_basic_operations {
    public static void main(String[] args) throws IOException {
        int i = 0;
        /*
        //Constructor of File class
        File f = new File(String pathname)
        File f = new File(String subDirectory, String child)
        File f = new File(File subDirectory, String child)
         */

        /*
        //Methods of File class
        boolean createNewFile() throws IOException //Creates a new file
        boolean delete() //Deletes the file
        boolean exists() //Checks if the file exists
        String getAbsolutePath() //Returns the absolute path of the file
        String getName() //Returns the name of the file
        String getParent() //Returns the parent directory of the file
        boolean isDirectory() //Checks if the file is a directory
        boolean isFile() //Checks if the file is a file
        boolean isHidden() //Checks if the file is hidden
        long lastModified() //Returns the last modified time of the file
        long length() //Returns the length of the file
        String[] list() //Returns the list of files in the directory
        File[] listFiles() //Returns the list of files in the directory as File objects
        boolean mkdir() //Creates a directory
        boolean mkdirs() //Creates a directory and all the parent directories
        boolean renameTo(File dest) //Renames the file to the specified file
        boolean setLastModified(long time) //Sets the last modified time of the file
        boolean setReadOnly() //Sets the file to read only
        boolean setWritable(boolean writable) //Sets the file to writable
        boolean setWritable(boolean writable, boolean ownerOnly) //Sets the file to writable
        boolean setReadable(boolean readable) //Sets the file to readable
        boolean setReadable(boolean readable, boolean ownerOnly) //Sets the file to readable
        boolean setExecutable(boolean executable) //Sets the file to executable
        boolean setExecutable(boolean executable, boolean ownerOnly) //Sets the file to executable
        boolean canExecute() //Checks if the file can be executed
        boolean canRead() //Checks if the file can be read
        boolean canWrite() //Checks if the file can be written
        boolean isAbsolute() //Checks if the file is absolute

        //Static methods of File class
        static File createTempFile(String prefix, String suffix) //Creates a temporary file
        static File createTempFile(String prefix, String suffix, File directory) //Creates a temporary file in the specified directory
        static File[] listRoots() //Returns the list of root directories
         */

        //important methods of File class
        /*
        //1. boolean createNewFile() throws IOException //if file already exists then it returns false else true
        //2. boolean delete() //deletes file in memory first and displays False
        //3. boolean exists() //if file is not present then it returns false else true
        //4. boolean isDirectory() //if file is not a directory then it returns false else true
        //5. boolean isFile() //if file is not a file then it returns false else true
        //6. String[] list() //returns the list of files in the directory
        //7. long length() //returns the length of the file
        //8. boolean mkdir() //creates a directory
         */

        //Constructor - 1
        File f = new File("D:\\Vs Code Projects\\Java-Camp\\src\\File_handling\\File_handlingtest");
        f.mkdir();
        //Constructor - 2
        File f1 = new File("D:\\Vs Code Projects\\Java-Camp\\src\\File_handling\\File_handlingtest", "File_handlingtest.txt");
        f1.createNewFile();
        //Constructor - 3
        File f2 = new File(f, "File_handlingtest.txt");

        File f3 = new File("D:\\Vs Code Projects\\Java-Camp\\src\\File_handling");

        //does not create new file it only creates object and if file is present object will point towards that file
        System.out.println("Does File Exists before using f.createNewFile command :" + f.exists());
        f.createNewFile();//creates new file
//        f.mkdir();//creates directory in the path
        //Creates file in memory first then displays test.txt in folder

        System.out.println(f.getPath());
        System.out.println("Does File Exists :" + f.exists());
        System.out.println("Is it Read-only file? :" + f.exists());
        System.out.println("File size is :" + f.length());
        System.out.println("is it File :" + f1.isFile());
        System.out.println("is it Directory :" + f.isDirectory());
        System.out.println("Last Modified Current File : " + f.lastModified());
        f.delete();//deletes file in memory first and displays False
        f1.delete();
        System.out.println("Does File Exists after deletion :" + f.exists());
        System.out.println(Arrays.toString(f3.list()) + " Length of file is - " + f3.list().length + "\n\n\n");

        File f4 = new File("D:\\Vs Code Projects\\Java-Camp");
        File[] arr = f4.listFiles();
        for (File s : arr) {
            if (s.isFile()) {
                System.out.println(s.getName() + " is a file");
            } else if (s.isDirectory()) {
                System.out.println(s.getName() + " is a directory");
            }
        }
    }
}
