package TestCases;

class Display {

    public void wish(String name) {
        synchronized (this) {
            for (int i = 0; i < 3; i++) {
                System.out.print("Good Morning : ");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                System.out.println(name);
            }
        }
    }

    public void print() {
        synchronized (this) {
            for (int i = 0; i < 3; i++) {
                System.out.println("Hello");
            }
        }

    }
}

class MyThread implements Runnable {
    Display d;

    MyThread(Display d) {
        this.d = d;
    }

    public void run() {
        d.wish("Ritesh");
    }
}

class newMyThread implements Runnable {
    Display d;

    newMyThread(Display d) {
        this.d = d;
    }

    public void run() {
        d.print();
    }
}

public class TestCase_6 {
    public static void main(String[] args) throws InterruptedException {
        Display d = new Display();
        Thread t3 = new Thread(new MyThread(d));
        Thread t4 = new Thread(new newMyThread(d));
        t3.start();
        t4.start();
        t4.join();
    }
}
