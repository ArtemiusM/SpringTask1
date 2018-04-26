package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.DAO.EventDAO;
import ua.epam.spring.hometask.domain.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EventServiceImpl implements EventService{

    private EventDAO eventDAO;

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    /**
     * Saving new object to storage or updating existing one
     *
     * @param object
     *            Object to save
     * @return saved object with assigned id
     */
    public Event save(Event object){
        return eventDAO.save(object);
    }

    /**
     * Removing object from storage
     *
     * @param object
     *            Object to remove
     */
    public void remove(Event object){
        eventDAO.remove(object);
    }

    /**
     * Getting object by id from storage
     *
     * @param id
     *            id of the object
     * @return Found object or <code>null</code>
     */
    public Event getById(Long id){
        return eventDAO.getById(id);
    }

    /**
     * Getting all objects from storage
     *
     * @return collection of objects
     */
    public Collection<Event> getAll(){
        return eventDAO.getAll();
    }

    public Set<Event> getForDateRange(LocalDate from, LocalDate to){
        Set<Event> eventsInRange = new HashSet<>();
        Set<Event> eventSet = (Set<Event>) eventDAO.getAll();

        for (Event t : eventSet) {
            LocalDate eventStartDate = t.getAirDates().first().toLocalDate();
                if((eventStartDate.isAfter(from) || eventStartDate.isEqual(from)) && ((eventStartDate.isBefore(to) || eventStartDate.isEqual(to)))){
                    eventsInRange.add(t);
                }
        }
        return eventsInRange;
    }

    public Event getByName(String name){
        return eventDAO.getByName(name);
    }
}
