import java.util.Scanner;

public class Calculator {
    private double total;
    private String operation;
    public boolean isRunning;
    public boolean isNumValid;
    public boolean isOperatorValid;

    public Calculator() {
        this.total = 0;
        this.operation = "";
        this.isRunning = true;
        this.isNumValid = false;
        this.isOperatorValid = false;
    }

    public boolean GetNumber(Scanner sc) {
        while (!this.isNumValid && this.isRunning) {
            System.out.print("Enter a number: ");
            String num = sc.next();
            if (num.equalsIgnoreCase("C")) {
                this.Clear();
                return false;
            }
            else if (num.equalsIgnoreCase("Q")) {
                this.TogglePower();
                return false;
            }
            else if (this.InputIsNumber(num)) {
                this.HandleArithmetic(num);
                return true;
            }
        }
        return false;
    }

    public boolean GetOperator(Scanner sc) {
        while (!this.isOperatorValid && this.isRunning) {
            System.out.print("Enter an operator [+, -, *, /]: ");
            String operator = sc.next();
            if (operator.equalsIgnoreCase("C")) {
                this.Clear();
                return false;
            }
            else if (operator.equalsIgnoreCase("Q")) {
                this.TogglePower();
                return false;
            }
            else if (this.InputIsOperator(operator)) {
                this.SetOperation(operator);
                return true;
            }
        }
        return false;
    }

    // Checks if the user input is a valid number or if user wants to quit
    private boolean InputIsNumber(String input) {
        try {
            double num = Double.parseDouble(input);
            if (num == 0 && this.operation.equals("/")) {
                System.out.println("Cannot divide by 0.");
                return false;
            }
            this.isNumValid = true;
            return true;
        } catch (Exception e) {
            System.out.println("Not a valid number.");
            return false;
        }
    }

    // Checks if the user input is a valid operator or if user wants to quit
    private boolean InputIsOperator(String input) {
        switch (input) {
            case "+", "-", "/", "*":
                this.isOperatorValid = true;
                return true;
            default:
                System.out.println("Not a valid operator.");
                break;
        }
        return false;
    }

    // Handles arithmetic operations based on operator provided by the user
    private void HandleArithmetic(String input) {
        double prevTotal = this.total;

        if (this.operation.isEmpty() || this.operation.equals("+")) {
            this.total = Add(this.total, Double.parseDouble(input));
        }
        else if (this.operation.equals("-")){
            this.total = Subtract(this.total, Double.parseDouble(input));
        }
        else if (this.operation.equals("*")){
            this.total = Multiply(this.total, Double.parseDouble(input));
        }
        else if (this.operation.equals("/")){
            this.total = Divide(this.total, Double.parseDouble(input));
        }

        // Prevent printing total value if input is the first number
        if (!this.operation.isEmpty()) {
            System.out.println(prevTotal + " " + this.operation + " " + input + " = " + this.total);
        }
    }

    private void SetOperation(String input) {
        this.operation = input;
    }

    private void TogglePower() {
        this.isRunning = !this.isRunning;
        System.out.println("Calculator is now off.");
    }

    private void Clear() {
        this.operation = "";
        this.total = 0;
        this.isNumValid = false;
        this.isOperatorValid = false;
        System.out.println("Calculator cleared.");
    }

    private double Add(double x, double y) {
        return x + y;
    }

    private double Subtract(double x, double y) {
        return x - y;
    }

    private double Multiply(double x, double y) {
        return x * y;
    }

    private double Divide(double x, double y) {
        return x / y;
    }
}
