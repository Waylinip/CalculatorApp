package org.example;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class CalculatorLogic {

    private double firstNum = 0;
    private String operator = "";
    private boolean start = true;

    public void processEvent(String value, TextField display, Label historyDisplay) {
        if (Character.isDigit(value.charAt(0))) {
            if (start) {
                display.setText(value);
                start = false;
            } else {
                display.setText(display.getText() + value);
            }
        } else if (value.equals("C")) {
            display.setText("0");
            historyDisplay.setText("");
            firstNum = 0;
            operator = "";
            start = true;
        } else if (value.equals("=")) {
            if (operator.isEmpty()) return;
            double snum = Double.parseDouble(display.getText());
            historyDisplay.setText(formatNumber(firstNum) + " " + operator + " " + formatNumber(snum) + " =");
            calculate(snum, display, historyDisplay);
            operator = "";
            start = true;
        } else {
            firstNum = Double.parseDouble(display.getText());
            operator = value;
            historyDisplay.setText(formatNumber(firstNum) + " " + operator);
            start = true;
        }
    }

    private void calculate(double secondNum, TextField display, Label historyDisplay) {
        try {
            switch (operator) {
                case "+" -> firstNum += secondNum;
                case "-" -> firstNum -= secondNum;
                case "*" -> firstNum *= secondNum;
                case "/" -> {
                    if (secondNum == 0) throw new ArithmeticException();
                    firstNum /= secondNum;
                }
            }
            display.setText(formatNumber(firstNum));

        } catch (ArithmeticException e) {
            display.setText("Cannot divide by 0");
            historyDisplay.setText("");
            firstNum = 0;
            operator = "";
        }
    }

    private String formatNumber(double d) {
        if (d == (long) d) return String.format("%d", (long) d);
        return String.valueOf(d);
    }
}

