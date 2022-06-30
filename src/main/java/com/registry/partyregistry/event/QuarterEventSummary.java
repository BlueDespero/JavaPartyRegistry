package com.registry.partyregistry.event;

import com.registry.partyregistry.Launch;
import org.joda.money.Money;

import java.util.ArrayList;
import java.util.Date;

public class QuarterEventSummary extends Event {

    private int quarter_number;
    private int year;
    public ArrayList<Event> events_in_quarter;

    public QuarterEventSummary(int quarter_number, int year, ArrayList<Event> events_in_quarter) {
        this.quarter_number = quarter_number;
        this.year = year;
        this.events_in_quarter = events_in_quarter;
    }

    @Override
    public String getDate() {
        return "";
    }

    @Override
    public void setDate(Date date) {
        super.setDate(date);
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getStarting_time() {
        return "";
    }

    @Override
    public void setStarting_time(String starting_time) {
        super.setStarting_time(starting_time);
    }

    @Override
    public String getCategory() {
        return "";
    }

    @Override
    public void setCategory(Integer category) {
        super.setCategory(category);
    }

    @Override
    public String getNotes() {
        return String.format(Launch.getBundle().getString("summarytab.quartetcomment"), quarter_number, year);
    }

    @Override
    public void setNotes(String notes) {
        super.setNotes(notes);
    }

    @Override
    public String getBudget() {
        Money quarter_budget = Money.parse(Launch.getBundle().getString("app.currency_id") + " 0");
        for (Event e :
                events_in_quarter) {
            quarter_budget = quarter_budget.plus(Money.parse(e.getBudget()));
        }
        return quarter_budget.toString();
    }

    @Override
    public void setBudget(String budget) {
        super.setBudget(budget);
    }
}
