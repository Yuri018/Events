package de.ait.events.repository.impl;

import de.ait.events.modules.Event;
import de.ait.events.repository.EventRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class EventRepositoryFileImpl implements EventRepository {

    private Long generatedId = 1L;
    private String fileName;

    public EventRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            event.setId(findAll().size() + generatedId);//получаем длину списка юзеров и к этой цифре прибавляется 1

            writer.write(event.getId() + ", " + event.getDescription() + "," + event.getDate());
            writer.newLine();//переход на новую строку
            writer.flush();

        } catch (IOException e){
            throw new IllegalStateException("Problem with file." + e.getMessage());
        }
        generatedId++;

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void updateById(Long id) {

    }

    @Override
    public Event findById(Long id) {
        return null;
    }

    @Override
    public List<Event> findAll() {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            return reader.lines()
                    .map(line -> line.split(","))
                    .map(parsed -> new Event (Long.parseLong(parsed[0])//преобразуем String to Long
                            , parsed[1], LocalDate.parse((parsed[2]))))
                    .toList();

        } catch (IOException e){
            throw new IllegalStateException("Problem with file.");
        }
    }

    @Override
    public Event findByDescription(String description) {
        return findAll().stream()
                .filter(user -> user.getDescription().equals(description))
                .findFirst().orElse(null);
    }
}
