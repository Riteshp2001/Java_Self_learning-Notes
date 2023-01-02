package Garbage_collection;

@interface DontDisturb {
    String since() default "";

    int test() default 1;
}


public class Requesting_GC_from_JVM {
    public static void main(String[] args) {
        //Ways for Requesting JVM to Run Garbage Collector
        //Once we made an object eligible for GC it may not be destroyed immediately by GC
        //Whenever JVM runs GC then only The objects are destroyed but when exactly JVM runs GC is not known to us

        //1. System.gc() : this is the only way to request JVM to run GC
        //2. Runtime.getRuntime().gc() : this is also the way to request JVM to run GC

        //We can create Runtime object by using Runtime.getRuntime() method
        //once we get runtime objects we can call following methods on that object
        Runtime r = Runtime.getRuntime();
        r.gc();
        r.totalMemory();//it returns number of bytes of total memory present in the heap(i.e. Heap Size);
        r.freeMemory();//it returns number of bytes of free memory present in the heap(i.e. Heap Size);
        r.gc();//For requesting JVM to run GC

        System.out.println("Total Memory : " + r.totalMemory() + " Bytes");
        System.out.println("Free Memory : " + r.freeMemory() + " Bytes");

        for (int i = 0; i < 10000; i++) {
            new Requesting_GC_from_JVM();
        }
        System.out.println("Free Memory after creating numerous objects : " + r.freeMemory() + " Bytes");

//        if(r.freeMemory() < 3000000){
//            System.out.println("Free Memory is less than 3 MB");
//            r.gc();
//        }else{
//            System.out.println("No need to run GC");
//        }
        r.gc();

        System.out.println("Free Memory after GC : " + r.freeMemory() + " Bytes");


        //Note:
        //it is continent to use System.gc() method when compared to Runtime class GC method
        //with respect to performance it is highly recommended to use runtime class GC method
        //when compares with System class GC method
        //because System class GC method internally calls Runtime class GC method
    }
}
