package org.example.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.model.KeyCounterModel;

public class KeyCounterController {
    private final KeyCounterModel model;
    private final Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML private StackPane root;
    @FXML private Label keyCountLabel;

    public KeyCounterController(KeyCounterModel model, Stage stage) {
        this.model = model;
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        keyCountLabel.setText("Keys Typed: " + model.getKeyCount());

        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    public void handleKeyPress() {
        model.incrementKeyCount();
        Platform.runLater(() -> keyCountLabel.setText("Keys Typed: " + model.getKeyCount()));
    }

    public StackPane getRoot() {
        return root;
    }
}