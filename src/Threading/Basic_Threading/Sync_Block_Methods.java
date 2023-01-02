package Threading.Basic_Threading;

public class Sync_Block_Methods {
    public synchronized void Method1() {
        // This is known as Method Synchronization or Synchronized Method or Synchronized Method Modifier
        // It is not Recommended to use Synchronized Method Modifier because it will lock the whole method and,
        // it will not allow any other thread to access the method until the current thread is not finished with the method.
        //unless you are sure that the method will not take much time to execute then you can use Synchronized Method Modifier.
    }

    public static synchronized void Method3() {
        //this is also known as Class Level Synchronization or Synchronized Static Method
        //So any Thread will not be able to execute this method until the current thread is not finished with the method.
        //it's like a lock on the class.
        //even if we create different objects of the class, the lock will be on the class.so those different object threads
        //will not be able to execute this method until the current thread is not finished with the method.
    }


    public void Method2() {
        synchronized (this) {//To Get Lock on the Current Object

            // Here this keyword refers to the current object of the class.
            // This is known as Block Synchronization or Synchronized Block or Synchronized Block Modifier
            // It is Recommended to use Synchronized Block Modifier because it will lock the block of code and not whole method
            //performance wise it is better than Synchronized Method Modifier.
        }
        synchronized (new Object()) {//To Get Lock on the New Object
        }
        synchronized (Sync_Block_Methods.class) {//To Get Lock on the Class
        }

//        Conclusion:
        //synchronized block is not available for primitives and only for objects. otherwise we will get CE:Incompatible types
    }

    public static void main(String[] args) {

    }

}

class X {
    public synchronized void m1() {//here Thread has lock of X
        Y y = new Y();
        synchronized (y) {//here Thread has acquired lock of Y
            //---here Thread has lock of X and Y
            synchronized (this) {//here Thread has acquired lock of X
                //---here Thread has lock of X, Y and X
                Z z = new Z();
                synchronized (z) {//here Thread has acquired lock of Z
                    //---here Thread has lock of X, Y, X and Z
//                    synchronized (new Object()){}....
                }
            }
        }
    }

    class Y {
    }

    class Z {
    }

//    Conclusion:
//    Thread can have multiple locks in it at a time.
}


//what is Synchronized Statement?
//Interview people created this Terminology to confuse you.
//The statements present in synchronized block or methods are called Synchronized Statements.