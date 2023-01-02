package Stacks;

public class Stack_using_LinkedList<T> {
    private Node<T> head = null;

    private class Node<T> {
        T data;
        Node<T> next;
    }

    public void push(T data) {
        Node<T> newNode = head;
        head = new Node<T>();
        head.data = data;
        head.next = newNode;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public T pop() {
        if (head != null) {
            T temp = head.data;
            head = head.next;
            return temp;
        }
        return null;
    }

    public void printStack() {
        Node<T> curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    public int size() {
        Node<T> curr = head;
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    public static void main(String[] args) {
        Stack_using_LinkedList<String> s = new Stack_using_LinkedList<String>();
        s.push("thanks you");
        s.push("tyhdiji");
        s.push("t");
        s.pop();
        s.printStack();
    }
}

//if we need constant time to do operations on stack then we can use linked list
//its a good choice when we don't know the size of the stack
//we will need O(n) space in linked list
//push operation in worst case will take O(1) time
//pop operation in worst case will take O(1) time