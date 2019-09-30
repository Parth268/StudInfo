package com.example.studinfo;

public class attribute {

    String college,event,city,date;

    public attribute(){
        this.college = college;
        this.event = event;
        this.city = city;
        this.date = date;
    }

    public attribute(String college, String event, String city, String date) {
        this.college = college;
        this.event = event;
        this.city = city;
        this.date = date;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
