package Threading.Basic_Threading;


public class Infinite_Waiting extends Thread {
    Thread t = new Thread(this);

    public static void main(String[] args) throws InterruptedException {
        new Infinite_Waiting().t.start();
        System.out.println("Main Thread Running now calling run method");
        new Infinite_Waiting().t.join();
        System.out.println("This is the end of the main thread DeadLock Achieved");//main thread ends here now no one is able to start run method again
        //run thread goes into infinite wait
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Infinitely Waiting");
            try {
                Thread.sleep(1000);
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("End of Run method");
        }
    }
}
