package de.ait.events.modules;

import java.time.LocalDate;
import java.util.Objects;

public class Event {
    private long id;
    private String description;
    private LocalDate date;

    public Event(long id, String description, LocalDate date) {
        this.id = id;
        this.description = description;
        this.date = date;
    }


    public Event() {
    }

    public Event(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    public Event(String description, String date) {
        this.description = description;
        this.date = LocalDate.parse(date);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
