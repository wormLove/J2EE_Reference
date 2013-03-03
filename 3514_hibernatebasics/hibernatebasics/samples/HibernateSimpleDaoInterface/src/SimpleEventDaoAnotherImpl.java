import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


/**
 * The Data Access Object for managing the persistent Events.
 *
 * @author Patrick Peak
 * @author Nick Heudecker
 */
public class SimpleEventDaoAnotherImpl implements SimpleEventDaoInterface {
    Log log = LogFactory.getLog(SimpleEventDao.class);
    private Session session;
    private Transaction tx;

    public SimpleEventDaoAnotherImpl() {
        HibernateFactory.buildIfNeeded();
    }

    /**
     * Insert a new Event into the database.
     * @param event
     */
    public void create(Event event) throws DataAccessLayerException {
        try {
            startOperation();
            session.save(event);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }


    /**
     * Delete a detached Event from the database.
     * @param event
     */
    public void delete(Event event) throws DataAccessLayerException {
        try {
            startOperation();
            session.delete(event);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }
    /**
     * Find an Event by its primary key.
     * @param id
     * @return
     */
    public Event find(Long id) throws DataAccessLayerException {
        Event event = null;
        try {
            startOperation();
            event = (Event) session.load(Event.class, id);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            //HibernateFactory.close(session);
        }
        return event;
    }

    /**
     * Updates the state of a detached Event.
     *
     * @param event
     */
    public void update(Event event) throws DataAccessLayerException {
        try {
            startOperation();
            session.update(event);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }

    /**
     * Finds all Events in the database.
     * @return
     */
    public List findAll() throws DataAccessLayerException{
        List events = null;
        try {
            startOperation();
            Query query = session.createQuery("from Event");
            events =  query.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
        return events;
    }

    private void handleException(HibernateException e) throws DataAccessLayerException {
        HibernateFactory.rollback(tx);
        throw new DataAccessLayerException(e);
    }

    private void startOperation() throws HibernateException {
        session = HibernateFactory.openSession();
        tx = session.beginTransaction();
    }

}
