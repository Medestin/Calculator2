import java.util.ArrayDeque;

public class Calculator extends RPNConverter {

    public double addition(double a, double b) {
        return a + b;
    }

    public double subtraction(double a, double b) {
        return a - b;
    }

    public double multiplication(double a, double b) {
        return a * b;
    }

    public double division(double a, double b) {
        return a / b;
    }

    public double modulo(double a, double b) {
        return a % b;
    }

    public double power(double a, double b) {
        return Math.pow(a, b);
    }

    private Double calculate(Double a, Double b, String operator) {
        Double result = null;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            case "%":
                result = a % b;
                break;
            case "^":
                result = Math.pow(a, b);
                break;
        }
        return result;
    }


    public double calculateThis(String toBeCalculated) {
        ArrayDeque<String> toBeCalculatedDeque = new ArrayDeque<>(convertToRPN(toBeCalculated));
        ArrayDeque<Double> numberPile = new ArrayDeque<>();
        while (!toBeCalculatedDeque.isEmpty()) {
            String currentElement = toBeCalculatedDeque.pollFirst();
            assert currentElement != null;
            if (isNumeric(currentElement)) {
                numberPile.add(Double.valueOf(currentElement));
            } else {
                Double a = numberPile.pollLast();
                Double b = numberPile.pollLast();
                numberPile.add(calculate(b, a, currentElement));
            }
        }

        return numberPile.peekLast();


    }

}
