package Basics;

public class Test {

    static int num = 10;//Global Variable,Instance Variable

//public: Modifier , class: Prototype of a System, Test:Java file name

/*Datatypes in Java

Primitive(Ancient) Datatypes:
8 bits = 1 byte

Total Range = 2^n; Where n = no of bits;

byte (1 byte === 8 bit),  -128 < range < 127 => 2^8 = 256 ; At 256 value of byte will start from 0
short (2 byte === 16 bit)   -32,768 < range < 32,767 ==> 2^16 =65,536; At 65,536 value of short will start from 0
int (4 byte === 32 bit)   -2^31 < range < 2^31 - 1
long (8 byte === 64 bit)   -2^64 < range < 2^64 - 1
char (2 byte === 16 bit)  -infinity < range < infinity
float
double
boolean (1 byte === 8 bit)    True or False

TypeCasting ->
Widening(Automatic) : byte -> short -> char -> int -> long -> float -> double
Narrowing (Manually) : byte <- short <- char <- int <- long <- float <- double
*/

    public static void main(String[] args) {
/*
public : Access Modifier
static : Code Becomes Faster and Easier to Accesses , Static variable always initializes at Runtime
void   : returns No value
main   : main Java Function where code Execution Starts
()     : function Passed Parameter
String : Datatype which contains Words
[]     : Array
args   : just a casual name Can give any name of your choice but why Args? just for representation
*/

//Code Starts from here

        Test t = new Test();
//        " ; " ---> end of statement
        byte a;//Variable Declaration
        a = (byte) 255;//Variable Initialization
        short b;
        b = (short) 65536;//typecast because it is overflowing in short and byte range matlab uske range se bahar chala gaya
        int c;
        c = 2147483647;
        long d = 1000000000000000000L;
        char e = 122;
        float f = 1.99999999f;//After decimal only 6 places float supports
        double g = 2.76599999999;//after decimal double supports 17 places
//        int h = (int)f;//Integer prints only floor values
        int h = (int) g;
        int x = t.govind(10);
        System.out.println(x);
        t.chotu();

        int z = t.sub(10, 5);
        System.out.println(z);

        t.div(5, 6.5f);

        int k = (int) t.modulo(6, 8.4);
        System.out.println(k);
    }

    //DRY - Don't Repeat Yourself
    int govind(int a) {//function
        int b = a;//referenced Variable
//        int v = 30;//Local variable
        return b;
    }

    void chotu() {
        System.out.println("my name is chotu");
    }

    int mul(int a, double b) {
        return (int) (a * b);
    }

    double add(char z, float n) {
        int a = z;
        int b = (int) n;
        return a + b;
    }

    int sub(int n, long v) {
        int a = n;
        int b = (int) v;
        return a - b;
    }

    void div(long p, float j) {
        int n = (int) p;
        int m = (int) j;
        System.out.println("the division of " + n + " divided by " + m + " is " + p / j);
    }

    double modulo(int a, double b) {
        int s = a;
        int q = (int) b;
        return s % q;
    }


}//End of class Test
