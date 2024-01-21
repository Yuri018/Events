package de.ait.events.repository.impl;

import de.ait.events.modules.Event;
import de.ait.events.repository.EventRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EventRepositoryFileImpl implements EventRepository {

    private Long generatedId = 1L;
    private final String fileName;

    public EventRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            event.setId(findAll().size() + generatedId);//получаем длину списка юзеров и к этой цифре прибавляется 1

            writer.write(event.getId() + "," + event.getDescription() + "," + event.getDate());
            writer.newLine();//переход на новую строку
            writer.flush();

        } catch (IOException e){
            throw new IllegalStateException("Problem with file." + e.getMessage());
        }
//        generatedId++;

    }

    @Override
    public void deleteById(Long id) {
        List<Event> events = findAll();
        Event eventForDelete = events.stream()
                .filter(event -> event.getId() == id)
                .findFirst()
                .orElse(null);
        events.remove(eventForDelete);

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
                    .collect(Collectors.toList());

        } catch (IOException e){
            throw new IllegalStateException("Problem with file.");
        }
    }

    @Override
    public void update(Event eventUpdate) {
        List<Event> events = findAll();
        Event eventForUpdate = events.stream()
                .filter(event -> event.getId() == eventUpdate.getId())
                .findFirst()
                .orElse(null);

        System.out.println(eventForUpdate);

        events.remove(eventForUpdate);//здесь ошибка - решено
        events.add(eventUpdate);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for (Event e: events){
                writer.write(e.getId() + "," + e.getDescription() + "," + e.getDate());
                writer.newLine();
            }
        } catch (IOException e){
            throw new IllegalArgumentException("Problem with file." + e.getMessage());
        }
    }

    @Override
    public Event findByDescription(String description) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(line -> line.split(","))
                    .filter(parsed -> parsed[1].contains(description)) // совпадение по подстроке
                    .findFirst()
                    .map(parsed -> new Event(Long.parseLong(parsed[0]), parsed[1], LocalDate.parse(parsed[2])))
                    .orElse(null);

        } catch (IOException e) {
            throw  new IllegalStateException("Problem with file.");
        }
    }
}
