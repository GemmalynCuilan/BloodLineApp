package com.example.bloodlineapp.model;

public class Event {
    private String event_name, event_description, event_venue, event_start_date, event_end_date, event_time;

    public Event( String event_name, String event_description, String event_venue, String event_start_date, String event_end_date, String event_time) {

        this.event_name = event_name;
        this.event_description = event_description;
        this.event_venue = event_venue;
        this.event_start_date = event_start_date;
        this.event_end_date = event_end_date;
        this.event_time = event_time;

    }


    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getEvent_venue() {
        return event_venue;
    }

    public void setEvent_venue(String event_venue) {
        this.event_venue = event_venue;
    }

    public String getEvent_start_date() {
        return event_start_date;
    }

    public void setEvent_start_date(String event_start_date) {
        this.event_start_date = event_start_date;
    }

    public String getEvent_end_date() {
        return event_end_date;
    }

    public void setEvent_end_date(String event_end_date) {
        this.event_end_date = event_end_date;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }
}