package Practice;

@FunctionalInterface
interface test {
    void fibo(int n);
}

public class ASQI implements test, Runnable {

    public static void main(String[] args) {
        // lambda expression is an anonymous function which does not require any name, return type as lambda function is smart enough to check the return type from the interface which we have mentioned
        //Lambda function can only be used on the interface which has one and only one abstract method in it
        //Lambda expression
        test fibo = (int n) -> {
            int i = 0, j = 1, sum = 0;
            System.out.print(i);
            while (n != 0) {
                i = j;
                j = sum;
                sum = i + j;
                System.out.print(" " + sum);
                n--;
            }
        };
        Thread t = new Thread(new ASQI());
        t.start();//traditional way

        //lambda expression way
        Runnable runnable = () -> System.out.println("run is running");
//        Thread x = new Thread(runnable);
        //or
        Thread x = new Thread(() -> System.out.println("run is running"));
        x.start();
    }

    @Override
    public void fibo(int n) {
        int i = 0, j = 1, sum = 0;
        System.out.print(i);
        while (n != 0) {
            i = j;
            j = sum;
            sum = i + j;
            System.out.print(" " + sum);
            n--;
        }
    }

    @Override
    public void run() {
        System.out.println("run is running");
    }
}
