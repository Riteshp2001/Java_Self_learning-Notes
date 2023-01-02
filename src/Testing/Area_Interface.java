package Testing;

interface Area {
    void circle(int r);

    void rect(int l, int b);
}

class Rectangle implements Area {
    @Override
    public void circle(int r) {

    }

    public void rect(int l, int b) {
        System.out.println("Area of rect is:" + (l * b));
    }
}

class Circle implements Area {
    public void circle(int r) {
        System.out.println("Area of circle is:" + (2 * 3.14 * r));
    }

    @Override
    public void rect(int l, int b) {

    }
}

public class Area_Interface {
    public static void main(String[] args) {
        Rectangle r = new Rectangle();
        r.rect(10, 20);
        Circle c = new Circle();
        c.circle(20);
    }
}
