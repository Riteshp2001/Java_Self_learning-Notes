package Threading.Basic_Threading;

import java.util.Scanner;

class Database {
    int n = 1000;
    int reader = 0;

    public void reading() throws InterruptedException {
        synchronized (this) {
            reader++;
            System.out.println("Number of readers are " + reader);
        }
        System.out.println(Thread.currentThread().getName() + " is reading data ");
        int sleep = 5000;
        Thread.sleep((int) (Math.random() * sleep));
        synchronized (this) {
            reader--;
            System.out.println(Thread.currentThread().getName() + " finished reading");
            if (reader == 0) {
                this.notifyAll();
            }
        }
    }

    public void writing(int number) throws InterruptedException {
        while (this.reader != 0) {
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
        System.out.println(Thread.currentThread().getName() + " is writing data ");
        System.out.println("do you want to change data ? ");
        String s = new Scanner(System.in).nextLine();
        if (s.equals("yes")) {
            System.out.println("Enter value ");
            this.n = number;
        }
        int sleep = 5000;
        Thread.sleep((int) (Math.random() * sleep));
        System.out.println("Writer " + number + " stops writing.");
        this.notifyAll();
    }
}

class Reader extends Thread {
    private static int readers = 0; // number of readers

    private int number;
    private Database database;

    /**
     * Creates a Reader for the specified database.
     *
     * @param database database from which to be read.
     */
    public Reader(Database database) {
        this.database = database;
        this.number = Reader.readers++;
    }

    /**
     * Reads.
     */
    public void run() {
        while (true) {
            final int DELAY = 5000;
            try {
                Thread.sleep((int) (Math.random() * DELAY));
            } catch (InterruptedException ignored) {
            }
            try {
                this.database.reading();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Writer extends Thread {
    private static int writers = 0; // number of writers

    private int number;
    private Database database;

    /**
     * Creates a Writer for the specified database.
     *
     * @param database database to which to write.
     */
    public Writer(Database database) {
        this.database = database;
        this.number = Writer.writers++;
    }

    /**
     * Writes.
     */
    public void run() {
        while (true) {
            final int DELAY = 5000;
            try {
                Thread.sleep((int) (Math.random() * DELAY));
            } catch (InterruptedException e) {
            }
            try {
                this.database.writing(this.number);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class READER_WRITER_SELF {
    public static void main(String[] args) {

    }
}
