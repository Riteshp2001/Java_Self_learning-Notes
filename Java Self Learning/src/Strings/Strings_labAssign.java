package Strings;

import java.util.Scanner;

public class Strings_labAssign {
    //Question 1
    static Boolean Proving_immutable(String str) {
        String a = str;
        a.replaceFirst("m", "t");
        System.out.println(a);
        //as we can see replaceFirst function is not working on String Class because it is immutable u cannot change the predefined string data
        return a.equals(str);
    }

    static char[] sortArrayChar(char arr[]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    char temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    static Boolean Same_characterString(String a, String b) {
        char[] arr_1 = a.toCharArray();
        char[] arr_2 = b.toCharArray();
        sortArrayChar(arr_1);
        sortArrayChar(arr_2);

        if (arr_1.length != arr_2.length) {
            return false;
        }

        for (int i = 0; i < arr_1.length; i++) {
            if (arr_1[i] != arr_2[i]) {
                return false;
            }
        }
        return true;
    }

    static Boolean String_contains(String a, String b) {
        return b.contains(a);
    }

    static void Palindromic_String() {
        Scanner sc = new Scanner(System.in);
        String rev = "";
        System.out.println("Enter the Words you want to check: ");
        String Str_1 = sc.nextLine();
        int len_Str = Str_1.length();
        for (int j = len_Str - 1; j >= 0; j--) {
            char ch = Str_1.charAt(j);
            rev = rev + ch;
        }
        if (Str_1.equals(rev)) {
            System.out.println("Result is : true");
        } else {
            System.out.print("Result is : false");
        }
    }


    static void SplitByWord_Reverse(String str) {
//            String str = "hello world this is ritesh pandit";
        String[] arr = str.split(" ");
        str = "";
        for (int i = arr.length - 1; i >= 0; i--) {
            str += arr[i] + " ";
        }
        System.out.println(str);
    }

    static String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    static void permutations(String a, int l, int r) {
        if (l == r) System.out.println(a);
        else {
            for (int i = l; i <= r; i++) {
                a = swap(a, l, i);
                permutations(a, l + 1, r);
                a = swap(a, l, i);
            }
        }
    }

    public static void main(String[] args) {
        //Question 1
        Proving_immutable("Hello world");

        //Question 2
        System.out.println(Same_characterString("Ritesh", "hsetiR"));
        System.out.println(String_contains("ritesh", "i am ritesh pandit"));

        //Question 3
//        Palindromic_String();
        SplitByWord_Reverse("hello world this is ritesh pandit");

        permutations("Ritesh", 0, 5);
    }
}
