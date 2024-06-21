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

    // Checks if the user input is a valid number or if user wants to quit
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
            this.isNumValid = true;
            return true;
        } catch (Exception e) {
            System.out.println("Not a valid number.");
            return false;
        }
    }

    // Checks if the user input is a valid operator or if user wants to quit
    public boolean InputIsOperator(String input) {
        switch (input) {
            case "+", "-", "/", "*":
                this.isOperatorValid = true;
                return true;
            case "q", "Q":
                TogglePower();
                break;
            default:
                System.out.println("Not a valid operator.");
                break;
        }
        return false;
    }

    // Handles arithmetic operations based on operator provided by the user
    public void HandleArithmetic(String input) {
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
            System.out.println("Total: "+ this.total);
        }
    }

    public void TogglePower() {
        this.isRunning = !this.isRunning;
        System.out.println("Calculator is now off.");
    }

    public void SetOperation(String input) {
        this.operation = input;
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
