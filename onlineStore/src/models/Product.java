package models;

import behavioralObserverPattern.User;
import java.util.ArrayList;
import java.util.List;
import structuralAdapterPattern.Discountable;

/**
 * The {@code Product} class represents a generic product in the system.
 * <p>
 * This class implements the {@link Discountable} interface and serves as the base class
 * for different types of products, such as electronics, clothing, and groceries.
 * It includes information about the product, including its name, price, discount, reviews, 
 * and a list of subscribed observers.
 * </p>
 */
public abstract class Product implements Discountable {
    private String name; 
    private double price; 
    private double discount; 
    private List<ProductReview> reviews; // Reviews
    private List<User> observers; // List of subscribed users

    /**
     * Initializes a new product with the specified name and price.
     * <p>
     * The product is initialized with no discount and an empty reviews list. Observers are also initialized as an empty list.
     * </p>
     *
     * @param name The name of the product.
     * @param price The price of the product.
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.discount = 0; // No discount by default
        this.reviews = new ArrayList<>(); // Initialize reviews list
        this.observers = new ArrayList<>();
    }

    /**
     * Retrieves the name of the product.
     *
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return The price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Retrieves the category of the product.
     * <p>
     * This method is abstract and must be implemented by subclasses to specify the product's category.
     * </p>
     *
     * @return The category of the product.
     */
    public abstract String getProductCategory();

    /**
     * Applies a discount to the product.
     * <p>
     * The discount is specified as a percentage, and it must be between 0 and 100. 
     * If the percentage is invalid, an {@link IllegalArgumentException} is thrown.
     * </p>
     *
     * @param percentage The discount percentage to be applied to the product.
     * @throws IllegalArgumentException If the percentage is less than 0 or greater than 100.
     */
    @Override
    public void applyDiscount(double percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100%");
        }
        this.discount = percentage;
    }

    /**
     * Retrieves the discounted price of the product.
     * <p>
     * The discounted price is calculated as the original price minus the discount percentage.
     * </p>
     *
     * @return The discounted price of the product.
     */
    @Override
    public double getDiscountedPrice() {
        return price * (1 - discount / 100);
    }

    /**
     * Displays the product details, including its category, original price, and discounted price (if applicable).
     * <p>
     * If a discount is applied, both the original price and the discounted price are shown. 
     * Otherwise, only the original price is displayed.
     * </p>
     *
     * @return A formatted string containing the product's name, category, price, and discounted price (if applicable).
     */
    public String displayInfo() {
        double discountedPrice = getDiscountedPrice();
        if (discount > 0) {
            // Show original and discounted prices if a discount is applied
            return String.format("%s (%s): Original Price: $%.2f, Discounted Price: $%.2f",
                    name, getProductCategory(), price, discountedPrice);
        } else {
            // Show only the original price if no discount is applied
            return String.format("%s (%s): $%.2f", name, getProductCategory(), price);
        }
    }

    /**
     * Adds a review to the product.
     * <p>
     * This method adds the given {@link ProductReview} to the list of reviews and notifies all subscribed observers.
     * </p>
     *
     * @param review The review to be added to the product.
     */
    public void addReview(ProductReview review) {
        reviews.add(review); // Add the review to the reviews list
        // Notify observers
        notifyObservers("New review added for " + getName() + ": \"" + review.getReviewText() + "\" (Rating: " + review.getRating() + ")");
    }

    /**
     * Displays all reviews for the product.
     * <p>
     * If no reviews are available, a message is displayed indicating that no reviews are available for the product.
     * </p>
     */
    public void displayReviews() {
        if (reviews.isEmpty()) {
            System.out.println("No reviews for " + name);
        } else {
            System.out.println("Reviews for " + name + ":");
            for (ProductReview review : reviews) {
                System.out.println("- " + review);
            }
        }
    }

    /**
     * Adds a user as an observer to the product.
     * <p>
     * Observers can be notified about updates regarding the product, such as changes in availability or reviews.
     * </p>
     *
     * @param user The user to be added as an observer.
     */
    public void addObserver(User user) {
        if (!observers.contains(user)) {
            observers.add(user);
        }
    }

    /**
     * Notifies all subscribed observers about an update.
     * <p>
     * This method sends a message to all users who are observing the product, notifying them of any updates.
     * </p>
     *
     * @param message The message to be sent to the observers.
     */
    protected void notifyObservers(String message) {
        for (User observer : observers) {
            observer.update(message);
        }
    }
}
