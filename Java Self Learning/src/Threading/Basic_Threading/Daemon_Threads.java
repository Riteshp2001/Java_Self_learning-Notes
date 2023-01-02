package Threading.Basic_Threading;

public class Daemon_Threads extends Thread {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Child());
        t.setDaemon(true);//THread Should be Set Daemon before starting If it is Set after Starting Thread Then RE : IllegalThreadState Exception will be thrown
        t.start();
        Thread.sleep(1000);
        //Daemon threads will only run until main threads run after main threads reaches end statement and ends program
        //Daemon Threads will end its execution phase and Program will Terminate
        //Main Thread Will always be Non-daemon thread as it is created at Runtime by JVM and State of Threads Cannot be changed

        //Daemon property of the Threads Follows Inheritance prperty If Parent has Set Daemon thread state as true then child thread will also have its Daemon state as true;
        System.out.println("Main Thread Ends");//Child Thread May or May not be printed
    }
}

class Child extends Daemon_Threads {
    public void run() {
        if (Thread.currentThread().isDaemon()) {
            System.out.println("As We have declared Thread t as Daemon here also it is Daemon Thread");
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Child Thread is Gonna Exit");//This will or will not execute will depend
    }

}
