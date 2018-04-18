package ua.epam.spring.hometask.DAO;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.DomainObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

/**
 * @author Artsem_Mitrafanau
 */

public interface AuditoriumDAO extends DomainObjectDAO<Auditorium>{
    /**
     * Getting all auditoriums from the system
     *
     * @return set of all auditoriums
     */
    public @Nonnull
    Set<Auditorium> getAll();

    /**
     * Finding auditorium by name
     *
     * @param name
     *            Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
    public @Nullable
    Auditorium getByName(@Nonnull String name);

}
