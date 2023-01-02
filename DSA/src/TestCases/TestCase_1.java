package TestCases;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class TestCase_1 {
    static class A {
        int a = 100;

        static void m1() {
            System.out.println("A.");
        }
    }

    static class B extends A {
        int a = 200;

        static void m1() {
            System.out.println("B.");
        }
    }

    static class C extends B {
        int a = 300;

        static void m1() {
            System.out.println("C.");
        }
    }

    void m1() {
        m2();
    }

    void m2() {
        m1();
    }

    //    static  int i = 10/0;
    public static void main(String[] args) throws FileNotFoundException {
        TestCase_1 t = new TestCase_1();
//        C c = new C();
//        c.m1();
//        ((B)c).m1();
//        ((A)((B)c)).m1();

//        try(Scanner sc = new Scanner(System.in)){//try with resources -> try(R){} -> R should be AutoCloseable
//            System.out.println(10);
//            try {
//                System.out.println(10/0);
//            }catch (ArithmeticException e){
//                System.out.println("Inner Catch");
//                System.exit(0);
//            }finally {
//                System.out.println("Inner Finally");
//            }
//        }catch (ArithmeticException e) {
//            System.out.println("ArithmeticException");
//        }catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            System.out.println("Finally");
//            //no need to close the resources as it is closed automatically by try with resources block
//        }

        try (Scanner sc = new Scanner(System.in); Scanner sc1 = new Scanner(System.in); PrintStream p = new PrintStream("test")) {
//            sc = new Scanner(System.lineSeparator());//sc value cannot be reassigned/changed as it is final
        }
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        PrintStream ps = new PrintStream("D:\\DSA\\src\\TestCases\\output.txt");
        try (sc; sc1; ps) {
        }

        //try block with multiple resources
        //try with resources -> try(R1,R2,R3){} -> R1,R2,R3 should be AutoCloseable
        //try with resources , Resources are implicitly final it cannot be reassigned
        //the biggest advantage of try with resources is that we don't need to close the resources explicitly
        //try with resources block will close the resources automatically
        //try with resources block will close the resources in reverse order
        //try with resources block will close the resources even if there is an exception,System.exit(0),return,throw,break,continue,goto in try block
        //try with resources block will close the resources even if there is an exception,System.exit(0),return,throw,break,continue,goto in catch block
        //try with resources block will close the resources even if there is an exception,System.exit(0),return,throw,break,continue,goto in finally block

//       System.out.println(i);
    }
}

