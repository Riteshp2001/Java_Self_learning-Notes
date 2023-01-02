package TestCases;

import java.util.LinkedList;

public class Producer_Consumer {
    private final LinkedList<Integer> goods;
    private final int capacity;

    public Producer_Consumer(int capacity) {
        this.goods = new LinkedList<>();
        this.capacity = capacity;
    }

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (goods) {
                // Wait until the goods list is not full
                while (goods.size() == capacity) {
                    goods.wait();
                }

                System.out.println("Producing " + value);
                goods.add(value++);

                // Notify the consumer thread that a new good is available
                goods.notify();
                Thread.sleep(100);
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (goods) {
                // Wait until the goods list is not empty
                while (goods.size() == 0) {
                    goods.wait();
                }

                int value = goods.removeFirst();
                System.out.println("Consuming " + value);

                // Notify the producer thread that there is space in the goods list
                goods.notify();
                Thread.sleep(100);
            }
        }
    }

    public static void main(String[] args) {
        Producer_Consumer pc = new Producer_Consumer(5);

        Thread t1 = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
