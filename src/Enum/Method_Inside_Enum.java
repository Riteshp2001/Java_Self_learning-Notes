package Enum;

public enum Method_Inside_Enum {
    A("Ritesh") {
        int i = 0;

        void loop() {
            while (i++ != 10) {
                System.out.println("A");
            }
        }

        public void m1() {
            System.out.println("A");
        }
    }, B("Ritesh"), C("Ritesh"), F("Ritesh");

    void loop() {
        int i = 0;
        while (i++ != 5) {
            System.out.println("B");
        }
    }

    String name;

    Method_Inside_Enum(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Method_Inside_Enum.A.loop();
        Method_Inside_Enum.B.loop();
    }
}
