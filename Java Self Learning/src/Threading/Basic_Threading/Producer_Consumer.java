package Threading.Basic_Threading;

class Goods {
    private int n;

    Goods() {
        this.n = 0;
    }

    private boolean flag = false;

    public synchronized void getN() throws InterruptedException {
        while (flag) {//if flag is true then it means producer has produced something and consumer has not consumed it yet so consumer will wait
            try {
                wait();//wait statement will make the thread to wait until it is notified by the other thread i.e. Consumer Thread
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName() + " Consumed : " + n);
        flag = true;
        notify();
    }

    public synchronized void setN(int n) throws InterruptedException {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " Produced : " + n);
        this.n = n;
        flag = false;
        notify();
    }
}

class Producer implements Runnable {
    private final Goods goods;
    int i;

    public Producer(Goods goods) {
        this.i = 0;
        this.goods = goods;
        Thread t = new Thread(this, "Producer");
        t.start();
    }

    public void run() {
        while (true) {
            try {
                goods.setN(i++);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer implements Runnable {
    private final Goods goods;

    public Consumer(Goods goods) throws InterruptedException {
        this.goods = goods;
        Thread t = new Thread(this, "Consumer");
        t.start();
    }

    public void run() {
        while (true) {
            try {
                goods.getN();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Producer_Consumer {
    public static void main(String[] args) throws InterruptedException {
        Goods goods = new Goods();
        new Producer(goods);
        new Consumer(goods);
    }
}
