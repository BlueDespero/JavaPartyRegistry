package com.registry.partyregistry.event;

import com.registry.partyregistry.Launch;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.LocalTime;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Event {
    private Date date;
    private String name;
    private LocalTime starting_time;
    private Integer category;
    private String notes;
    private Money budget;

    private Locale initial_internationalization;

    public Event(Date date, String name, LocalTime starting_time, Integer category, String notes, Money budget, Locale initial_internationalization) {
        this.date = date;
        this.name = name;
        this.starting_time = starting_time;
        this.category = category;
        this.notes = notes;
        this.budget = budget;
        this.initial_internationalization = initial_internationalization;
    }

    public Event() {

    }

    public String getDate() {
        final String NEW_FORMAT = Launch.getBundle().getString("app.dateformat");

        SimpleDateFormat sdf = new SimpleDateFormat(NEW_FORMAT);
        return sdf.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarting_time() {
        return starting_time.toString(Launch.getBundle().getString("app.hour_format"), Launch.getLocale());
    }

    public void setStarting_time(String starting_time) {
        this.starting_time = LocalTime.parse(starting_time);
    }

    public String getCategory() {
        return Launch.getBundle().getString("app.categories").split(" ")[this.category];
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBudget() {

        String new_currency = Launch.getBundle().getString("app.currency_id");

        double exchange_rate = Double.parseDouble(ResourceBundle.getBundle("com.registry.partyregistry.Bundle",
                this.initial_internationalization).getString(String.format("app.to_%s", new_currency)));

        return budget.convertedTo(CurrencyUnit.of(new_currency), BigDecimal.valueOf(exchange_rate), RoundingMode.DOWN).toString();
    }

    public void setBudget(String budget) {
        this.budget = Money.parse(budget);
    }


    protected int getQuarter() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        return month / 4 + 1;
    }

    public Object getYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }
}
