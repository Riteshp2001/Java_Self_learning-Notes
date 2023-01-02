package Enum;

enum Enum_Example {
    A, B, C, D;


    class Inner extends Thread {
        public synchronized void run() {
            int i = 0;
            while (i++ < 2) {
                System.out.print("Run" + " ");
            }
            System.out.print(Thread.currentThread().getName() + " ");
            System.out.println();
        }
    }

    class Inner2 implements Runnable {
        public void run() {
            int i = 0;
            while (i++ < 2) {
                System.out.print("Run" + " ");
            }
            System.out.print(Thread.currentThread().getName() + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Inner2 i = Enum_Example.A.new Inner2();
        Thread t = new Thread(i);
        t.start();
    }
}
