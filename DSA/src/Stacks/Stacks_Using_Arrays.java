package Stacks;

public class Stacks_Using_Arrays<T> {
    private final T[] st_Array;
    private int N = 0;

    Stacks_Using_Arrays(int size) {//predefined size
        st_Array = (T[]) new Object[size];
    }

    public void push(T data) {
        if (N == st_Array.length) {
            throw new ArrayIndexOutOfBoundsException("Stack is full");
        } else {
            st_Array[N++] = data;
        }
    }

    public T pop() {
        if (N <= 0) throw new ArrayIndexOutOfBoundsException("Stack is empty");
//        }else{
//            return st_Array[--N];//can create loitering effect where system still holds the object of the preivous element
//        }
        //to avoid loitering effect
        T temp = st_Array[--N];
        st_Array[N] = null;
        return temp;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }
}
