package Threading.Advance_Threading;

import java.util.concurrent.locks.ReentrantLock;

interface abc {
    void show();
}

public abstract class Java_Concurrent_Package {
    //Problems with Traditional Synchronized Keyword ---

    //1. We are not having any flexibility to try for a lock without waiting for Thread to complete its execution in Synchronized Block
    //2. There is no way to specify maximum waiting time for a thread to acquire a lock so that thread will wait until lock is available which may create
    //   Performance Issues which may cause Deadlock situation
    //3. If a Thread releases lock then which waiting thread will get the lock there is no specific control on this
    //4. There is no API to list out all waiting threads for a lock
    //5. In Synchronized keyword compulsory we have to use either at method level or within a method(Synchronized Block) and it is not
    //   possible to use across multiple methods

    //   java.util.Concurrent Package is introduced in Java 1.5 to overcome above problems

    //----------------------------------------------------------//

    //Lock Interface ---
    //java.util.concurrent.locks.Lock Interface ---
    //Lock object is similar to Implicit Lock acquired by a thread to execute Synchronized Block or Synchronized Method
    //Lock implementation provide more extensive locking operations than traditional implicit locks
    //Lock implementation classes are --- ReentrantLock, ReentrantReadWriteLock, StampedLock, ReadWriteLock

    //Methods of Lock Interface ---
    //1. void lock() --- Acquires the lock
    //2. void unlock() --- Releases the lock
    //   to call unlock() method compulsory , current thread should be owner of the lock otherwise we will get IllegalMonitorStateException
    //3. Condition newCondition() --- Returns a Condition instance associated with this Lock instance
    //4. boolean tryLock() --- Acquires the lock only if it is free at the time of invocation
    //5. boolean tryLock(long time, TimeUnit unit) --- Acquires the lock if it is free within the given waiting time and the current thread has not been interrupted
    //   TimeUnit --- TimeUnit is an enum which is used to specify time unit which is present in java.util.concurrent.TimeUnit
    //6. void lockInterruptibly() --- Acquires the lock if it is available and returns immediately
    //   if the lock is not available then it will wait , if the current thread is interrupted while waiting then it will throw InterruptedException
    //   and Thread will not acquire the lock

    //Implementations of Lock Interface ---
    //    👇👇👇👇👇👇👇👇👇👇

    //ReentrantLock Class ---
    //ReentrantLock class is implementation of Lock interface and is a direct child class of Object class
    //Reentrant means a thread can acquire the same lock multiple times without any issue
    //Internally ReentrantLock class uses a concept of Lock Count and Owner Thread

    public static void main(String[] args) throws InterruptedException {
        /*
         * Constructor of ReentrantLock Class ---
         * ReentrantLock l = new ReentrantLock();
         * ReentrantLock l = new ReentrantLock(boolean fairness);
         * fairness --- boolean value which is used to specify whether the lock should be fair or not
         *              if fairness is true then Longest Waiting Thread will get the lock
         *              if fairness is false then waiting threads will be granted lock in no specific order
         *              default value of fairness is false
         *
         * Methods of ReentrantLock Class ---
         * 1. int getHoldCount() --- Returns the number of holds on this lock by the current thread
         * 2. boolean isFair() --- Returns true if this lock has fairness set true
         * 3. boolean isHeldByCurrentThread() --- Queries if this lock is held by the current thread
         * 4. boolean isLocked() --- Queries if this lock is held by any thread
         * 6. boolean hasQueuedThreads() --- Queries whether any threads are waiting to acquire this lock
         * 7. int getQueueLength() --- Returns an estimate of the number of threads waiting to acquire this lock
         * 8. Collection<Thread> getQueuedThreads() --- Returns a collection containing threads that may be waiting to acquire this lock
         * 9. Thread getOwner() --- Returns the thread that currently owns this lock, or null if not owned
         */
        //Example to explain all reentrant lock methods
//        ReentrantLock l1 = new ReentrantLock();
//        l1.lock();
//        l1.lock();
//        l1.lock();
//        System.out.println(l1.getHoldCount()); //3
//        System.out.println(l1.isFair()); //false
//        System.out.println(l1.isHeldByCurrentThread()); //true
//        System.out.println(l1.isLocked()); //true
//        System.out.println(l1.hasQueuedThreads()); //false
//        System.out.println(l1.getQueueLength()); //0
//        l1.unlock();
//        l1.unlock();
//        l1.unlock();
//        System.out.println(l1.getHoldCount()); //0
//        System.out.println(l1.isFair()); //false
//        System.out.println(l1.isHeldByCurrentThread()); //false
//        System.out.println(l1.isLocked()); //false
//        System.out.println(l1.hasQueuedThreads()); //false
//        System.out.println(l1.getQueueLength()); //0
        ThreadGroup g = new ThreadGroup("Parent");
        Thread t1 = new MyThread(g, "Child1");
        Thread t2 = new MyThread(g, "Child2");
        Thread t3 = new MyThread(g, "Child3");
        Thread t4 = new MyThread(g, "Child4");
        Thread t5 = new MyThread(g, "Child5");
        g.setMaxPriority(5);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        Thread.sleep(1000);
    }
}

class MyThread extends Thread {
    ReentrantLock l = new ReentrantLock(true);

    public void run() {
        wish();
    }

    MyThread(ThreadGroup g, String name) {
        super(g, name);
    }

    public void wish() {
        l.lock();
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello world " + Thread.currentThread().getName());
        }
        l.unlock();
    }
}


