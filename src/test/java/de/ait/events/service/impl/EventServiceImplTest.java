package de.ait.events.service.impl;

import de.ait.events.modules.Event;
import de.ait.events.repository.EventRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("EventServiceImpl is working   ")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class EventServiceImplTest {
    private static final String EXISTED_EVENT_DESCRIPTION = "description_1";
    private static final String NOT_EXISTED_EVENT_DESCRIPTION = "description_3";
    private static final LocalDate DEFAULT_DATE = LocalDate.parse("2024-02-10");
    private static final Event EXISTED_EVENT = new Event(EXISTED_EVENT_DESCRIPTION, DEFAULT_DATE);
    private static final Event NOT_EXISTED_EVENT = new Event(NOT_EXISTED_EVENT_DESCRIPTION, DEFAULT_DATE);

    //Dependency
    private EventServiceImpl eventService;
    private EventRepository eventRepository;

    @BeforeEach
    public void setup(){
        eventRepository = Mockito.mock(EventRepository.class);

        when(eventRepository.findByDescription(EXISTED_EVENT_DESCRIPTION)).thenReturn(EXISTED_EVENT);
        when(eventRepository.findByDescription(NOT_EXISTED_EVENT_DESCRIPTION)).thenReturn(null);

        this.eventService = new EventServiceImpl(eventRepository);
    }

    @Test
    public void addEvent_on_incorrect_description_throws_exception(){
        assertThrows(IllegalArgumentException.class,
                () -> eventService.addEvent(null, LocalDate.parse("2024-04-04")));
    }
    @Test
    public void addEvent_on_incorrect_date_throws_exception() {
        assertThrows(IllegalArgumentException.class,
                () -> eventService.addEvent(EXISTED_EVENT_DESCRIPTION, null));
    }
    @Test
    public void on_existed_event_throw_exception(){
        assertThrows(IllegalArgumentException.class,
                () -> eventService.addEvent(EXISTED_EVENT_DESCRIPTION, DEFAULT_DATE));
    }
    @Test
    public void return_create_event(){
        Event actual = eventService.addEvent(NOT_EXISTED_EVENT_DESCRIPTION, DEFAULT_DATE);
        verify(eventRepository).save(NOT_EXISTED_EVENT);

        assertNotNull(actual);
    }
}