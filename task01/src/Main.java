package src;

import java.io.Console;

public class Main {
    public static Float $last = 0f;

    public static Float add(float a, float b) {
        return a + b;
    }

    public static Float subtract(float a, float b) {
        return a - b;
    }

    public static Float divide(float a, float b) {
        return a / b;
    }

    public static Float multiply(float a, float b) {
        return a * b;
    }

    public static void evaluate(float a, String operator, float b) {
        switch (operator) {
            case "+":
                $last = add(a, b);
                System.out.printf("%.0f\n", $last);
                break;

            case "-":
                $last = subtract(a,b);
                System.out.printf("%.0f\n", $last);
                break;

            case "/":
                if (b == 0) {
                    System.out.println("Cannot divide by zero");
                } else {
                    $last = divide(a,b);
                    System.out.printf("%.0f\n", $last);
                }
                break;

            case "*":
                $last = multiply(a,b);
                System.out.printf("%.0f\n", $last);
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
                evaluate(Float.parseFloat(inputArray[0].trim()), inputArray[1], $last);
            } else if (inputArray[0].trim().equals("$last")) {
                evaluate($last, inputArray[1], Float.parseFloat(inputArray[2].trim()));
            } else {
                evaluate(Float.parseFloat(inputArray[0].trim()), inputArray[1], Float.parseFloat(inputArray[2].trim()));
            }
        }
    }
}