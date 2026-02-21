package structuralCompositePattern;

import behavioralObserverPattern.User;
import java.util.ArrayList;
import java.util.List;
import models.Product;

/**
 * The {@code ProductComposite} class represents a composite product made up of multiple individual products.
 * <p>
 * This class allows you to group individual products into a single composite product. It supports applying
 * discounts to all products within the composite, calculating the total discounted price, and setting a fixed
 * price for the composite. It also allows adding or removing individual products from the composite.
 * </p>
 */
public class ProductComposite extends Product {
    private Double fixedPrice = null; // Optional fixed price for the composite
    private List<Product> products = new ArrayList<>(); // Holds individual products that make up the composite

    /**
     * Creates a composite product with a specified name but no price.
     * <p>
     * The price is initially set to 0, and the composite can later have a fixed price assigned to it.
     * </p>
     *
     * @param name The name of the composite product.
     */
    public ProductComposite(String name) {
        super(name, 0);
    }

    /**
     * Returns the category of the composite product.
     *
     * @return The string "Composite" representing the category of the composite product.
     */
    @Override
    public String getProductCategory() {
        return "Composite";
    }

    /**
     * Applies a discount to all products in the composite.
     * <p>
     * If the composite has a fixed price, discounts cannot be applied. Otherwise, the discount is applied
     * to all individual products that make up the composite.
     * </p>
     *
     * @param percentage The discount percentage to apply to the products in the composite.
     */
    @Override
    public void applyDiscount(double percentage) {
        if (fixedPrice != null) {
            System.out.println("Cannot apply discounts to a composite with a fixed price.");
        } else {
            // Applying discount to all products in the composite
            for (Product product : products) {
                product.applyDiscount(percentage);
            }
        }
    }

    /**
     * Retrieves the total discounted price of the composite.
     * <p>
     * If the composite has a fixed price, that price is returned. Otherwise, the price is calculated as
     * the sum of the discounted prices of all individual products that make up the composite.
     * </p>
     *
     * @return The total discounted price of the composite.
     */
    @Override
    public double getDiscountedPrice() {
        if (fixedPrice != null) {
            return fixedPrice; // Return the fixed price if set
        }
        // Calculate price from the sum of the discounted prices of all components
        return products.stream().mapToDouble(Product::getDiscountedPrice).sum();
    }

    /**
     * Sets a fixed price for the composite.
     * <p>
     * This method assigns a fixed price to the composite, overriding the dynamic pricing calculation based on its components.
     * </p>
     *
     * @param price The fixed price to assign to the composite.
     * @throws IllegalArgumentException If the price is less than 0.
     */
    public void setFixedPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.fixedPrice = price;
        System.out.printf("Fixed price of $%.2f assigned to composite \"%s\".%n", price, getName());
    }

    /**
     * Adds an individual product to the composite.
     * <p>
     * If the product is already part of the composite, it will not be added again.
     * </p>
     *
     * @param product The product to be added to the composite.
     */
    public void addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
            System.out.println("Product \"" + product.getName() + "\" added to composite \"" + getName() + "\".");
        } else {
            System.out.println("Product \"" + product.getName() + "\" is already part of the composite.");
        }
    }

    /**
     * Removes an individual product from the composite.
     * <p>
     * If the product is not part of the composite, a message is displayed indicating that the product is not in the composite.
     * </p>
     *
     * @param product The product to be removed from the composite.
     */
    public void removeProduct(Product product) {
        if (products.remove(product)) {
            System.out.println("Product \"" + product.getName() + "\" removed from composite \"" + getName() + "\".");
        } else {
            System.out.println("Product \"" + product.getName() + "\" is not part of the composite.");
        }
    }

    /**
     * Retrieves all products that are part of the composite.
     * <p>
     * This method returns a copy of the list of products to avoid external modifications to the original list.
     * </p>
     *
     * @return A list of products that are part of the composite.
     */
    public List<Product> getProducts() {
        return new ArrayList<>(products); // Return a copy to avoid external modification
    }

    /**
     * Displays the details of the composite product, including its components and total price.
     * <p>
     * This method prints the name of the composite, its components, and the total discounted price.
     * If no products are added to the composite, it indicates that there are no components.
     * </p>
     */
    public void displayCompositeDetails() {
        System.out.println("Composite Product: " + getName());
        if (products.isEmpty()) {
            System.out.println("No components in this composite.");
        } else {
            System.out.println("Components:");
            for (Product product : products) {
                System.out.printf("- %s: $%.2f%n", product.getName(), product.getDiscountedPrice());
            }
        }
        System.out.printf("Total Price: $%.2f%n", getDiscountedPrice());
    }

    /**
     * Displays the product information for the composite, including its name, category, and price.
     * <p>
     * This method overrides the `displayInfo()` method to return the formatted information of the composite product.
     * </p>
     *
     * @return A string representing the composite's name, category, and price.
     */
    @Override
    public String displayInfo() {
        double priceToShow = getDiscountedPrice(); 
        return String.format("%s (%s): $%.2f", getName(), getProductCategory(), priceToShow);
    }

    /**
     * Placeholder method for adding an observer to the composite.
     * <p>
     * This method is currently unimplemented and can be used to implement observer functionality in the future.
     * </p>
     *
     * @param currentUser The user to be added as an observer.
     */
    @Override
    public void addObserver(User currentUser) {
        // Placeholder for observer functionality
    }

    /**
     * Placeholder method for notifying observers about a change in the composite's state.
     * <p>
     * This method is currently unimplemented and can be used to implement observer functionality in the future.
     * </p>
     *
     * @param message The message to be sent to the observers.
     */
    @Override
    protected void notifyObservers(String message) {
        // Placeholder for observer functionality
    }
}
