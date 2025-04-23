package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.controller.KeyCounterController;
import org.example.model.KeyCounterModel;
import org.example.view.KeyCounterView;

import java.io.IOException;

public class MyApplication extends Application {
    private KeyCounterView view;

    @Override
    public void start(Stage stage) throws IOException {
        KeyCounterModel model = new KeyCounterModel();
        KeyCounterController controller = new KeyCounterController(model, stage);

        FXMLLoader loader = new FXMLLoader(MyApplication.class.getResource("/main.fxml"));
        if (loader.getLocation() == null) {
            throw new IllegalStateException("FXML file not found at /main.fxml");
        }
        loader.setController(controller);
        loader.load();

        view = new KeyCounterView(controller);

        Scene scene = new Scene(controller.getRoot(), 500, 400);
        stage.setScene(scene);
        stage.setTitle("Key Counter");
        scene.setFill(Color.TRANSPARENT);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(true);

        stage.show();
    }

    @Override
    public void stop() {
        if (view != null) {
            view.shutdown();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}