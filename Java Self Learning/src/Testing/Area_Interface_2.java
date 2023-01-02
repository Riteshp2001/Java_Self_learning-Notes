package Testing;

interface calc {
    void area(int r);

    default void name(String n) {//here default acts like concrete method in interface we can give user access t o any information oin interface using default
        System.out.println("My name is " + n);
    }

    ;
}

class Square implements calc {
    private int r;

    void Square(int r) {
        this.r = r;
    }

    public void area(int r) {
        System.out.println("Area of square is " + (r * r));
    }

}

public class Area_Interface_2 {
    public static void main(String[] args) {
        Square s = new Square();
        s.name("Ritesh");
        s.area(4);
    }
}
