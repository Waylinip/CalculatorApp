package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    private CalculatorLogic logic = new CalculatorLogic();
    private TextField display = new TextField("0");
    private Label historyDisplay = new Label("");


    @Override
    public void start(Stage primaryStage) {

        CalculatorView.setupDisplay(display, historyDisplay);

        GridPane grid = CalculatorView.createButtonGrid(text -> logic.processEvent(text, display, historyDisplay));

        VBox displayArea = new VBox(historyDisplay, display);
        displayArea.setPadding(new Insets(10, 0, 10, 0));
        VBox root = new VBox(displayArea, grid);
        root.setStyle("-fx-background-color: #e6e6e6;");

        Scene scene = new Scene(root, 320, 380);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}