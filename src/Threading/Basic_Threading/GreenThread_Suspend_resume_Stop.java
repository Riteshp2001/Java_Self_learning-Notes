package Threading.Basic_Threading;

public class GreenThread_Suspend_resume_Stop {
    //Green Thread Model:
    //The Threads model in which the JVM handles the total Threads managemnet without taking Help of any Underlying OS support
    //this type of model is called Green Thread Model
    //Green Thread Model is also called as User Level Thread Model
    //Very few JVMs support this model like //SNU solaris JVM, Jikes RVM, J9 JVM, JRockit JVM, etc
    //Green Thread Model is Deprecated in Java 1.2 and is not recommended to use

    //Native OS Model:
    //The Thread which is managed by the JVM with the help of the Underlying OS is called Native OS Model
    //Native OS Model is also called as Kernel Level Thread Model
    //All the JVMs support this model
    //Native OS Model is recommended to use

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Child Thread");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.suspend();
        //suspend the thread t
        //Temporary pauses the execution of the thread t

        System.out.println("Main Thread");

        t.resume();//resume the thread t
        //Resumes the execution of the thread t
        t.stop();
        //stop the thread t and immediately goes into Death state
        //t.stop() is Deprecated in Java 1.2 and is not recommended to use as it may cause data inconsistency
        //t.stop() is replaced by t.interrupt() in Java 1.2
    }
}
