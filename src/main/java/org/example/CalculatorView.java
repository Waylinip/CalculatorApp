package org.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.function.Consumer;

public class CalculatorView {

    public static void setupDisplay(TextField display, Label historyDisplay) {
        historyDisplay.setAlignment(Pos.CENTER_RIGHT);
        historyDisplay.setMinHeight(30);
        historyDisplay.setMaxWidth(Double.MAX_VALUE);
        historyDisplay.setStyle("-fx-text-fill: #666666; -fx-padding: 0 10 0 0;");
        historyDisplay.setFont(Font.font("Segoe UI", 14));

        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setMinHeight(70);
        display.setStyle("-fx-background-color: transparent; -fx-border-color: none; -fx-text-fill: #000;");
        display.setFont(Font.font("Segoe UI Semibold", 36));
    }

    public static GridPane createButtonGrid(Consumer<String> onClick) {
        GridPane grid = new GridPane();
        grid.setHgap(2);
        grid.setVgap(2);
        grid.setPadding(new Insets(2));

        String[] labels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        int row = 0, col = 0;
        for (String label : labels) {
            Button btn = createButton(label, onClick);
            grid.add(btn, col, row);
            col++;
            if (col > 3) { col = 0; row++; }
        }
        return grid;
    }

    private static Button createButton(String text, Consumer<String> onClick) {
        Button btn = new Button(text);
        btn.setMinSize(78, 60);
        btn.setFont(Font.font("Segoe UI", 15));

        if (Character.isDigit(text.charAt(0))) {
            btn.setStyle("-fx-background-color: #ffffff; -fx-border-color: #dcdcdc;");
        } else if (text.equals("=")) {
            btn.setStyle("-fx-background-color: #de550d; -fx-text-fill: white;");
        } else {
            btn.setStyle("-fx-background-color: #f3f3f3; -fx-border-color: #dcdcdc;");
        }

        btn.setOnAction(e -> onClick.accept(text));
        return btn;
    }
}

