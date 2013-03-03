import java.util.HashSet;
import java.util.Set;

public class AttendeeManyToMany {

    private Long id;
    private String firstName;
    private String lastName;
    private Set events;

    public AttendeeManyToMany() {
    }

    public AttendeeManyToMany(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public AttendeeManyToMany(String firstName, String lastName, EventManyToMany event) {
        this(firstName, lastName);
        addEvent(event);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set getEvents() {
        return this.events;
    }

    public void setEvents(Set events) {
        this.events = events;
    }

    private void addEvent(EventManyToMany event) {
        if (events == null) {
            events = new HashSet();
        }
        events.add(event);
    }

}
