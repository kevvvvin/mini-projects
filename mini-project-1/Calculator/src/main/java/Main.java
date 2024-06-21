import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner sc = new Scanner(System.in);

        System.out.println("Calculator is now on!");
        System.out.println("Enter [q or Q] to turn off.");
        System.out.println("Enter [c or C] to clear the calculator.\n");

        while (calculator.isRunning) {
            if (!calculator.GetNumber(sc)) {continue;}
            if (!calculator.GetOperator(sc)) {continue;}
            calculator.isNumValid = false;
            calculator.isOperatorValid = false;
        }

        sc.close();
    }
}
