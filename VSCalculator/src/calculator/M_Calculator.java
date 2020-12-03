package calculator;

import static java.lang.Double.parseDouble;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.String.valueOf;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Sherab Pereira Arias
 * @version 1.0
 */
public class M_Calculator {

    private final LinkedList<String> operation;
    private boolean operationSuccessful;

    public M_Calculator() {
        this.operation = new LinkedList<>();
        this.operationSuccessful = true;
    }

    public String addOperand(String operand) {
        if (operand != null) {
            operation.offerLast(operand);
        }
        return operand;
    }

    public String pollLastEntry() {
        if (!operation.isEmpty()) {

            return (String) operation.pollLast();
        }
        return null;
    }

    public void clearFields() {
        operation.clear();

    }

    public String getLastEntry() {
        return operation.getLast();
    }

    public boolean isEmpty() {
        return this.operation.isEmpty();
    }

    public boolean isSuccessful() {
        return operationSuccessful;
    }

    public void setIsSuccessful() {
        this.operationSuccessful = true;
    }

    public int getSize() {
        return operation.size();
    }

    public String performCalculation() {

        try {
            this.firstLogicLevel();
            this.secondLogicLevel();
            this.thirdLogicLevel();
        } catch (NumberFormatException | NoSuchElementException ex) {
            operationSuccessful = false;
        }
        //caused by wrongly operating, Example: ²5  instead of 5²
        //the way in which the levels are resolved need this
        //to be handled
        //We return the first position since after the calculations
        //the array size is expected to be 1, (the final result)
        return operation.getFirst();

    }

    /**
     * This logical level resolves the square roots and powers and modifies the LinkedList
     * accordingly
     */
    private void firstLogicLevel() {
        double result;

        ListIterator<String> iter = operation.listIterator();

        while (iter.hasNext()) {
            String operand = (String) iter.next();

            if (operand.equals("√")) {
                iter.previous();

                if (iter.hasPrevious()) {
                    String previousChar = iter.previous();

                    if (previousChar.matches("[-+]?([0-9]*\\.[0-9]+|[0-9]+)")) {
                        iter.next();
                        iter.add("x");
                        iter.next();
                    } else {
                        if (previousChar.isBlank()) {
                            iter.remove();
                        }
                        iter.next();
                        iter.next();
                    }
                }

                iter.remove();
                double x = parseDouble(iter.next());
                iter.remove();

                result = sqrt(x);
                iter.add(valueOf(result));

            } else if (operand.equals("²")) {

                iter.remove();
                double x = parseDouble(iter.previous());
                iter.remove();

                result = pow(x, 2);
                iter.add(valueOf(result));
            }
        }
    }

    /**
     * This second logic level resolves the divisions and multiplications and modifies the
     * LinkedList accordingly
     */
    private void secondLogicLevel() {
        double result;

        ListIterator<String> iter = operation.listIterator();

        while (iter.hasNext()) {
            String operand = (String) iter.next();

            if (operand.equals("x")) {

                iter.previous();
                iter.next();
                iter.remove();
                double x = parseDouble(iter.previous());
                iter.remove();
                double y = parseDouble(iter.next());
                iter.remove();

                result = x * y;
                iter.add(valueOf(result));

            } else if (operand.equals("÷")) {

                iter.previous();
                iter.next();
                iter.remove();
                double x = parseDouble(iter.previous());
                iter.remove();
                double y = parseDouble(iter.next());
                iter.remove();

                result = x / y;
                iter.add(valueOf(result));
            }
        }
    }

    /**
     * Lastly this third logic level resolves the additions and subtractions and modifies
     * the LinkedList accordingly
     */
    private void thirdLogicLevel() {
        double result;

        ListIterator<String> iter = operation.listIterator();

        while (iter.hasNext()) {
            String operand = (String) iter.next();

            double x = 0;
            double y = 0;
            if (operand.equals("+")) {

                iter.previous();
                iter.remove();
                if (iter.hasPrevious()) {
                    x = parseDouble(iter.previous());
                    iter.remove();
                }

                if (iter.hasNext()) {
                    y = parseDouble(iter.next());
                    iter.remove();
                }

                result = x + y;
                iter.add(valueOf(result));

            } else if (operand.equals("-")) {

                iter.previous();
                iter.remove();

                if (iter.hasPrevious()) {
                    x = parseDouble(iter.previous());
                    iter.remove();
                }

                if (iter.hasNext()) {
                    y = parseDouble(iter.next());
                    iter.remove();
                }

                result = x - y;
                iter.add(valueOf(result));
            }
        }
    }
}
