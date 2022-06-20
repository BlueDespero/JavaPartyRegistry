package com.registry.partyregistry.event;

import com.registry.partyregistry.Launch;
import org.joda.money.Money;

import java.util.ArrayList;
import java.util.Date;

public class YearEventSummary extends Event {

    private final ArrayList<QuarterEventSummary> quarters_in_year;
    private final int year;

    public YearEventSummary(int year, ArrayList<Event> events_in_year) {
        super();
        this.year = year;
        quarters_in_year = new ArrayList<>();

        ArrayList<Event> Q1 = new ArrayList<>();
        ArrayList<Event> Q2 = new ArrayList<>();
        ArrayList<Event> Q3 = new ArrayList<>();
        ArrayList<Event> Q4 = new ArrayList<>();

        for (Event e :
                events_in_year) {
            int event_quarter = e.getQuarter();
            switch (event_quarter) {
                case 1 -> Q1.add(e);
                case 2 -> Q2.add(e);
                case 3 -> Q3.add(e);
                case 4 -> Q4.add(e);
            }
        }

        if (!Q1.isEmpty())
            quarters_in_year.add(new QuarterEventSummary(1, this.year, Q1));
        if (!Q2.isEmpty())
            quarters_in_year.add(new QuarterEventSummary(2, this.year, Q2));
        if (!Q3.isEmpty())
            quarters_in_year.add(new QuarterEventSummary(3, this.year, Q3));
        if (!Q4.isEmpty())
            quarters_in_year.add(new QuarterEventSummary(4, this.year, Q4));

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
    public void setCategory(String category) {
        super.setCategory(category);
    }

    @Override
    public String getNotes() {
        return String.format(Launch.getBundle().getString("summarytab.yearcomment"), this.year);
    }

    @Override
    public void setNotes(String notes) {
        super.setNotes(notes);
    }

    @Override
    public String getBudget() {
        Money year_budget = Money.parse(Launch.getBundle().getString("app.currency_id") + " 0");
        for (QuarterEventSummary e :
                quarters_in_year) {
            System.out.println("Before"+e.getBudget());
            year_budget = year_budget.plus(Money.parse(e.getBudget()));
            System.out.println("After"+ year_budget);
        }
        return year_budget.toString();
    }

    @Override
    public void setBudget(String budget) {
        super.setBudget(budget);
    }

    public ArrayList<QuarterEventSummary> getQuarters_in_year() {
        return quarters_in_year;
    }
}
