package ua.epam.spring.hometask.DAO;

import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Artsem_Mitrafanau
 */

public interface UserDAO extends DomainObjectDAO<User>{

    /**
     * Finding user by email
     *
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    public @Nullable
    User getUserByEmail(@Nonnull String email);

}
