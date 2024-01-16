package de.ait.events.repository.impl;

import de.ait.events.modules.Event;
import org.junit.jupiter.api.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.nio.file.Files.deleteIfExists;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EventRepositoryFileImpl is working   ")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class EventRepositoryFileImplTest {

    private static final String TEMP_EVENTS_FILE_NAME = "event_test.txt";
    private EventRepositoryFileImpl eventRepository;

    @BeforeEach
    public void setup() throws IOException {
        createNewEventFileForTest();
        eventRepository = new EventRepositoryFileImpl(TEMP_EVENTS_FILE_NAME);
    }

    @AfterEach
    public void tearDown(){
        deleteFileAfterTest(TEMP_EVENTS_FILE_NAME);
    }
    @DisplayName("save event")
    @Nested
    class saveTest {
        @Test
        public void write_correct_line_to_file() throws IOException {
            Event event = new Event("description_1", LocalDate.of(2023, 5, 10));
            String expected = "1,description_1,2023-05-10";
            eventRepository.save(event);

            BufferedReader reader = new BufferedReader(new FileReader(TEMP_EVENTS_FILE_NAME));
            String actual = reader.readLine();

            assertEquals(expected, actual);
        }
    }

    @DisplayName("find all events")
    @Nested
    class findAllTest {
        @Test
        public void returns_correct_list_of_events() throws IOException {
            BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_EVENTS_FILE_NAME));
            writer.write("1,description_1,2023-05-10");
            writer.newLine();
            writer.write("2,description_2,2024-03-05");
            writer.newLine();
            writer.close();

            List<Event> expected = Arrays.asList(
                    new Event(1L, "description_1", LocalDate.of(2023, 5, 10)),
                    new Event(2L, "description_2", LocalDate.of(2024, 3, 5))
            );

            List<Event> actual = eventRepository.findAll();

            assertEquals(expected, actual);
        }
    }

    private void deleteFileAfterTest(String tempEventsFileName) {
        File file = new File(tempEventsFileName);
        deleteIfExists(file);
    }

    private void createNewEventFileForTest() throws IOException {
        File file = new File(EventRepositoryFileImplTest.TEMP_EVENTS_FILE_NAME);
        deleteIfExists(file);
        boolean result = file.createNewFile();
        if (!result) {
            throw new IllegalStateException("Problem with file. Can't create.");
        }
    }

    private void deleteIfExists(File file) {
        if (file.exists()) {
            boolean result = file.delete();
            if (!result) {
                throw new IllegalStateException("Problem with file. Can't delete.");
            }
        }
    }
}