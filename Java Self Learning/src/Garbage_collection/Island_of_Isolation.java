package Garbage_collection;

public class Island_of_Isolation {
    static class Test {
        Test i;
    }

    ;

    public static void main(String[] args) {
        Test t1 = new Test();
        Test t2 = new Test();
        Test t3 = new Test();
        t1.i = t2;
        t2.i = t3;
        t3.i = t1;
        t1 = null; //Step 1 : t1,t2,t3 is not eligible for GC when t1 is nullified
        //all are still eligible cause we can still access object using
        //t2.i.i and t3.i
        t2 = null; //Step 2 : t1,t2,t3 is not eligible for GC when t2 is nullified
        //all are still eligible cause we can still access object using
        //t3.i and t3.i.i
        t3 = null; //Step 3 : t1,t2,t3 are now eligible for GC when t3 is also nullified
        //all are now GC cause we can't access any object now cause all 3 are pointing to null

        //Conclusion :
        //1. If any object doesn't contain any reference variable then that object is eligible for GC
        //2. even though object contains reference variable still sometimes it is eligible for garbage collection(if all references are internal references)
        //   best Example is Island of Isolation
    }
}
