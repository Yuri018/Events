package de.ait.events.repository;

import de.ait.events.modules.Event;

public interface EventRepository extends CrudRepository<Event>{
    Event findByDescription(String description);
}
