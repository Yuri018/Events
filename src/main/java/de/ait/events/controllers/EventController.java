package de.ait.events.controllers;

import de.ait.events.modules.Event;
import de.ait.events.service.impl.EventServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class EventController {

    private final Scanner scanner;
    private final EventServiceImpl eventService;

    public EventController(Scanner scanner, EventServiceImpl eventService) {
        this.scanner = scanner;
        this.eventService = eventService;
    }

    public void addEvent() {
        System.out.println("Enter event description: ");
        String description = scanner.nextLine();

        System.out.println("Enter the date in the format yyyy-MM-dd: ");
        String enterDate = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(enterDate, formatter);
            Event event = eventService.addEvent(description, date);
            System.out.println(event);
        } catch (Exception e) {
            System.out.println("Incorrect date input format. Input format: year-month-day");
        }
    }

    public void getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        System.out.println(events);
    }

    public void updateEvent() {
        System.out.println("Input ID: ");
        Long idForUpdate = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Input description: ");
        String newDescription = scanner.nextLine();

        System.out.println("Input date:  ");
        LocalDate newDate = LocalDate.parse(scanner.nextLine());

        eventService.updateEvent(idForUpdate, newDescription, newDate);
    }
}
