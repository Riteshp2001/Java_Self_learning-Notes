package Hashtable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Table<E> {
    List<List<E>> list;

    public Table() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new LinkedList<>());
        }
    }

    public void put(E elem) {
        int idx = elem.hashCode() % 10;
        list.get(idx).add(elem);
    }

    public List<E> getChain(int index) {
        return list.get(index);
    }

    public static void main(String[] args) {

        Table<Integer> t = new Table<>();

        t.put(4);
        t.put(5);
        t.put(6);
        t.put(4);
        t.put(7);

        System.out.println(t.getChain(6));
    }
}
