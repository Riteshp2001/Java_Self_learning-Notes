package Queue;

public class Queue_UsingArray<T> {
    T[] queue;
    int n = queue.length;
    int first = -1;
    int last = -1;

    Queue_UsingArray(int data) {
        queue = (T[]) new Object[data];
    }

    public boolean isEmpty() {
        return last == -1;
    }

    public void resize(int size) {
        T[] copy = (T[]) new Object[2 * size];
        for (int i = 0; i < size; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    public void enqueue(T data) {
        if (last == n - 1) {
            resize(queue.length);
        }
        ;
        if (last == -1) last++;
        queue[last++] = data;
    }

    public T dequeue() {
        if (first == -1) first = 0;
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
}
