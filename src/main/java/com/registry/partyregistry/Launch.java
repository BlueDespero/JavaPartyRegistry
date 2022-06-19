package com.registry.partyregistry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launch extends Application {

    static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.registry.partyregistry.Launch.class.getResource("registry.fxml"));
        mainStage = stage;
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setTitle("Stragan!");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
