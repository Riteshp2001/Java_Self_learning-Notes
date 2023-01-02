package Threading.Basic_Threading;

public class Stop_Thread implements Runnable {
    private boolean stopThread = false;

    public boolean isStopThread() {
        return stopThread;
    }

    public void setStopThread(boolean stopThread) {
        this.stopThread = stopThread;
    }

    public void run() {
        System.out.println("Thread Running " + Thread.currentThread().getName());
        while (!isStopThread()) {
            sleep(1000);
            System.out.println("...");
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " Interrupted/Stopped.");
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Stop_Thread s = new Stop_Thread();
        Thread t = new Thread(s, "Thread 1");//creating thread and passing object of class and thread name into it
        t.start();
        Thread.sleep(5000);//making main thread sleep for 5 seconds
        System.out.println("Stopping Thread 1 will see one more ... cause stop request is sent just now");
        s.setStopThread(true);//setting stopThread to true after 5 seconds,this main thread will again continue parallel with the thread 1 and will set the thread 1 to stop
        Thread.sleep(1000);//making main thread sleep for 1 second
        System.out.println("Thread 1 Stopped");//before printing this control will go over to thread t for printing last statement in the function

        Runnable r = () -> {
            while (true) {
                System.out.println("Thread Running name -> " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread t1 = new Thread(r, "Thread 2");
        t1.setDaemon(true);//setDaemon means thread will only run util main thread is running after main thread is stopped this thread will also be stopped
        t1.start();
        Thread.sleep(5000);
        System.out.println("Stopping Thread 2 as it is daemon(Main Thread) thread");
    }
}
