package services;

import java.util.ArrayList;
import java.util.List;
import models.Product;

/**
 * The {@code ShoppingCart} class represents a shopping cart that contains products and their quantities.
 * <p>
 * This class allows adding, removing, displaying, and calculating the total cost of products in the cart. 
 * It keeps track of the products and their quantities and can compute the total price of all items, 
 * including their discounted prices.
 * </p>
 */
public class ShoppingCart {
    private List<Product> products; // List of products in the cart
    private List<Integer> quantities; // List of quantities for each product

    /**
     * Initializes a new shopping cart with no products.
     * <p>
     * The constructor sets up empty lists for products and quantities.
     * </p>
     */
    public ShoppingCart() {
        products = new ArrayList<>();
        quantities = new ArrayList<>();
    }

    /**
     * Adds a product to the cart with the specified quantity.
     * <p>
     * If the product is already in the cart, its quantity is updated; otherwise, the product is added to the cart.
     * </p>
     *
     * @param product The product to be added.
     * @param quantity The quantity of the product to add.
     * @throws IllegalArgumentException If the product is {@code null} or the quantity is less than or equal to 0.
     */
    public void addProduct(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Invalid product or quantity.");
        }

        int index = products.indexOf(product);
        if (index >= 0) {
            // Update quantity if the product is already in the cart
            quantities.set(index, quantities.get(index) + quantity);
        } else {
            // Add product if it is not in the cart
            products.add(product);
            quantities.add(quantity);
        }
        System.out.printf("%d of %s added to cart.%n", quantity, product.getName());
    }

    /**
     * Removes a product from the cart.
     * <p>
     * If the product is in the cart, it is removed along with its quantity. 
     * If the product is not in the cart, a message is displayed.
     * </p>
     *
     * @param product The product to be removed from the cart.
     */
    public void removeProduct(Product product) {
        if (product == null) {
            System.out.println("Invalid product.");
            return;
        }

        int index = products.indexOf(product);
        if (index >= 0) {
            products.remove(index);
            quantities.remove(index);
            System.out.println(product.getName() + " removed from cart.");
        } else {
            System.out.println(product.getName() + " is not in the cart.");
        }
    }

    /**
     * Retrieves the list of products currently in the cart.
     * 
     * @return A list of {@code Product} objects in the cart.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Displays the items in the cart, including the quantity, name, category, and price per unit.
     * <p>
     * If the cart is empty, a message indicating that the cart is empty is displayed.
     * </p>
     */
    public void showCartItems() {
        if (products.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("Items in your cart:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            int quantity = quantities.get(i);
            double pricePerUnit = product.getDiscountedPrice(); // Use discounted price
            System.out.printf("%d x %s (%s): $%.2f each%n", quantity, product.getName(),
                    product.getProductCategory(), pricePerUnit);
        }
    }

    /**
     * Calculates the total price of all items in the cart, considering the discounted prices.
     *
     * @return The total cost of all items in the cart, including discounts.
     */
    public double calculateTotal() {
        double total = 0;
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            int quantity = quantities.get(i);
            total += product.getDiscountedPrice() * quantity; // Use discounted price
        }
        return total;
    }

    /**
     * Clears all items in the cart.
     * <p>
     * This method removes all products and their quantities from the cart.
     * </p>
     */
    public void clear() {
        products.clear(); // Remove all products
        quantities.clear(); // Remove all quantities
    }

    /**
     * Retrieves a copy of the list of quantities for the products in the cart.
     * <p>
     * This method returns a copy of the quantities list to prevent external modification.
     * </p>
     *
     * @return A list of quantities for each product in the cart.
     */
    public List<Integer> getQuantities() {
        return new ArrayList<>(quantities); // Return a copy to avoid external modification
    }
}
