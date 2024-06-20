import java.util.Stack;

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

    public boolean InputIsNumber(String input) {
        if (input.equalsIgnoreCase("Q")) {
            TogglePower();
            return false;
        }

        try {
            double num = Double.parseDouble(input);
            if (num == 0 && this.operation.equals("/")) {
                System.out.println("Cannot divide by 0.");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Not a valid number.");
            return false;
        }
    }

    public boolean InputIsOperator(String input) {
        switch (input) {
            case "+", "-", "/", "*":
                return true;
            case "q", "Q":
                TogglePower();
                break;
            default:
                System.out.println("Not a valid operator.");
                return false;
        }
        return false;
    }

    public void HandleArithmetic(String input) {
        if (this.operation.isEmpty() || this.operation.equals("+")) {
            this.total = Add(this.total, Double.parseDouble(input));
            this.isNumValid = true;
        }
        else if (this.operation.equals("-")){
            this.total = Subtract(this.total, Double.parseDouble(input));
            this.isNumValid = true;
        }
        else if (this.operation.equals("*")){
            this.total = Multiply(this.total, Double.parseDouble(input));
            this.isNumValid = true;
        }
        else if (this.operation.equals("/")){
            double num = Double.parseDouble(input);
            if (num != 0) {
                this.total = Divide(this.total, num);
                this.isNumValid = true;
            }
        }

        if (!this.operation.isEmpty()) {
            System.out.println("Total: "+ this.total);
            this.operation = "";
        }
    }

    public void TogglePower() {
        this.isRunning = !this.isRunning;
        System.out.println("Calculator is now off.");
    }

    public void SetOperation(String input) {
        this.operation = input;
        this.isOperatorValid = true;
    }

    public double Add(double x, double y) {
        return x + y;
    }

    public double Subtract(double x, double y) {
        return x - y;
    }

    public double Multiply(double x, double y) {
        return x * y;
    }

    public double Divide(double x, double y) {
        return x / y;
    }


}
