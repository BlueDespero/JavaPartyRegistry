package com.registry.partyregistry.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventRegistry {
    static ArrayList<Event> event_list = new ArrayList<>();

    public void add_event() {

    }

    public ArrayList<YearEventSummary> prepare_registry(){
        ArrayList<YearEventSummary> years_list = new ArrayList<>();

        Map<Object, List<Event>> eventsGrouped =
                event_list.stream().collect(Collectors.groupingBy(Event::getYear));

        for (Map.Entry<Object, List<Event>> entry : eventsGrouped.entrySet()) {
            years_list.add(new YearEventSummary((Integer) entry.getKey(), (ArrayList<Event>) entry.getValue()));
        }

        return years_list;
    }
}
