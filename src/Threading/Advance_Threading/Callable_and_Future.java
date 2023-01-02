package Threading.Advance_Threading;

import java.util.concurrent.*;

public class Callable_and_Future {

    //In the case of Runnable Job thread wont return anything after completing the job
    //if a thread is required to return some result after execution then we should go for callabble

    //Callable Interface Methods:
    //callable interface contains only one method call
//    @Override
//    public Object call() throws Exception{
//
//    }

    //if we Submit callable object to executor then after completing the job Thread returns an object of type Future
    //i.e. Future object can be used to retrive the result from callable jobs

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService e = Executors.newFixedThreadPool(3);
        MyThreadCalls[] array = new MyThreadCalls[]{
                new MyThreadCalls(10),
                new MyThreadCalls(20),
                new MyThreadCalls(30),
                new MyThreadCalls(40),
                new MyThreadCalls(50),
                new MyThreadCalls(60),
                new MyThreadCalls(70),
        };

        for (MyThreadCalls thread : array) {
            Future f = e.submit(thread);
            thread = null;
            System.out.print(f.get() + "\n");
        }

        e.shutdown();
        e.close();
    }
}

class MyThreadCalls implements Callable {
    private int num = 0;

    public MyThreadCalls(int n) {
        num = n;
    }

    @Override
    public Object call() throws Exception {
        System.out.print(Thread.currentThread().getName() + " is Executing - The sum of Given number " + num + " is :");
        return num * (num + 1) >> 1;
    }
}