package models;

import behavioralObserverPattern.User;

/**
 * The {@code Clothing} class represents a product in the "Clothing" category.
 * <p>
 * This class extends the {@link Product} class and overrides its methods to specify the
 * "Clothing" category and provide functionality for managing observers.
 * </p>
 */
public class Clothing extends Product {

    /**
     * Initializes a new {@code Clothing} product with the specified name and price.
     *
     * @param name The name of the clothing product.
     * @param price The price of the clothing product.
     */
    public Clothing(String name, double price) {
        super(name, price);
    }

    /**
     * Returns the category of this product.
     * <p>
     * This method always returns "Clothing" for products of this type.
     * </p>
     *
     * @return The string "Clothing" as the category of this product.
     */
    @Override
    public String getProductCategory() {
        return "Clothing";
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
