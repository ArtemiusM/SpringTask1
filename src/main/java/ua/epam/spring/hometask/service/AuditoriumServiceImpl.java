package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.DAO.AuditoriumDAO;
import ua.epam.spring.hometask.domain.Auditorium;

import java.util.Set;

public class AuditoriumServiceImpl implements AuditoriumService{

    AuditoriumDAO auditoriumDAO;

    /**
     * Getting all auditoriums from the system
     *
     * @return set of all auditoriums
     */
    public Set<Auditorium> getAll(){
        return auditoriumDAO.getAll();
    }

    /**
     * Finding auditorium by name
     *
     * @param name
     *            Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
    public Auditorium getByName(String name){
        return auditoriumDAO.getByName(name);
    }

}
