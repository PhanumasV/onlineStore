/**
 * The {@code Observer} interface defines the contract for objects that need to be notified 
 * about changes in the status of an item they are observing.
 * <p>
 * This interface is part of the {@code behavioralObserverPattern} package.
 * </p>
 * <p>
 * Classes implementing this interface should provide a concrete implementation of the
 * {@link #update(String)} method to handle status updates.
 * </p>
 * 
 * @author [Shane, Phanumas, Ju]
 * @version 1.0
 * @see behavioralObserverPattern
 */
package behavioralObserverPattern;

// Interface for observer
public interface Observer {

    /**
     * Updates the observer with the new status of the item being tracked.
     * 
     * @param status The updated status of the observed item. 
     *               This could be any string representation of the current state.
     */
    void update(String status);
}



