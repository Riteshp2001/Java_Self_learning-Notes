package Threading.Basic_Threading;

public class DeadLock_1 extends Thread {
    A a = new A();
    B b = new B();

    public void m1() {
        this.start();
        a.method1(b);
    }

    public void run() {
        b.method1(a);
    }

    class A {
        public synchronized void method1(B b) {
            System.out.println("Thread is Locking A class Method 1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;
            System.out.println("Calling caller() method of A");
            b.caller();
        }

        public synchronized void caller() {
            System.out.println("caller Called");
        }
    }

    class B {
        public synchronized void method1(A a) {
            System.out.println("Thread is Locking B class Method 1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;
            System.out.println("Calling caller() method of B");
            a.caller();
        }

        public synchronized void caller() {
            System.out.println("caller Called");
        }
    }

    public static void main(String[] args) {
        DeadLock_1 t = new DeadLock_1();
        t.m1();
    }
}


