package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.DAO.UserDAO;
import ua.epam.spring.hometask.domain.User;

import java.util.Collection;

public class UserServiceImpl implements UserService {

    UserDAO userDAO;

    /**
     * Finding user by email
     *
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    public User getUserByEmail(String email){
        User user = userDAO.getUserByEmail(email);
        return user;
    }

    /**
     * Saving new object to storage or updating existing one
     *
     * @param object
     *            Object to save
     * @return saved object with assigned id
     */
    public User save(User object){
        return userDAO.save(object);
    }

    /**
     * Removing object from storage
     *
     * @param object
     *            Object to remove
     */
    public void remove(User object){
        userDAO.remove(object);
    }

    /**
     * Getting object by id from storage
     *
     * @param id
     *            id of the object
     * @return Found object or <code>null</code>
     */
    public User getById(Long id){
        return userDAO.getById(id);
    }

    /**
     * Getting all objects from storage
     *
     * @return collection of objects
     */
    public Collection<User> getAll(){
        return userDAO.getAll();
    }

}
