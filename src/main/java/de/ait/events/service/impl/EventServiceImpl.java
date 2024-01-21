package de.ait.events.service.impl;

import de.ait.events.modules.Event;
import de.ait.events.repository.EventRepository;
import de.ait.events.service.EventService;
import de.ait.events.validation.DateValidator;
import de.ait.events.validation.DescriptionValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventServiceImpl implements EventService {
    public final EventRepository eventRepository;
    public final DescriptionValidator descriptionValidator;
    public final DateValidator dateValidator;

    public EventServiceImpl(EventRepository eventRepository, DescriptionValidator descriptionValidator, DateValidator dateValidator) {
        this.eventRepository = eventRepository;
        this.descriptionValidator = descriptionValidator;
        this.dateValidator = dateValidator;
    }

    @Override
    public Event addEvent(String description, LocalDate date) {
//        if (description == null || description.isEmpty() || description.equals(" ")) {
//            throw new IllegalArgumentException("Description can't be empty");
//        }
        descriptionValidator.validate(description);

//        if (date == null || date.equals(" ")) {
//            throw new IllegalArgumentException("Date can't be empty");
//        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateToString = date.format(formatter);
        dateValidator.validate(dateToString);

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

    public void updateEvent(Long idForUpdate, String newDescription, LocalDate newDate) {
        List<Event> events = eventRepository.findAll();
        Event eventForUpdate = events.stream().filter(event -> event.getId() == idForUpdate)
                .findFirst().orElse(null);
        if (eventForUpdate == null) {
            throw new IllegalArgumentException("Event with ID <" + idForUpdate + "> not found");
        }
        if (!newDescription.isBlank()) {
            eventForUpdate.setDescription(newDescription);
        }
        if (!newDate.isEqual(newDate)) {
            eventForUpdate.setDate(newDate);
        }

        eventRepository.update(eventForUpdate);
    }
}
