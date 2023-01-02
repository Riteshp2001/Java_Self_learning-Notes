package Queue;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<T> implements Iterable<T> {

    private T[] queue;
    private int first = 0, last = -1, count = 0;
    Iterator<T> iterator;

    RandomizedQueue(int size) {
        queue = (T[]) new Object[size];
        iterator = iterator();
    }


    public T randomize() {
        return queue[(int) (Math.random() * (count))];
    }

    public boolean isEmpty() {
        return last == -1;
    }


    public void enqueue(T data) {
        if (last == queue.length - 1) {
            resize(queue.length);
        }
        ;
        if (last == -1) last++;
        queue[last++] = data;
        count++;
    }

    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        T temp = queue[first];
        for (int i = 0; i < last; i++) {
            queue[i] = queue[i + 1];
        }
        queue[last] = null;
        last--;
        if (last == queue.length / 4) {
            resize(queue.length / 2);
        }
        return temp;
    }

    public void resize(int size) {
        T[] curr = (T[]) new Object[2 * size];
        for (int j = 0; j < size; j++) {
            curr[j] = queue[j];
        }
        queue = curr;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Queues<T>();
    }

    private class Queues<t> implements Iterator<t> {
        int i = first;

        public boolean hasNext() {
            if (isEmpty()) throw new IllegalStateException("Empty queue");
            return queue[i] != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public t next() {
            if (!hasNext()) throw new NoSuchElementException();
            t data = (t) queue[i];
            i++;
            return data;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>(5);
        q.enqueue(1);
        q.enqueue(3);
        q.enqueue(5);
        q.enqueue(90);
        System.out.println(q.isEmpty());
        System.out.println(q.randomize());
        while (q.iterator.hasNext()) {
            System.out.println(q.iterator.next());
        }
    }
}
