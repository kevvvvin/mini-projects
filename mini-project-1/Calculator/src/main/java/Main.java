import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner sc = new Scanner(System.in);

        System.out.println("Calculator is now on!");
        System.out.println("Enter [q or Q] to turn off.\n");

        while (calculator.isRunning) {
            while (!calculator.isNumValid && calculator.isRunning) {
                System.out.print("Enter a number: ");
                String num = sc.next();
                if (calculator.InputIsNumber(num)) {
                    calculator.HandleArithmetic(num);
                }
            }

            while (!calculator.isOperatorValid && calculator.isRunning) {
                System.out.print("Enter an operator [+, -, *, /]: ");
                String operator = sc.next();
                if (calculator.InputIsOperator(operator)) {
                    calculator.SetOperation(operator);
                }
            }

            calculator.isNumValid = false;
            calculator.isOperatorValid = false;
        }
    }
}
