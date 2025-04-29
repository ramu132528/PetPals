package entity;

import java.time.LocalDate;

public class AdoptionEvent {
    private String eventName;
    private LocalDate eventDate;

    // Constructor with both name and date
    public AdoptionEvent(String eventName, LocalDate eventDate) {
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    // Constructor with only name; defaults to today’s date
    public AdoptionEvent(String eventName) {
        this.eventName = eventName;
        this.eventDate = LocalDate.now();
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }
}
