package com.registry.partyregistry;

import org.joda.money.Money;
import org.joda.time.LocalTime;

import java.text.DateFormat;
import java.util.Date;

public class Event {
    private Date date;
    private String name;
    private LocalTime starting_time;
    private String category;
    private String notes;
    private Money budget;

    private String locale_id;


    public String getDate() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Launch.getLocale());
        return df.format(date);
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
        return starting_time.toString("HH:mm", Launch.getLocale());
    }

    public void setStarting_time(String starting_time) {
        this.starting_time = LocalTime.parse(starting_time);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBudget() {
        return budget.toString();
    }

    public void setBudget(String budget) {
        this.budget = Money.parse(budget);
    }
}
