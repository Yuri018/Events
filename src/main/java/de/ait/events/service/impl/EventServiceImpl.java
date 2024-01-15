package de.ait.events.service.impl;

import de.ait.events.modules.Event;
import de.ait.events.repository.EventRepository;
import de.ait.events.service.EventService;

import java.time.LocalDate;
import java.util.List;

public class EventServiceImpl implements EventService {
    public final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event addEvent(String description, LocalDate date) {
        if (description == null || description.isEmpty() || description.equals(" ")) {
            throw new IllegalArgumentException("Description can't be empty");
        }
        if (date == null || date.equals(" ")) {
            throw new IllegalArgumentException("Date can't be empty");
        }
        Event existedEvent = eventRepository.findByDescription(description);
        if (existedEvent != null) {
            throw new IllegalArgumentException("ID already exists.");
        }

        Event event = new Event(description, date);

        eventRepository.save(event);

        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
