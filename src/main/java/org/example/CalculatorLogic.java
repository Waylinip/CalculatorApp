package org.example;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorLogic {

    private double fnum = 0;
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
            fnum = 0;
            operator = "";
            start = true;
        } else if (value.equals("=")) {
            if (operator.isEmpty()) return;
            double snum = Double.parseDouble(display.getText());
            historyDisplay.setText(formatNumber(fnum) + " " + operator + " " + formatNumber(snum) + " =");
            calculate(snum, display, historyDisplay);
            operator = "";
            start = true;
        } else {
            fnum = Double.parseDouble(display.getText());
            operator = value;
            historyDisplay.setText(formatNumber(fnum) + " " + operator);
            start = true;
        }
    }

    private void calculate(double snum, TextField display, Label historyDisplay) {
        try {
            switch (operator) {
                case "+" -> fnum += snum;
                case "-" -> fnum -= snum;
                case "*" -> fnum *= snum;
                case "/" -> {
                    if (snum == 0) throw new ArithmeticException();
                    fnum /= snum;
                }
            }
            display.setText(formatNumber(fnum));
        } catch (ArithmeticException e) {
            display.setText("Cannot divide by zero");
            historyDisplay.setText("");
            fnum = 0;
            operator = "";
        }
    }

    private String formatNumber(double d) {
        if (d == (long) d) return String.format("%d", (long) d);
        return String.valueOf(d);
    }
}

