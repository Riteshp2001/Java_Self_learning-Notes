package Testing;

import java.util.Scanner;

interface Banking {
    void deposit();

    void withdraw();

    void check();

    void slip();
}


class input {
    private int dep = 0;

    public int getDep() {
        return dep;
    }

    public void setDep(int dep) {
        if (dep > 10 && dep < 10000) {
            this.dep += dep;
        } else {
            System.out.println("Error Enter Amount between 11 and 99999 : ");
        }
    }
}

class output extends input {
    int dec = 0;
    Scanner sc = new Scanner(System.in);

}

public class Banking_System {
    public static void main(String[] args) {

    }
}