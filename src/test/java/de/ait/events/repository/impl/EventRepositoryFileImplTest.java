package de.ait.events.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.io.File;
import java.io.IOException;

@DisplayName("EventRepositoryFileImpl is working   ")
@DisplayNameGeneration(value = DisplayNameGenerator.class)
class EventRepositoryFileImplTest {

    private static final String TEMP_EVENTS_FILE_NAME = "event_test.txt";
    private EventRepositoryFileImpl eventRepository;

    @BeforeEach
    public void setup() throws IOException {
        createNewEventFileForTest();
        eventRepository = new EventRepositoryFileImpl(TEMP_EVENTS_FILE_NAME);

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