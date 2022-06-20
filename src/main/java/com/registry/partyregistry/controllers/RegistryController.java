package com.registry.partyregistry.controllers;

import com.registry.partyregistry.Launch;
import com.registry.partyregistry.event.Event;
import com.registry.partyregistry.event.EventRegistry;
import com.registry.partyregistry.event.QuarterEventSummary;
import com.registry.partyregistry.event.YearEventSummary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.StringConverter;
import org.joda.money.Money;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RegistryController {
    public TextField party_name_input;
    public DatePicker event_date_input;
    public ChoiceBox<String> category_input;
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

        for (String s :
                Launch.getBundle().getString("app.categories").split(" ")) {
            category_input.getItems().add(s);
        }

        this.internationalize_datapicker();
    }

    private void internationalize_datapicker() {
        String pattern = Launch.getBundle().getString("app.dateformat");
        event_date_input.setPromptText(pattern.toLowerCase());

        event_date_input.setConverter(new StringConverter<LocalDate>() {
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    public void add_event(ActionEvent actionEvent) {
        String event_name = party_name_input.getText();
        String category = (String) category_input.getValue();
        String note = notes_input.getText();
        String money_string = Launch.getBundle().getString("app.currency_id") + " " + budget_input.getText();
        Money budget = Money.parse(money_string);

        LocalDate localDate = event_date_input.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);

        LocalTime starting_time = LocalTime.parse(starting_time_input.getText(), DateTimeFormat.forPattern(Launch.getBundle().getString("app.hour_format")));
        String current_locale = Launch.getLocale().getLanguage();
        EventRegistry.getEvent_list().add(new Event(date, event_name, starting_time, category, note, budget, new Locale(current_locale)));

        this.refresh_summary();
    }

    private void refresh_summary() {
        ArrayList<YearEventSummary> events_by_year = EventRegistry.prepare_registry();

        TreeItem<Event> root = new TreeItem<>(new YearEventSummary(0, new ArrayList<>()));
        tree_table.setShowRoot(false);

        for (YearEventSummary y :
                events_by_year) {

            TreeItem<Event> year = new TreeItem<>(y);

            for (QuarterEventSummary q :
                    y.getQuarters_in_year()) {
                TreeItem<Event> quarter = new TreeItem<>(q);

                for (Event e :
                        q.events_in_quarter) {
                    TreeItem<Event> event = new TreeItem<>(e);
                    quarter.getChildren().add(event);
                }
                year.getChildren().add(quarter);
            }
            root.getChildren().add(year);
        }

        tree_table.setRoot(root);
    }

    private void change_language(Locale locale) {
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
        Launch.setLocale(locale);

        this.internationalize_datapicker();
        this.refresh_summary();

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
