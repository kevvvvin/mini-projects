import java.util.Stack;

public class Calculator {
    public double total;
    public boolean isRunning;
    private Stack<String> inputHistory;
    private String operation;

    public Calculator() {
        this.total = 0;
        this.isRunning = true;
        this.inputHistory = new Stack<>();
    }

    public void TogglePower() {
        this.isRunning = !this.isRunning;
        System.out.println("Calculator is now off.");
    }

    public boolean InputIsNumber(String input) {
        if (input.equalsIgnoreCase("Q")) {
            TogglePower();
            return false;
        }

        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
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
                return false;
        }
        return false;
    }

    public void SetOperation(String input) {
        this.operation = input;
    }

}
