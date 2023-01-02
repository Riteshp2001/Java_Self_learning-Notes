package Threading.Advance_Threading;

public class ThreadGroups extends Thread {

    //We can group a Set of Threads into a single group using ThreadGroup class
    //i.e. ThreadGroup Contains a group of Child Threads , in Addition to Threads ThreadGroup can also contain
    //Sub-ThreadGroups means Groups under Groups
    //Main Advantage of Maintaining threads in a form of thread-groups is :
    //We can perform Common operations very easily

    //Every Thread in Java belongs to some ThreadGroup

    //        Important:
    //        Every ThreadGroup is a Child Group of System directly or indirectly
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getThreadGroup().getName());//Main
        System.out.println(Thread.currentThread().getThreadGroup().getParent());//System

        //----------------------------------//
        ThreadGroup t = new ThreadGroup("Sigma Thread");//this thread-group will be child thread of Main ThreadGroup
        System.out.println(t.getParent().getName());//Main
        ThreadGroup t1 = new ThreadGroup(t, "Beta Group");
        System.out.println(t1.getParent().getName());//Sigma Thread


        //----------------------------------//
        ThreadGroup t3 = new ThreadGroup("Parent");
        System.out.println(t3.getMaxPriority());
        Thread a1 = new Thread(t3, "child thread-1");
        Thread a2 = new Thread(t3, "child thread-2");
        t3.setMaxPriority(2);
        Thread a3 = new Thread(t3, "child thread-3");
        System.out.println(a1.getPriority());//5
        System.out.println(a2.getPriority());//5
        System.out.println(a3.getPriority());//2

        //----------------------------------//
        //Constructors of ThreadGroup:
        /*
         * ThreadGroup t = new ThreadGroup(ThreadGroup g, String Name);
         * ThreadGroup t = new ThreadGroup(ThreadGroup g, String Name);
         *
         */
        //ThreadGroup Methods
        /*
         * String getName(); | returns name of ThreadGroup
         *
         * int getMaxPriority(); | returns Max priority of ThreadGroup
         *
         * void setMaxPriority(int priority); | sets Max Priority of ThreadGroup (Default Max_Priority is 10)
         *
         * ThreadGroup getParent(); | returns parent group of current thread
         *
         * void list();  | prints information about thread-group to console
         *
         * int activeCount(); | returns number of active threads present in thread-group
         *
         * int activeGroupCount(); | returns number of active Groups present in the Current thread-group
         *
         * int enumerate(Thread[] t); | Copy all active threads of this thread-group into provided Thread-Array in this SubThread groups will also be considered
         *
         * int enumerate(ThreadGroup[] g); | Copy all active thread-groups of this thread-group into provided Thread group-Array
         *
         * boolean isDaemon(); | check whether ThreadGroup is Daemon or not
         *
         * void setDaemon(boolean b); | Change Daemon Nature of ThreadGroup
         *
         * void interrupt(); | To interrupt all waiting / sleeping threads in ThreadGroup
         *
         * void destroy(); | to kill all the Threads, and it's sub ThreadGroups present in the ThreadGroup
         */

        //----------------------------------//
        ThreadGroup pg = new ThreadGroup("Parent group");
        ThreadGroup cg = new ThreadGroup(pg, "Child group");
        Thread p1 = new ThreadGroup_Child(pg, "Child1");
        Thread p2 = new ThreadGroup_Child(pg, "Child2");
        p1.start();
        p2.start();
        System.out.println(pg.activeCount());//2 --> active threads are p1 and p2
        System.out.println(pg.activeGroupCount());//1
        pg.list();
        Thread.sleep(5000);
        System.out.println(pg.activeCount());//0 --> all child threads are terminated before reaching this statement
        System.out.println(pg.activeGroupCount());//1
        pg.list();

        //----------------------------------//
        //Write a program to Display all active Thread and ThreadGroup names Belongs to System group and its child groups

        ThreadGroup system = Thread.currentThread().getThreadGroup().getParent();
        //For all active threads and active Thread-groups
        Thread[] threadArray = new Thread[system.activeCount()];
        system.enumerate(threadArray);
        System.out.println("All System Group Active Thread Names");
        for (Thread i : threadArray) {
            System.out.println(i.getName() + " - " + i.isDaemon());
        }

        //For ThreadGroup
        ThreadGroup[] threadGroupArray = new ThreadGroup[system.activeGroupCount()];
        system.enumerate(threadArray);
    }

    //Threads in ThreadGroup that already have higher priority won't be affected by setMaxPriority method
    //but for newly added thread this max priority is applicable
}

class ThreadGroup_Child extends Thread {
    ThreadGroup_Child(ThreadGroup t, String name) {
        super(t, name);
    }

    public void run() {
        System.out.println("Child Thread - " + Thread.currentThread().getName() + " Started ...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Child Thread - " + Thread.currentThread().getName() + " Terminated ...");
    }
}
