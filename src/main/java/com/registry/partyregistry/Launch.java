package com.registry.partyregistry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class Launch extends Application {

    static Stage mainStage;
    static Locale locale;

    public static Stage getMainStage() {
        return mainStage;
    }

    public static Locale getLocale() {
        return locale;
    }

    public static void setLocale(Locale locale) {
        Launch.locale = locale;
    }

    public static ResourceBundle getBundle() {
        return ResourceBundle.getBundle("com.registry.partyregistry.Bundle", locale);
    }

    @Override
    public void start(Stage stage) throws IOException {
        locale = new Locale("en");
        ResourceBundle bundle = ResourceBundle.getBundle("com.registry.partyregistry.Bundle", locale);
        Parent root = FXMLLoader.load(Objects.requireNonNull(Launch.class.getResource("registry.fxml")), bundle);

        mainStage = stage;
        Scene scene = new Scene(root);
        mainStage.setTitle(bundle.getString("app.title"));
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
