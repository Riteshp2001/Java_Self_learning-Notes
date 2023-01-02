package Durga_File_handling;

import java.io.File;

public class File_Operations {

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

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        File f = new File("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\File_Operations_Directory");
        //creating a directory
        File f1 = new File("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\File_Operations_Directory\\File1.txt");
        // creating a file inside newly created directory
        File f2 = new File(f, "File2.txt");//creating another file inside newly created directory using Third Constructor
        //File f2 = new File("D:\\Vs Code Projects\\Java-Camp\\src\\Durga_File_handling\\File_Operations_Directory","File2.txt");//Also Valid
        f.mkdir();
        try {
            f1.createNewFile();
            f2.createNewFile();
        } catch (Exception e) {
            System.out.println(e + " Exception Occurred");
        }
        System.out.println("File1.txt exists: " + f1.exists());
        System.out.println("File2.txt exists: " + f2.exists());
        System.out.println("File1.txt is a file: " + f1.isFile());
        System.out.println("File2.txt is a file: " + f2.isFile());
        System.out.println("File1.txt is a directory: " + f1.isDirectory());
        System.out.println("File2.txt is a directory: " + f2.isDirectory());
//        Thread.sleep(10000);
        //deleting file
        f1.delete();
        System.out.println("File1.txt exists: " + f1.exists());
        f2.delete();
        System.out.println("File2.txt exists: " + f2.exists());
        f.delete();
        System.out.println("File_Operations_Directory exists: " + f.exists());
    }
}
