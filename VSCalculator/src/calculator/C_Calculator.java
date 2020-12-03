package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Sherab Pereira Arias
 * @version 1.0
 */
public final class C_Calculator implements ActionListener {

    private final V_Calculator view;
    private final M_Calculator model;
    private final String warningMessage = "Unsupported operation";
    private final String divideByCero = "Infinity";

    public C_Calculator(V_Calculator view, M_Calculator model) {

        this.view = view;
        this.model = model;

    }

    public void run() {

        view.getButtonComma().addActionListener(this);
        view.getButton0().addActionListener(this);
        view.getButton1().addActionListener(this);
        view.getButton2().addActionListener(this);
        view.getButton3().addActionListener(this);
        view.getButton4().addActionListener(this);
        view.getButton5().addActionListener(this);
        view.getButton6().addActionListener(this);
        view.getButton7().addActionListener(this);
        view.getButton8().addActionListener(this);
        view.getButton9().addActionListener(this);
        view.getButtonSubstraction().addActionListener(this);
        view.getButtonAddition().addActionListener(this);
        view.getButtonMultiplication().addActionListener(this);
        view.getButtonDivision().addActionListener(this);
        view.getButtonPow().addActionListener(this);
        view.getButtonRoot().addActionListener(this);
        view.getButtonClear().addActionListener(this);
        view.getButtonDelete().addActionListener(this);
        view.getButtonEquals().addActionListener(this);

        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String action = e.getActionCommand();

        if (view.getTextDisplay().getText().equalsIgnoreCase(warningMessage)
                || view.getTextDisplay().getText().equalsIgnoreCase(divideByCero)) {

            view.setTextDisplay("");
            model.clearFields();
        }

        switch (action) {
            case ".":

                int previous = view.getTextDisplay().getText().length();
                char operator;

                //To avoid enterin multiple decimal floating points (only 1 per number allowed)
                if (!view.getCurrentNumber().contains(".")) {
                    //formatting the point, if entered alone at the beginning of the Display Screen, a value of 0 is assumed
                    if (previous == 0) {
                        view.getTextDisplay().setText(view.getTextDisplay().getText() + "0.");
                        view.concatCurrentNumber("0.");
                    } else {
                        //Else we retrieve the right-hand most digit
                        operator = view.getTextDisplay().getText().charAt(previous - 1);
                        //We control each one under different rules
                        switch (operator) {
                            case '+':
                            case '-':
                            case '÷':
                            case 'x':
                            case '√':
                            case '²':
                                view.getTextDisplay().setText(view.getTextDisplay().getText() + "0.");
                                view.concatCurrentNumber("0.");
                                break;
                            default:
                                view.getTextDisplay().setText(view.getTextDisplay().getText() + ".");
                                view.concatCurrentNumber(".");
                                break;
                        }
                    }
                }
                break;
            //All numbers are concatenated with the current number
            //All numbers are shown concatenated onto the full String showing in the screen
            case "0":
                view.setTextDisplay(view.getTextDisplay().getText() + "0");
                view.concatCurrentNumber("0");
                break;
            case "1":
                view.setTextDisplay(view.getTextDisplay().getText() + "1");
                view.concatCurrentNumber("1");
                break;
            case "2":
                view.setTextDisplay(view.getTextDisplay().getText() + "2");
                view.concatCurrentNumber("2");
                break;
            case "3":
                view.setTextDisplay(view.getTextDisplay().getText() + "3");
                view.concatCurrentNumber("3");
                break;
            case "4":
                view.setTextDisplay(view.getTextDisplay().getText() + "4");
                view.concatCurrentNumber("4");
                break;
            case "5":
                view.setTextDisplay(view.getTextDisplay().getText() + "5");
                view.concatCurrentNumber("5");
                break;
            case "6":
                view.setTextDisplay(view.getTextDisplay().getText() + "6");
                view.concatCurrentNumber("6");
                break;
            case "7":
                view.setTextDisplay(view.getTextDisplay().getText() + "7");
                view.concatCurrentNumber("7");
                break;
            case "8":
                view.setTextDisplay(view.getTextDisplay().getText() + "8");
                view.concatCurrentNumber("8");
                break;
            case "9":
                view.setTextDisplay(view.getTextDisplay().getText() + "9");
                view.concatCurrentNumber("9");
                break;
            //Displaying the operators as the last char on the String showing on the screen(concatenated)
            //Then we proced to send them to the model Array, first it checks the previous character:
            //If this character is an empty string, it just stores the operator onto the model
            //Otherwise we first send the number, store it, then the operator, and store it.
            case "-":

                view.setTextDisplay(view.getTextDisplay().getText() + "-");
                if (!view.getCurrentNumber().equals("")) {
                    model.addOperand(view.getCurrentNumber());
                }
                model.addOperand("-");
                view.clearCurrentNumber();
                break;
            case "+":

                view.setTextDisplay(view.getTextDisplay().getText() + "+");
                if (!view.getCurrentNumber().equals("")) {
                    model.addOperand(view.getCurrentNumber());
                }
                model.addOperand("+");
                view.clearCurrentNumber();
                break;
            case "x":

                view.setTextDisplay(view.getTextDisplay().getText() + "x");
                if (!view.getCurrentNumber().equals("")) {
                    model.addOperand(view.getCurrentNumber());
                }
                model.addOperand("x");
                view.clearCurrentNumber();
                break;
            case "÷":

                view.setTextDisplay(view.getTextDisplay().getText() + "÷");
                if (!view.getCurrentNumber().equals("")) {
                    model.addOperand(view.getCurrentNumber());
                }
                model.addOperand("÷");
                view.clearCurrentNumber();
                break;
            case "x²":

                view.setTextDisplay(view.getTextDisplay().getText() + "²");
                if (!view.getCurrentNumber().equals("")) {
                    model.addOperand(view.getCurrentNumber());
                }
                model.addOperand("²");
                view.clearCurrentNumber();
                break;
            case "√":

                view.setTextDisplay(view.getTextDisplay().getText() + "√");
                if (!view.getCurrentNumber().equals("")) {
                    model.addOperand(view.getCurrentNumber());
                }
                model.addOperand("√");
                view.clearCurrentNumber();
                break;
            case "C":

                model.clearFields();
                view.clearCurrentNumber();
                view.setTextDisplay("");
                break;
            //Here we control how we delete the values stored in the model's LinkedList
            case "⬅":
                //We check the size of the whole String being displayed on the screen
                int currentDisplaySize = view.getTextDisplay().getText().length() - 1;
                //Proceed only if the string is bigger or equals to 0
                if (currentDisplaySize >= 0) {
                    //We figure out what character is being deleted by getting the right-most digit on the Display
                    view.setCharDeleting(view.getTextDisplay().getText().substring(currentDisplaySize));
                    //Now we set the display size equals to the display size minus the last character
                    view.setTextDisplay(view.getTextDisplay().getText().substring(0, currentDisplaySize));
                    //Here we are looking it the char being deleted is a OPERATOR (+-x etc.....)
                    if (!view.getCharDeleting().matches("[0-9.]")) {
                        //If the model's Linked list is not empty, we remove the last entry
                        if (!model.isEmpty()) {
                            model.pollLastEntry();
                            //if the char being deleted does not equal sq.root operation and the model is not empty
                            //We check the previous character to the character just deleted
                            if (!view.getCharDeleting().equals("√") && model.getSize() > 0) {
                                String recoveredOperator = model.getLastEntry();
                                if (recoveredOperator.matches("[-+]?([0-9]*\\.[0-9]+|[0-9]+)")) {
                                    //If this character is not a numeric value, IE: operators(x+- etc...)
                                    //that means we have just deleted an operator, so we need to set the current number
                                    //to the previous number being operated
                                    //for that we get the last entry before the operator, and only, if it is a number
                                    //and we set the current number to this
                                    view.setCurrentNumber(model.pollLastEntry());
                                }
                            }
                        }
                    } else {
                        //If it's not either a number or a floating point (comma)
                        //and if the current number stored is not null and bigger to 0
                        //Then we set the size of the current number string to be equal to its size minus the last character
                        if (view.getCurrentNumber().length() > 0 && view.getCurrentNumber() != null) {
                            view.setCurrentNumber(view.getCurrentNumber().substring(0, view.getCurrentNumber().length() - 1));
                        }
                    }
                }
                break;
            case "=":
                //We send to the model's LinkedList the last operator on the screen,
                //Then we proceed to do the operation
                //And displaying the results or errors on the screen
                model.addOperand(view.getCurrentNumber());
                String resultOperation = model.performCalculation();

                if (model.isSuccessful()) {
                    view.setTextDisplay(resultOperation);

                    if (!resultOperation.equalsIgnoreCase(divideByCero)) {
                        view.setCurrentNumber(resultOperation);
                    }
                    model.clearFields();

                } else {
                    view.setTextDisplay(warningMessage);
                    view.clearCurrentNumber();
                    model.clearFields();
                    view.clearCharDeleting();
                    model.setIsSuccessful();
                }
                break;
            default:
                System.out.println("Warning: issues while performing " + action + " were found");
                break;
        }
    }
}
