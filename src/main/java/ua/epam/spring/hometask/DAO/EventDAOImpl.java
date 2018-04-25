package ua.epam.spring.hometask.DAO;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EventDAOImpl implements EventDAO{


    private static Set<Event> eventSet;

    public static void setEventSet(Set<Event> eventSet) {
        EventDAOImpl.eventSet = eventSet;
    }

    /**
     * Saving new object to storage or updating existing one
     *
     * @param object
     *            Object to save
     * @return saved object with assigned id
     */
    public Event save(@Nonnull Event object){
        object.setId((long) (Math.random()*1000000));
        eventSet.add(object);
        return object;
    }

    /**
     * Removing object from storage
     *
     * @param object
     *            Object to remove
     */
    public void remove(@Nonnull Event object){
        eventSet.remove(object);
    }

    /**
     * Getting object by id from storage
     *
     * @param id
     *            id of the object
     * @return Found object or <code>null</code>
     */
    public Event getById(@Nonnull Long id){
        for(Event e : eventSet){
            if(e.getId().equals(id)){
                return e.clone();
            }
        }
        return null;
    }

    /**
     * Getting all objects from storage
     *
     * @return collection of objects
     */
    public @Nonnull
    Set<Event> getAll(){
        Set<Event> eventSetCopy = new HashSet<>();
        for(Event e : eventSet){
            eventSetCopy.add(e.clone());
        }
        return eventSetCopy;
    }

}
