package ua.epam.spring.hometask.DAO;

import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Artsem_Mitrafanau
 */

public class UserDAOImpl implements UserDAO {

    private static Set<User> userSet;

    /**
     * Finding user by email
     *
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    public @Nullable
    User getUserByEmail(@Nonnull String email){
        for(User u : userSet){
            if(u.getEmail().equals(email)){
                return u.clone();
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
    public User save(@Nonnull User object){
        object.setId((long) (Math.random()*1000000));
        userSet.add(object);
        return object;
    }

    /**
     * Removing object from storage
     *
     * @param object
     *            Object to remove
     */
    public void remove(@Nonnull User object){
        userSet.remove(object);
    }

    /**
     * Getting object by id from storage
     *
     * @param id
     *            id of the object
     * @return Found object or <code>null</code>
     */
    public User getById(@Nonnull Long id){
        for(User u : userSet){
            if(u.getId().equals(id)){
                return u.clone();
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
    Collection<User> getAll(){
        Set<User> userSetCopy = new HashSet<>();
        for(User u : userSet){
            userSetCopy.add(u.clone());
        }
        return userSetCopy;
    }

}
