package com.erait.mas_jaka.Model;

public class EventModel {
    private String event_id, event_name, event_desc, event_date, event_image ;

    public EventModel() {
    }

    public EventModel(String event_id, String event_name, String event_desc, String event_date, String event_image) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_desc = event_desc;
        this.event_date = event_date;
        this.event_image = event_image;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_desc() {
        return event_desc;
    }

    public void setEvent_desc(String event_desc) {
        this.event_desc = event_desc;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_image() {
        return event_image;
    }

    public void setEvent_image(String event_image) {
        this.event_image = event_image;
    }
}
