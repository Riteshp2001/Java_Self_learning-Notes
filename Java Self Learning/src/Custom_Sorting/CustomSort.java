package Custom_Sorting;

import java.util.Comparator;
import java.util.TreeMap;

class Marks {
    private final String name;
    private final int marks;

    Marks(String name, int marks) {
        this.marks = marks;
        this.name = name;
    }

    public int getMarks() {
        return this.marks;
    }

    public String getName() {
        return this.name;
    }
}

class Custom_SortPart implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Marks m1 = (Marks) o1;
        Marks m2 = (Marks) o2;
        if (m1.getMarks() > m2.getMarks()) {
            return -1;
        }
        return 1;
    }
}

public class CustomSort {
    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<String, Integer>(new Custom_SortPart());
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);
        for (String key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }
    }
}
