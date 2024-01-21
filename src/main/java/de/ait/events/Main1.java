package de.ait.events;

import de.ait.events.config.AppConfig;
import de.ait.events.controllers.EventController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        Scanner scanner = applicationContext.getBean(Scanner.class);
        EventController eventController = applicationContext.getBean(EventController.class);

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
