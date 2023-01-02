package Testing;

public class Interface implements Interface_2, temp {
    public void disp() {
        System.out.println("Implementation");
    }

    public static void main(String[] args) {
        Interface i = new Interface();
        i.disp();
    }
}

interface Interface_2 {
    void disp();
}

interface temp {
    void disp();
}