package Garbage_collection;

public class Finalization {

    //just before destroying an object GC calls Finalize method to beform cleanup activities like
    //Closing Connections / Databases
    //Once finalize method completes automatically GC destroyes that object and releases Heap Memory
    //Finalise method present in Object Class with the following Declaration
    //we can override finalise method in out class to define our own cleanup activities
    @Override
    protected void finalize() throws Throwable {
        for (int i = 0; i < 3; i++) System.out.println("Hello World");
        System.out.println("Finalise Method is Called");
        super.finalize();
    }

    public static void main(String[] args) throws Throwable {
        //Case 1: Just before Destroying object GC calls finalize() method on the object which is eligible for GC
        //        Then the corresponding class finalise method will be executed
        //Example
        //If string object eligible for GC then string class finalise method will be executed but not Current classs finalise method

        Finalization s = new Finalization();//Finalise Gets called when we pass this as Object is Finalization so GC calls Finalise in Finalization which we have provided
        //output : Finalise Method is Called
        //         Main has Completed

        // String s = new String("Hello");//Finalise gets called on String object and not Finalization object;
        //output : Main has Completed

        s = null;
        System.gc();
        System.out.println("Main has Completed");


        //Case 2. Based on our requirement we can call finalise method explicitly then it will be executed just like normal method call
        //        and object wont be destroyed
        //Example
        Finalization f = new Finalization();
        f.finalize();//runs like normal method
        //NOTE:
        //1. If exception rises in finalise method while using it as normal method then it need to be handeled explicitly
        //2. If exception rises in finalise method while GC is performing CleanUp activities then JVM ignores that Exception message and rest of program executes normally


        //Case 3: On any object GC calls **finalize method** only once even though object eligible for GC multiple times
        Finalize_Case_3 finalizeCase3 = new Finalize_Case_3();
        System.out.println(finalizeCase3.hashCode());
        finalizeCase3 = null;
        System.gc();//Finalise method gets called on finalizeCase3 object
        Thread.sleep(1000);
        System.out.println(Finalize_Case_3.s.hashCode());
        Finalize_Case_3.s = null;
        System.gc();//this time GC automatically deletes s object cause its same object and finalize method doesn't run
        Thread.sleep(3000);
        System.out.println("Main Thread Ends");

        //Case 4. We cant expect exact behavior of Garbage Collector it is varied from JVM to JVM
        //        hence for the following questions we cant provide exact answers
        //1) when exactly JVM runs GC ?(whenever programs runs with low Memory but we cant expect at what time this happens)
        //2) in which order GC destroyes Eligible objects ?
        //3) wheather GC destroyes all eligible objects or not
        //4) what is algorithm followed by GC ? etc...(Most of GC follow Standard Algorithm i.e Mark and Sweep Algorithm)

        //Demonstration of when GC is called
        Finalize_Case_4 f4 = new Finalize_Case_4();
        System.out.println("\n\n Finalize Method Called by GC Demonstration :");
        for (int i = 0; i < 100000; i++) {
            Finalize_Case_4 finalizeCase4 = new Finalize_Case_4();
        }


        //Case 5. Memory Leaks:
        // Objects which are not used in our program and which are not eligible for GC such types of useless objects are called
        //Memory Leaks
        //In our program if memory Leaks present then Program will be terminated by rising : OutOfMemoryError
        //if the object is not required it is highly recommended to make that object eligible for GC
        //if not its Programmers Mistake

        //Third-Party Memory Management Tools to Check Memory Leakage :
        //-- HP OVO
        //-- HP J Meter
        //-- Jprobe
        //-- Patrol
        //-- IBM Trivoli
    }


    static class Finalize_Case_3 {
        static Finalize_Case_3 s;

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalise Method Called");
            s = this;
        }
    }

    static class Finalize_Case_4 {
        static int count = 0;

        protected void finalize() throws Throwable {
            System.out.println("Finalise Method count : " + ++count);
        }
    }


    //NOTE:
    //init , service , destroy methods are considered as lifecycle methods of servlet
    //just before destroying servet object web container calls destroy method to perform cleanup activities
    //but based on out requirement we can call destroy method from init and service methods, then destroy method will be executed just like normal method call and servlet object wont be destroyed
}
