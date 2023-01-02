package SimpleChatBot;

import java.util.Scanner;

public class Simple_Chatty_Bot {
    private static final Scanner sc = new Scanner(System.in);

    private void intro() {
        System.out.println("Hello! My name is Ritesh.");
        System.out.println("I was created in 2021.");
    }

    private void name_Guess() {
        System.out.println("Please, remind me your name.");
        // reading a name
        String name = sc.nextLine();
        System.out.println("What a great name you have, " + name + "!");
    }

    private void guess_Age() {
        while (true) {
            try (Scanner sc = new Scanner(System.in)) {
                System.out.println("Let me guess your age.");
                System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");
                int[] ans = new int[3];
                for (int i = 0; i < ans.length; i++) {
                    ans[i] = sc.nextInt();
                }
                int age = (ans[0] * 70 + ans[1] * 21 + ans[2] * 15) % 105;
                if (age <= 12) {
                    System.out.println("You are yet to unlock your full potential till then Enjoy your childhood");
                } else System.out.println("Your age is " + age + "; that's a good time to start programming!");
                break;
            } catch (Exception e) {
                System.out.println("Please enter valid input");
                sc.nextLine();
                continue;
            }
        }
    }

    private void guess_Question() {
        System.out.println("Let's test your programming knowledge");
        System.out.println("Why do we use methods?");
        String[] arr = new String[]{
                "1. To repeat a statement multiple times.",
                "2. To decompose a program into several small subroutines.",
                "3. To determine the execution time of a program.",
                "4. To interrupt the execution of a program."
        };
        for (String i : arr) {
            System.out.println(i);
        }
        sc.nextLine();
        do {
            try {
                String a = sc.nextLine();
                if (a.equals("1") || a.equals("1.")) {
                    System.out.println("Congratulations you are correct !");
                    System.out.println("\nThank You for using ChatBot !");
                    break;
                } else {
                    System.out.println("Please try again");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid input");
            }
        } while (true);

    }

    public static void main(String[] args) {
        Simple_Chatty_Bot s = new Simple_Chatty_Bot();
        s.intro();
        s.name_Guess();
        s.guess_Age();
        s.guess_Question();
        sc.close();
    }
}
