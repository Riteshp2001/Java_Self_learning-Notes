package Threading.Basic_Threading;

import java.util.concurrent.Semaphore;

public class WriterReadersProblem {

    static int readerCount = 0;
    static Semaphore x = new Semaphore(1);
    static Semaphore rsem = new Semaphore(1);
    static Semaphore wsem = new Semaphore(1);
    static String test = "ritesh";

    static class Read implements Runnable {
        @Override
        public synchronized void run() {
            try {
                rsem.acquire();
                x.acquire();
                readerCount++;
                if (readerCount == 1) wsem.acquire();
                x.release();

                System.out.println("Thread " + Thread.currentThread().getName() + " is READING");
                System.out.println("Saw Test Variable name as " + test);
                Thread.sleep(1500);
                System.out.println("Thread " + Thread.currentThread().getName() + " has FINISHED READING");

                x.acquire();
                readerCount--;
                if (readerCount == 0) wsem.release();
                x.release();
                rsem.release();

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static class Write implements Runnable {
        String temp;

        Write(String temp) {
            this.temp = temp;
        }

        @Override
        public synchronized void run() {
            try {
                rsem.acquire();
                wsem.acquire();
                System.out.println("Thread " + Thread.currentThread().getName() + " is WRITING");
                System.out.println("Changing test variable name as " + temp);
                test = temp;
                Thread.sleep(2500);
                System.out.println("Thread " + Thread.currentThread().getName() + " has finished WRITING");
                wsem.release();
                rsem.release();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Read read = new Read();
        Thread t1 = new Thread(read);
        t1.setName("thread1");
        Thread t2 = new Thread(read);
        t2.setName("thread2");
        Thread t3 = new Thread(new Write("Avi"));
        t3.setName("thread3");
        Thread t4 = new Thread(read);
        t4.setName("thread4");
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
        t4.start();
        t4.join();
    }
}