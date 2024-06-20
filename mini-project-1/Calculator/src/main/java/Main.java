import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner sc = new Scanner(System.in);

        while (calculator.isRunning) {
            System.out.println(calculator.total);

            String input = sc.next();
            if (calculator.InputIsNumber(input)) {
                calculator.total += Double.parseDouble(input);
            }
            else {
                System.out.println("Not a number.");
                continue;
            }

            input = sc.next();
            if (calculator.InputIsOperator(input)) {
                calculator.SetOperation(input);
            }
            else {
                System.out.println("Not a valid operator.");
                continue;
            }
        }
    }
}
