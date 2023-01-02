package Comparator;

import java.util.Comparator;
import java.util.TreeSet;

public class Comparator_Sorting {
    public static void main(String[] args) {
        TreeSet t = new TreeSet(new Comparings());
        t.add(1);
        t.add(18);
        t.add(12);
        t.add(18);
        t.add(91);
        System.out.println(t);
    }

    static class Comparings implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            Integer i1 = (Integer) o1;
            Integer i2 = (Integer) o2;

//            Various types of return statements
//            return +1;                //return original order of inputted elements
//            return -1;                //return reverse order of inputted elements
//            return 0;                 //only first element will be inserted rest all will be duplicate
//            return i1.compareTo(i2);  //return Ascending sorted order
//            return i2.compareTo(i1);  //return Descending sorted order
//            return -i1.compareTo(i2); //return Descending sorted order
            return -i2.compareTo(i1); //return Ascending sorted order
        }
    }
}

//method 1
//public int compare(obj1,obj2)
//returns -ve if obj1 has to come before obj2
//returns +ve if obj1 has to come after obj2
//returns 0   if obj1 is equals obj2

//method 2
//public boolean equals(object obj)

/*whenever we are implementing comparator interface compulsory we have to provide implementation only for compare method,
and we are not required to provide implementation for equals method as it is automatically present in the created class through inheritance as it is child of our defined class*/
