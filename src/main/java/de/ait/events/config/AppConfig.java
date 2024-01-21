package de.ait.events.config;

import de.ait.events.controllers.EventController;
import de.ait.events.repository.EventRepository;
import de.ait.events.repository.impl.EventRepositoryFileImpl;
import de.ait.events.service.EventService;
import de.ait.events.service.impl.EventServiceImpl;
import de.ait.events.validation.DateValidator;
import de.ait.events.validation.DescriptionValidator;
import de.ait.events.validation.impl.DateFormatValidatorImpl;
import de.ait.events.validation.impl.DateNotEmptyValidatorImpl;
import de.ait.events.validation.impl.DescriptionNotEmptyValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class AppConfig {

    @Bean
    public DescriptionValidator descriptionValidatorNotEmpty() {
        return new DescriptionNotEmptyValidatorImpl();
    }

    @Bean
    public DateValidator dateValidatorNotEmpty() {
        return new DateNotEmptyValidatorImpl();
    }

    @Bean
    public DateValidator dateValidatorFormat() {
        return new DateFormatValidatorImpl();
    }

    @Bean
    public EventRepository eventRepositoryFile() {
        return new EventRepositoryFileImpl("src/main/java/de/ait/events/events.txt");
    }
    @Bean
    public EventService eventService(EventRepository eventRepositoryFile, DescriptionValidator descriptionValidatorNotEmpty,
                                     DateValidator dateValidatorNotEmpty){
        return new EventServiceImpl(eventRepositoryFile, descriptionValidatorNotEmpty, dateValidatorNotEmpty);
    }
    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }
    @Bean
    public EventController eventController(Scanner scanner, EventService eventService){
        return new EventController(scanner, eventService);
    }

}
