package src;

import java.io.Console;

public class Main {
    public static Integer $last = 0;

    public static Integer add(int a, int b) {
        return a + b;
    }

    public static Integer subtract(int a, int b) {
        return a - b;
    }

    public static Integer divide(int a, int b) {
        return a / b;
    }

    public static Integer multiply(int a, int b) {
        return a * b;
    }

    public static void evaluate(int a, String operator, int b) {
        switch (operator) {
            case "+":
                $last = add(a, b);
                System.out.println($last);
                break;

            case "-":
                $last = subtract(a,b);
                System.out.println($last);
                break;

            case "/":
                if (b == 0) {
                    System.out.println("Cannot divide by zero");
                } else {
                    $last = divide(a,b);
                    System.out.println($last);
                }
                break;

            case "*":
                $last = multiply(a,b);
                System.out.println($last);
                break;

            default:
                break;
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome");

        Console cons = System.console();
        String input = "";
        String[] inputArray;

        while (!input.equals("exit")) {
            input = cons.readLine("> ");
            if(input.equals("exit")){
                System.out.println("Bye bye");
                break;
            }
            inputArray = input.trim().split(" ");
            if(inputArray[0].trim().equals("$last") && inputArray[2].trim().equals("$last")) {
                evaluate($last, inputArray[1], $last);
            } else if (inputArray[2].trim().equals("$last")) {
                evaluate(Integer.parseInt(inputArray[0].trim()), inputArray[1], $last);
            } else if (inputArray[0].trim().equals("$last")) {
                evaluate($last, inputArray[1], Integer.parseInt(inputArray[2].trim()));
            } else {
                evaluate(Integer.parseInt(inputArray[0].trim()), inputArray[1], Integer.parseInt(inputArray[2].trim()));
            }
        }
    }
}