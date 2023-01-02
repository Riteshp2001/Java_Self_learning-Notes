package Garbage_collection;

import java.util.Comparator;


public final class Garbage_Test implements Comparator {
    int i = 10;

    @Override
    protected void finalize() throws Throwable {
        try {
            System.out.println("Garbage_Test finalize");
            int i = 10 / 0;
        } catch (ArithmeticException e) {
            throw new RuntimeException(new ArrayIndexOutOfBoundsException());//Ignored
        }
    }

    public static void main(String[] args) throws Throwable {
        Garbage_Test s = new Garbage_Test();
        s.finalize();
        s = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println("Main has Completed");


        //How to Make Object Eligible for Garbage Collection

        //Case 1. Nullifying object Reference
        //if an object no longer required then assign null to all its reference variables then that object automatically eligible for garbage collection
        //String s = new String("RR");
        //s = null;

        //Case 2. Reassigning object reference
        //if object is no longer required then reassign its reference variable to some other object then whole previous
        //object by default eligible for garbage collection
        //Student s1 = new Student("ram");
        //Student s2 = new Student("shyam");
        //s1 = new Student("mihir");
        //or
        //s2 = s1;

        //Case 3. Defining Objects inside Method
        //Objects which are created inside a method are eligible for GC once method is completed cause those Objects act like
        //local objects which are inside method and when method ends all local variables automatically gets destroyed by GC
        //psvm(String[] args){
        //m1();
        // }
        //void m1(){
        //Student s1 = new Student("ram");
        //Student s2 = new Student("shyam");
        //}


    }

    @Override
    public int compare(Object o1, Object o2) {
        Garbage_Test g1 = (Garbage_Test) o1;
        Garbage_Test g2 = (Garbage_Test) o2;
        if (g1.i == g2.i) {
            return 0;
        } else if (g1.i > g2.i) {
            return 1;
        } else if (g1.i % 10 > g2.i % 10) {
            return 1;
        } else if (g1.i % 10 < g2.i % 10) {
            return -1;
        } else if (g1.i % 10 == g2.i % 10) {
            return -1;
        } else if (g1.i % 100 < g2.i) {
            return -1;
        } else return 1;
    }
}