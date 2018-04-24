package ua.epam.spring.hometask.DAO;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.DomainObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Artsem_Mitrafanau
 */

public class AuditoriumDAOImpl implements AuditoriumDAO {

    private static Set<Auditorium> auditoriumsSet;

    public AuditoriumDAOImpl(){
    }
//    TODO create auditoriumSet setter

    public static void setAuditoriumsSet(Set<Auditorium> auditoriumsSet) {
        AuditoriumDAOImpl.auditoriumsSet = auditoriumsSet;
    }

    /**
     * Getting all auditoriums from the system
     *
     * @return set of all auditoriums
     */
    public Set<Auditorium> getAll(){
        Set<Auditorium> auditoriumSetCopy = new HashSet<>();
        for(Auditorium a : auditoriumsSet){
            auditoriumSetCopy.add(a.clone());
        }
        return auditoriumSetCopy;
    }

    /**
     * Finding auditorium by name
     *
     * @param name
     *            Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
    public Auditorium getByName(@Nonnull String name){
        for(Auditorium a : auditoriumsSet){
            if(a.getName().equals(name)){
                return a.clone();
            }
        }
        return null;
    }

    /**
     * Saving new object to storage or updating existing one
     *
     * @param object
     *            Object to save
     * @return saved object with assigned id
     */
    public Auditorium save(@Nonnull Auditorium object) {
        object.setId((long) (Math.random()*1000000));
        auditoriumsSet.add(object);
        return object;
    }

    /**
     * Removing object from storage
     *
     * @param object
     *            Object to remove
     */
    public void remove(@Nonnull Auditorium object){
        auditoriumsSet.remove(object);
    }

    /**
     * Getting object by id from storage
     *
     * @param id
     *            id of the object
     * @return Found object or <code>null</code>
     */
    public Auditorium getById(@Nonnull Long id){
        for(Auditorium a : auditoriumsSet){
            if(a.getId().equals(id)){
                return a.clone();
            }
        }
        return null;
    }

}
