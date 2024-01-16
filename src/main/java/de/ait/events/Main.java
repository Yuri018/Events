package de.ait.events;

import de.ait.events.controllers.EventController;
import de.ait.events.repository.EventRepository;
import de.ait.events.repository.impl.EventRepositoryFileImpl;
import de.ait.events.service.impl.EventServiceImpl;

import java.util.Scanner;



public class Main {

    private static final String FILE_NAME = "src/main/java/de/ait/events/events.txt";
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EventRepository eventRepository = new EventRepositoryFileImpl(FILE_NAME);
        EventServiceImpl eventService = new EventServiceImpl(eventRepository);
        EventController eventController = new EventController(scanner, eventService);

        boolean isRun = true;

        while (isRun) {
            System.out.println("Input command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "add Event" -> eventController.addEvent();
                case "all Events" -> eventController.getAllEvents();
                case "update Event" -> eventController.updateEvent();
                case "exit" -> isRun = false;
            }
        }
    }
}
