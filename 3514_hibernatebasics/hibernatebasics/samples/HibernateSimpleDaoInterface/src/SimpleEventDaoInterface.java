/*
 * SimpleEventDaoInterface.java
 *
 * Created on April 23, 2007, 2:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.util.List;
/**
 *
 * @author sang
 */
public interface SimpleEventDaoInterface {
    /**
     * Insert a new Event into the database.
     * 
     * @param event
     */
    void create(Event event) throws DataAccessLayerException;

    /**
     * Delete a detached Event from the database.
     * 
     * @param event
     */
    void delete(Event event) throws DataAccessLayerException;

    /**
     * Find an Event by its primary key.
     * 
     * @param id
     * @return 
     */
    Event find(Long id) throws DataAccessLayerException;

    /**
     * Finds all Events in the database.
     * 
     * @return 
     */
    List findAll() throws DataAccessLayerException;

    /**
     * Updates the state of a detached Event.
     * 
     * 
     * @param event
     */
    void update(Event event) throws DataAccessLayerException;
    
}
