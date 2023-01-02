package Stacks;

public class Stacks_Using_Resizingarray<T> {
    private T[] st_Array;
    private int N = 0;

    Stacks_Using_Resizingarray(int size) {//predefined size
        st_Array = (T[]) new Object[size];
    }

    public void resize(int capacity) {
        T[] copy = (T[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = st_Array[i];
        }
        //or if (N >= 0) System.arraycopy(st_Array, 0, copy, 0, N);
        st_Array = copy;
    }

    public void push(T data) {
        if (N == st_Array.length) {
            resize(2 * st_Array.length);
        }
        st_Array[N++] = data;
    }

    public T pop() {
        if (N <= 0) throw new ArrayIndexOutOfBoundsException("Stack is empty");
//        }else{
//            return st_Array[--N];//can create loitering effect where system still holds the object of the preivous element
//        }
        //to avoid loitering effect
        T temp = st_Array[--N];
        st_Array[N] = null;
        if (N > 0 && N == st_Array.length / 4) {//if array is 1/4 full then resize it to 1/2 of its size toe
            resize(st_Array.length / 2);
        }
        return temp;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }
}

//Results
//if we need Amortized time or like average time to do operations on stack then we can use resizing array
//its a tradeoff between time and space
//we will need less space in resizing array
//push operation in worst case will take O(n) time as we need to resize the array to double its size
//pop operation in worst case will take O(n) time as we need to resize the array to half its size when array is 1/4 full
