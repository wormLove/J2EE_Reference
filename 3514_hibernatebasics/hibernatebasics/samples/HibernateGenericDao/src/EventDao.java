
import java.util.List;


public class EventDao extends AbstractDao {
    public EventDao() {
        super();
    }
    
    /**
     * Insert a new Event into the database.
     * @param event
     */
    public void create(Event event) throws DataAccessLayerException {
        super.saveOrUpdate(event);
    }
    
    
    /**
     * Delete a detached Event from the database.
     * @param event
     */
    public void delete(Event event) throws DataAccessLayerException {
        super.delete(event);
    }
    
    /**
     * Find an Event by its primary key.
     * @param id
     * @return
     */
    public Event find(Long id) throws DataAccessLayerException {
        return (Event) super.find(Event.class, id);
    }
    
    /**
     * Updates the state of a detached Event.
     *
     * @param event
     */
    public void update(Event event) throws DataAccessLayerException {
        super.saveOrUpdate(event);
    }
    
    /**
     * Finds all Events in the database.
     * @return
     */
    public List findAll() throws DataAccessLayerException{
        return super.findAll(Event.class);
    }
    
}
