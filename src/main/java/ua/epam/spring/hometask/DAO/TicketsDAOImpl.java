package ua.epam.spring.hometask.DAO;

import ua.epam.spring.hometask.domain.Ticket;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Artsem_Mitrafanau
 */

public class TicketsDAOImpl implements TicketsDAO {

        private static Set<Ticket> ticketSet;

        /**
         * Saving new object to storage or updating existing one
         *
         * @param object
         *            Object to save
         * @return saved object with assigned id
         */
        public Ticket save(@Nonnull Ticket object){
            object.setId((long) (Math.random()*1000000));
            ticketSet.add(object);
            return object;
        }

        /**
         * Removing object from storage
         *
         * @param object
         *            Object to remove
         */
        public void remove(@Nonnull Ticket object){
            ticketSet.remove(object);
        }

        /**
         * Getting object by id from storage
         *
         * @param id
         *            id of the object
         * @return Found object or <code>null</code>
         */
        public Ticket getById(@Nonnull Long id){
            for(Ticket t : ticketSet){
                if(t.getId().equals(id)){
                    return t.clone();
                }
            }
            return null;
        }

        /**
         * Getting all objects from storage
         *
         * @return collection of objects
         */
        public @Nonnull Collection<Ticket> getAll(){
            Set<Ticket> ticketSetCopy = new HashSet<>();
            for(Ticket t : ticketSet){
                ticketSetCopy.add(t.clone());
            }
            return ticketSetCopy;
        }
}
