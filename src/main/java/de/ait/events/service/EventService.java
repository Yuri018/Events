package de.ait.events.service;

import de.ait.events.modules.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    Event addEvent(String description, LocalDate date);

    List<Event> getAllEvents();
}
