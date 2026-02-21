package behavioralObserverPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Subject} class is an abstract class representing an entity that maintains a list of
 * observers. This class allows observers to be added, removed, and notified of changes in the subject's state.
 * <p>
 * This class serves as a base for concrete subjects that need to notify observers about changes in their state.
 * </p>
 */
public abstract class Subject {
    // A list of all observers subscribed to this subject
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Adds an observer to the list of observers if it is not already subscribed.
     * <p>
     * This method ensures that the observer is not added multiple times to the list.
     * </p>
     *
     * @param observer The observer to be added to the list.
     */
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Removes an observer from the list of observers.
     * <p>
     * This method allows an observer to stop receiving updates from the subject.
     * </p>
     *
     * @param observer The observer to be removed from the list.
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all subscribed observers about a change in the subject's status.
     * <p>
     * This method sends a status update to each observer, invoking their {@link Observer#update(String)} method.
     * </p>
     *
     * @param status The status message to be sent to all observers.
     */
    protected void notifyObservers(String status) {
        System.out.println("Notifying observers about: " + status); // Debugging
        for (Observer observer : observers) {
            observer.update(status);
        }
    }
}
