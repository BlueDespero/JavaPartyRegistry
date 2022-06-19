package com.registry.partyregistry.controllers;

import com.registry.partyregistry.Launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegistryController {
    public TextField party_name_input;
    public DatePicker event_date_input;
    public ChoiceBox category_input;
    public TextArea notes_input;
    public TextField budget_input;
    public TextField starting_time_input;
    public Button add_button;
    public Label error_label;

    public void add_event(ActionEvent actionEvent) {
    }

    private void change_language(Locale locale)
    {
        ResourceBundle bundle = ResourceBundle.getBundle("com.registry.partyregistry.Bundle", locale);
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(Launch.class.getResource("registry.fxml")), bundle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);
        Launch.getMainStage().hide();
        Launch.getMainStage().setTitle(bundle.getString("app.title"));
        Launch.getMainStage().setScene(scene);
        Launch.getMainStage().show();
    }

    public void language_to_english(ActionEvent actionEvent) {
        this.change_language(new Locale("en"));
    }

    public void language_to_spanish(ActionEvent actionEvent) {
        this.change_language(new Locale("es"));
    }

    public void language_to_polish(ActionEvent actionEvent) {
        this.change_language(new Locale("pl"));
    }
}
