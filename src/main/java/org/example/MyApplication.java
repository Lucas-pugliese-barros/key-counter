package org.example;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;


public class MyApplication extends Application {

    int keyCount = 0;
    private double xOffset = 0;
    private double yOffset = 0;
    private Label keyCountLabel;
    GlobalKeyListener globalKeyListener;

    public MyApplication() {
        globalKeyListener = new GlobalKeyListener(this::onClickAction);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        keyCountLabel = new Label("Keys Typed: 0");
        keyCountLabel.setFont(Font.font("Verdana", 28));
        keyCountLabel.setTextFill(Color.WHITE);

        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(keyCountLabel);

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/background.png").toExternalForm()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        root.setBackground(new Background(backgroundImage));

        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        double size = 300;

        Scene scene = new Scene(root, size + 50, size);
        scene.setFill(Color.TRANSPARENT);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(true);

        stage.show();

        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(globalKeyListener);
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }

    private void onClickAction() {
        keyCount++;
        Platform.runLater(() -> keyCountLabel.setText("Keys Typed: " + keyCount));
    }
}
