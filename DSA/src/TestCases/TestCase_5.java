package TestCases;

import java.util.Scanner;

class Threads implements Runnable {
    static Thread t1;

    public void run() {
        try (Scanner sc = new Scanner(System.in)) {
            t1.join();//main thread ko rookdo aur t1 thread ko chala do
        } catch (InterruptedException | IllegalAccessError e) {
        }
        System.out.println("Thread is running");
    }
}

public class TestCase_5 {
    public static void main(String[] args) {
        Threads.t1 = Thread.currentThread();
        Thread t2 = new Thread(new Threads());
        t2.start();
        Threads.t1.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Main Thread - " + Thread.currentThread().getName() + " Interrupted");
        }
        if (t2.isInterrupted()) {
            System.out.println("Thread is interrupted");
        }
        System.out.println("Main Thread");
    }
}
