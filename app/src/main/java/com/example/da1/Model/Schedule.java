package com.example.da1.Model;

public class Schedule {
    private String subject;
    private String date;
    private String room;
    private String classTime;

    public Schedule() {
        // Firestore requires an empty constructor
    }

    public Schedule(String subject, String date, String room, String classTime) {
        this.subject = subject;
        this.date = date;
        this.room = room;
        this.classTime = classTime;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public String getRoom() {
        return room;
    }

    public String getClassTime() {
        return classTime;
    }
}
