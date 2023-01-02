package Threading.Basic_Threading;

import java.util.Scanner;

//class WeddingCardsPrinting implements Runnable{
//    public void run() {
//        System.out.println("WeddingCards Printing is going on");
//    }
//}
//class VenueFixing implements Runnable{
//    public void run() {
//        System.out.println("Venue is being fixed");
//        Thread.yield();
//    }
//}
//
//class WeddingCardsDistribution implements Runnable{
//    public void run() {
//        System.out.println("WeddingCards are being distributed");
//    }
//}

public class Threads_Wait_Join_yeild_sleeep_Notify_Notifyall_Examples implements Runnable {//run
    //main

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Threads_Wait_Join_yeild_sleeep_Notify_Notifyall_Examples());
        t1.start();
        synchronized (t1) {
            t1.wait();
        }
        System.out.println("Fibo Numbers Printed !!");
        System.out.println("Main Thread Exited !!");
//        Thread t1 = new Thread(new VenueFixing());
//        Thread t2 = new Thread(new WeddingCardsPrinting());
//        Thread t3 = new Thread(new WeddingCardsDistribution());
//        t1.join();//this join method stops main thread and lets t1 thread run first same for other threads
//        matlab agar kisi dusre thread ko bulana caahte hai toh current thread ko rook ke bulana padega so t1.join() method
        //it is read like main thread calling join() method on t1 thread to complete t1 Thread execution first
//        t2.start();
//        t2.join();
//        t3.start();
//        t3.join();

    }

    @Override
    public void run() { //t1 main thread
        Thread t2 = new Thread(new Example());
        System.out.println("Fibonacci Numbers");
        t2.start();

        synchronized (t2) {//Example class object is used as a lock
            try {
                t2.wait();//Main Thread Stops;//can be used to solve infinite wait problem
                //resume
            } catch (InterruptedException u) {
                throw new RuntimeException(u);
            }
        }

        synchronized (this) { //Current Object Thread{//current class object giving notification to main thread
            this.notify();
        }
//        for(int i = 0;i<3;i++){
//            System.out.println("Child Thread");
//            Thread.yield();
//        }

        // 1 crore lines oof code
    }

}

class Example implements Runnable {
    public void run() {
        synchronized (this) {//eeek baar mei eek he thread execute hoga
            //t1 lock acquire kiya
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter how many numbers : ");
            int i = sc.nextInt();
            sc.close();
            fibo(i);
            this.notify();
        }
        //t1 unlocks the synchronized area
    }

    public void fibo(int i) {
        int j = 0;
        int k = 1;
        System.out.print("0 1 ");
        for (int l = 1; l <= i; l++) {
            int temp = j;
            j = k;
            k = temp + j;
            System.out.print(k + " ");
        }
        System.out.println();
    }
}

//if the Thread calls join method on itself then it will wait for infinite time this is Deadlock situation