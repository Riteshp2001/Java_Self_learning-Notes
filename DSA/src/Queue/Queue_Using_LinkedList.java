package Queue;

public class Queue_Using_LinkedList<T> {
    Node<T> first, last;

    private static class Node<T> {
        T data;
        Node<T> next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(T data) {
        Node<T> newNode = last;
        last = new Node<>();
        last.data = data;
        last.next = null;
        if (isEmpty()) first = last;
        else newNode.next = last;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        } else {
            T temp = first.data;
            first = first.next;
            return temp;
        }
    }

    public void peek() {
        if (isEmpty()) {
            System.out.println("null");
        } else {
            System.out.println(first.data);
        }
    }

    public void printQueue() {
        Node<T> temp = first;
        System.out.print("[ ");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.print("]");
    }
}
