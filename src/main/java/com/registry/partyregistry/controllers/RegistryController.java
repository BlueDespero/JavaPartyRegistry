package com.registry.partyregistry.controllers;

import com.registry.partyregistry.event.Event;
import com.registry.partyregistry.Launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import org.joda.money.Money;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
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
    public TreeTableView<Event> tree_table;
    public TreeTableColumn<Event, String> date_column;
    public TreeTableColumn<Event, String> party_name_column;
    public TreeTableColumn<Event, String> starting_time_column;
    public TreeTableColumn<Event, String> category_column;
    public TreeTableColumn<Event, String> notes_column;
    public TreeTableColumn<Event, String> budget_column;

    @FXML
    public void initialize() {
        date_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("date"));
        party_name_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        starting_time_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("starting_time"));
        category_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("category"));
        notes_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("notes"));
        budget_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("budget"));
    }

    public void add_event(ActionEvent actionEvent) {
        String event_name = party_name_input.getText();
        String category = (String) category_input.getValue();
        String note = notes_input.getText();
        String money_string = Launch.getBundle().getString("currency_id") + " "+ budget_input.toString();
        Money budget = Money.parse(money_string);
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Launch.getLocale());
        try {
            Date date = df.parse(event_date_input.getChronology().toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


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
        Launch.setLocale(locale);
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
