import java.util.ArrayDeque;
import java.util.Scanner;

public class RPNConverter {

    private ArrayDeque<String> splitThis(String stringToSplit) {
        ArrayDeque<String> splitString = new ArrayDeque<>();
        Scanner sc = new Scanner(stringToSplit);
        while (sc.hasNext()) {
            splitString.add(sc.next());
        }
        return splitString;
    }

    public boolean isNumeric(String checkThis) {
        return checkThis.contains("1") || checkThis.contains("2") || checkThis.contains("3") || checkThis.contains("4")
                || checkThis.contains("5") || checkThis.contains("6") || checkThis.contains("7") ||
                checkThis.contains("8") || checkThis.contains("9") || checkThis.contains("0");
    }

    // Returns true only if "a" has a higher priority than "b"
    private boolean absPriorityCheck(String a, String b) {
        if (a.equals("(")) {
            return false;
        } else if (a.equals(b)) {
            return false;
        } else if (a.equals("^")) {
            return true;
        } else return (a.equals("*") || a.equals("/") || a.equals("%")) && (b.equals("+") || b.equals("-"));
    }

    //Returns true if "a" has higher or the same priority as "b"
    private boolean priorityCheck(String a, String b) {

        if (a.equals(b) || a.equals("^")) {
            return true;
        } else if (a.equals("(")) {
            return false;
        } else if ((a.equals("*") || a.equals("/") || a.equals("%")) && !b.equals("^")) {
            return true;
        } else return (a.equals("+") || a.equals("-")) && (b.equals("+") || b.equals("-"));


    }

    public ArrayDeque<String> convertToRPN(String stringToConvert) {
        ArrayDeque<String> dequeToConvert = new ArrayDeque<>(splitThis(stringToConvert));
        ArrayDeque<String> convertedDeque = new ArrayDeque<>();
        ArrayDeque<String> operatorPile = new ArrayDeque<>();


        while (!dequeToConvert.isEmpty()) {
            String currentElement = dequeToConvert.pollFirst();

            assert currentElement != null;
            if (isNumeric(currentElement)) {
                convertedDeque.add(currentElement);
            } else if (currentElement.equals("(")) {
                operatorPile.add(currentElement);
            } else if (currentElement.equals(")")) {
                while (!operatorPile.peekLast().equals("(")) {
                    convertedDeque.add(operatorPile.pollLast());
                }
                operatorPile.removeLast();
            } else if (operatorPile.isEmpty() || absPriorityCheck(currentElement, operatorPile.peekLast())) {
                operatorPile.add(currentElement);
            } else {
                while ((!operatorPile.isEmpty() && !operatorPile.peekLast().equals("(") && priorityCheck(operatorPile.peekLast(), currentElement))) {
                    convertedDeque.add(operatorPile.pollLast());
                }

                operatorPile.add(currentElement);

            }

        }

        while (!operatorPile.isEmpty()) {
            convertedDeque.add(operatorPile.pollLast());
        }


        return convertedDeque;
    }


}
