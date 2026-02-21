package behavioralObserverPattern;

/**
 * Represents a user who subscribes to updates from a {@link Subject} and gets notified of changes.
 * <p>
 * The {@code User} class implements the {@link Observer} interface, allowing users to receive
 * notifications whenever the subject's state changes.
 * </p>
 */
public class User implements Observer {
    private String name; // User name for those who subscribe to notifications

    /**
     * Initializes a new user with the specified name.
     * <p>
     * This constructor sets the user's name, which will be used in notifications to identify the user.
     * </p>
     *
     * @param name The name of the user.
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * This method is called when a {@link Subject} updates its status and notifies all observers.
     * <p>
     * The {@code update} method displays a notification with the new status of the subject.
     * </p>
     *
     * @param status The updated status provided by the subject.
     */
    @Override
    public void update(String status) {
        // Message displayed
        System.out.println("Notification for " + name + ": " + status);
    }
}

