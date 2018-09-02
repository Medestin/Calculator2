import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String testString = "( 6 / 2 ) ^ 4";
        Calculator calculator = new Calculator();

        System.out.println(calculator.convertToRPN(testString));
        System.out.println(calculator.calculateThis(testString));


        System.out.println("Enter an expression to be calculated, remember spacing between number, operators and parentheses");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        System.out.println("RPN converted:");
        System.out.println(calculator.convertToRPN(userInput));


        System.out.println("Result: " + calculator.calculateThis(userInput));


    }
}
