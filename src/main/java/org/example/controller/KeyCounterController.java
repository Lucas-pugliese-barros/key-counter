package org.example.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.model.KeyCounterModel;

public class KeyCounterController {
    private final KeyCounterModel model;
    private final Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML private BorderPane root; // Already updated to BorderPane
    @FXML private Label keyCountLabel;
    @FXML private MFXButton resetButton;
    @FXML private MFXButton pauseButton;

    public KeyCounterController(KeyCounterModel model, Stage stage) {
        this.model = model;
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        // Bind label text to model property
        keyCountLabel.textProperty().bind(model.keyCountTextProperty());

        // Set up mouse dragging for window movement
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        // Handle reset button action
        resetButton.setOnAction(event -> model.resetKeyCount());

        // Handle pause button action
        pauseButton.setOnAction(event -> {
            model.togglePause();
            pauseButton.setText(model.isPaused() ? "Resume" : "Pause");
        });
    }

    public void handleKeyPress() {
        model.incrementKeyCount();
    }

    public BorderPane getRoot() { // Already updated to return BorderPane
        return root;
    }
}