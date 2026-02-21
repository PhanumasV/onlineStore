package models;

import behavioralObserverPattern.User;

/**
 * The {@code Grocery} class represents a product in the "Grocery" category.
 * <p>
 * This class extends the {@link Product} class and overrides its methods to specify the
 * "Grocery" category and provide functionality for managing observers.
 * </p>
 */
public class Grocery extends Product {

    /**
     * Initializes a new {@code Grocery} product with the specified name and price.
     *
     * @param name The name of the grocery product.
     * @param price The price of the grocery product.
     */
    public Grocery(String name, double price) {
        super(name, price);
    }

    /**
     * Returns the category of this product.
     * <p>
     * This method always returns "Grocery" for products of this type.
     * </p>
     *
     * @return The string "Grocery" as the category of this product.
     */
    @Override
    public String getProductCategory() {
        return "Grocery";
    }

    /**
     * Adds a user as an observer to this product.
     * <p>
     * Observers can be notified of changes in the product's state, such as availability or updates.
     * This method is currently unimplemented.
     * </p>
     *
     * @param currentUser The user to be added as an observer.
     */
    @Override
    public void addObserver(User currentUser) {
        // For adding an observer
    }

    /**
     * Notifies all subscribed observers about a change in the product's status.
     * <p>
     * This method is currently unimplemented but would notify all users subscribed to this product
     * about changes such as availability or updates.
     * </p>
     *
     * @param string The message or status update to be sent to the observers.
     */
    @Override
    protected void notifyObservers(String string) {
        // Notifies subscribed users when the product becomes available
    }
}
