package com.example.bloodlineapp.model;

public class Event {
    private String title, dtevent, name, description;

    public Event(String title, String dtevent, String name, String description){
        this.title = title;
        this.dtevent = dtevent;
        this.name = name;
        this.description = description;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDtevent() {
        return dtevent;
    }

    public void setDtevent(String dtevent) {
        this.dtevent = dtevent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
