package Threading.Basic_Threading;

public class Wait_Notify_NotifyAll {

    //types of methods in Object class of wait notify and notifyAll
    //1. wait() - it is used to pause the current thread and wait for another thread to perform some task
    //2. notify() - it is used to wake up a single thread that is waiting on the same object
    //3. notifyAll() - it is used to wake up all the threads that are waiting on the same object

//    public final void wait(){};
//    public final native void wait(long timeout){};
//    public final void wait(long timeout, int nanos){};
//    public final void notify(){};
//    public final void notifyAll(){};

    //every wait method throws InterruptedException which is a checked exception
    //every notify method throws IllegalMonitorStateException which is a runtime exception

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        //Point to remember---

        //Two threads can communicate with each other using wait(), notify() and notifyAll() methods.
        //wait() method is used to pause the execution of current thread and give chance to other threads to execute.
        //notify() method is used to wake up a single thread which is waiting on the same object.
        //notifyAll() method is used to wake up all the threads which are waiting on the same object.
        //wait(), notify() and notifyAll() methods are defined in Object class. and it's defined in Object class cause
        //every object in java has its own lock and every object can be used to call wait(), notify() and notifyAll() methods.

        // to call wait , notify and notifyAll methods on any object , Thread should be owner of the object i.e ,
        // we need to synchronize that object in Synchronized area or thread should have lock of the object
        // hence we can call wait , notify and notifyAll methods on any object only from Synchronized area
        // otherwise we will get RuntimeException saying `IllegalMonitorStateException`

        //if thread calls wait method on any locked object it will release lock of only that particular object and will go to waiting state
        //if thread calls notify method on any locked object it will release lock of that object but may not immediately release lock
        //except wait , notify and notifyAll methods there is no other method which releases lock of the object
        ThreadExample t1 = new ThreadExample();
        Thread t = new Thread(t1);
        t.start();
//        Thread.sleep(1000);//This method is not at all recommended to use
//        cause thread will be done iwth execution iwthin less than 1 nano secpond but we are using 1000 ms sleep of the main thread
        //system execution will slow down
//        t.join();//imagine if ThreadExample class has 1 crore lines of code after updation of sum value then because of t.join()
        //main thread will wait for t1 thread to complete its execution for all those 1 crore lines of code then resume
        //this will degrade the performance of the system
        //so we should not use t.join() method
        //instead we should use wait() and notify() methods
//        t.wait();//it is recommended to use wait() method instead of t.join() and Thread.sleep(long timestamp) method
        synchronized (t) {
            System.out.println("Main Thread called wait() method; Waiting for t to complete...");
            t.wait();//remember to always use wait() method inside synchronized block
            //it is read like main Thread ko wait pe daaldo and t1 thread ko execute karwa do until t thread gives notify signal
            // the upper code will give RE : IllegalMonitorStateException
            System.out.println("t thread completed execution main thread got notified succesfully...");
        }
        System.out.println("Sum = " + t1.sum);
    }
}

class ThreadExample implements Runnable {
    int sum = 0;

    //    public synchronized void run(){
//        for(int i = 0;i<1000;i++){
//            sum+=i;
//        }
//        this.notify();
//        //1 crore lines of code
//    }
//    or
    public void run() {
        synchronized (this) {//Main Thread Running
            System.out.println("ThreadExample class object lock acquired by t thread now Starts Calculation");
            for (int i = 0; i < 1000; i++) {
                sum += i;
            }
            System.out.println("ThreadExample class object lock released by t thread now Notifies Main Thread");
            this.notify();
        }
        //1 crore lines of code
    }
}

