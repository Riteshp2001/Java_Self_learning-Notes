package Threading.Advance_Threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPools {
    //Creating a new thread for every job can create performance and memory problems
    //to overcome this we should go for thread pool
    //Threadpool is a pool of already created threads reasy to do our job
    //Java 1.5 version introduces Thread pool framework to implement threadpools
    //Thread Pool framework is also known as Executor Framework
    public static void main(String[] args) {

        //We can create a threadpools as Follows :
        ExecutorService executorService = Executors.newFixedThreadPool(1);//1 thread are responsible to execute 6 jobs
        //ExecutorService executorService = Executors.newFixedThreadPool(3);//3 thread are responsible to execute 6 jobs

        //We can submit a Runnable Job by using Submit method
        //executorService.submit(Runnable Job);
        //We can Shutdown Executor service by using Shutdown method
        //executorService.shutdown();

        PrintJob[] array = new PrintJob[]{
                new PrintJob("Ritesh"),
                new PrintJob("govind"),
                new PrintJob("suresh"),
                new PrintJob("Udhyam"),
                new PrintJob("aakash")
        };

//        for(int i = 0 ; i<array.length;i++){
//            executorService.submit(array[i]);
//        }
        //or
        for (PrintJob printJob : array) {
            executorService.submit(printJob);
            printJob = null;
        }

        executorService.shutdown();
        executorService.close();
    }
}

class PrintJob implements Runnable {
    String name;

    PrintJob(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + ".. job started by Thread : " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name + "...Job Completed ");
    }
}
