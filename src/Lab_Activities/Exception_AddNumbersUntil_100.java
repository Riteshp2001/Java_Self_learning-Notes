package Lab_Activities;

import java.util.Scanner;

//Program to check if two number Sum exceeds 100 and Print new Exception using throw
public class Exception_AddNumbersUntil_100 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);//creating Scanner object
        System.out.print("Enter Starting number: ");
        int num1 = sc.nextInt();
        System.out.print("Enter Ending number: ");//taking input
        int num2 = sc.nextInt();
        int sum = num1 + num2;
        if (sum >= 100) {
            throw new ArithmeticException("Limit EXCEEDED!!!");//compile time output as we are providing input rather than machine itself checking it
        } else {
            System.out.println(sum);//displaying end result
        }
    }
}

//Unchecked Exception
//if child class throws any exception then parent class must throw same exception or its parent exception
//if parent class throws any exception then child class can throw same exception or its child exception
//if parent class throws any exception then child class can throw no exception

//Checked Exception
//No such rules we can use any exception in class any time