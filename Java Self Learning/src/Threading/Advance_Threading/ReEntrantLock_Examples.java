package Threading.Advance_Threading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantLock_Examples {

    static class MyThread extends Thread {
        Display d;
        String name;

        MyThread(Display d, String name) {
            this.d = d;
            this.name = name;
        }

        public void run() {
            d.wish(name);
            d = null;
            name = null;
        }
    }

    static class Display {
        ReentrantLock r = new ReentrantLock();

        void wish(String name) {
            r.lock();//-----> 1
            for (int i = 0; i < 3; i++) {
                System.out.println(name + " now Thread is running : " + Thread.currentThread().getName());
            }
            r.unlock();//----> 2
        }
    }

    static class MyThread_2 extends Thread {
        Display_2 d;

        MyThread_2(Display_2 d) {
            this.d = d;
        }

        public void run() {
            try {
                d.wish();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            d = null;
        }
    }

    static class Display_2 {
        ReentrantLock r = new ReentrantLock();

        void wish() throws InterruptedException {
            do {
                if (r.tryLock(5000, TimeUnit.MILLISECONDS)) {
                    System.out.println("Thread is holding lock : " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        System.out.println("Thread got interrupted");
                    } finally {
                        r.unlock();
                    }
                    break;
                } else {
                    System.out.println(Thread.currentThread().getName() + " Unable to Get Lock");
                }
            } while (true);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReEntrantLock_Examples e = new ReEntrantLock_Examples();

        //Normal Useage
        e.Thread_1Start();

        Thread.sleep(2000);
        System.out.println("\nExecuting Second Method ...\n");

        //Flexibility
        //Demo program for tryLock Method
        e.Thread_2Start();
    }

    void Thread_1Start() {
        Display d = new Display();
        MyThread t1 = new MyThread(d, "ritesh");
        MyThread t2 = new MyThread(d, "govind");
        MyThread t3 = new MyThread(d, "Test");
        t1.start();
        t2.start();
        t3.start();
    }

    void Thread_2Start() throws InterruptedException {
        Display_2 d1 = new Display_2();
        MyThread_2 t4 = new MyThread_2(d1);
        MyThread_2 t5 = new MyThread_2(d1);
        MyThread_2 t6 = new MyThread_2(d1);
        t4.start();
        Thread.sleep(1000);
        t5.start();
        Thread.sleep(1000);
        t6.start();
    }
}
