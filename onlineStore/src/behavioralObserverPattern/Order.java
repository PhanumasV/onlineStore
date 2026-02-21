package behavioralObserverPattern;

import java.util.ArrayList;
import java.util.List;
import models.Product;
import services.OrderInterface;

/**
 * Represents an order in the system. An order includes a unique order ID, a list of products, 
 * quantities for each product, and the total amount for the order.
 * This class implements the {@link OrderInterface}.
 * <p>
 * The order details are provided with the product name, quantity, price, and total amount.
 * </p>
 */
public class Order implements OrderInterface {
    private String orderId; // Unique order ID
    private List<Product> products; // Products in the order
    private List<Integer> quantities; // Quantities for each product
    private double totalAmount; // Total cost of the order

    /**
     * Constructs an order with the specified order ID, products, quantities, and total amount.
     * <p>
     * This constructor checks that the products and quantities lists are not null and that their 
     * sizes match. If not, it throws an {@link IllegalArgumentException}.
     * </p>
     *
     * @param orderId The unique ID for the order.
     * @param products The list of products in the order.
     * @param quantities The list of quantities for each product.
     * @param totalAmount The total amount for the order.
     * @throws IllegalArgumentException If the products or quantities list is null or their sizes don't match.
     */
    public Order(String orderId, List<Product> products, List<Integer> quantities, double totalAmount) {
        if (products == null || quantities == null || products.size() != quantities.size()) {
            throw new IllegalArgumentException("Products and quantities must match and cannot be null.");
        }
        this.orderId = orderId;
        this.products = new ArrayList<>(products); // Ensure the list is copied
        this.quantities = new ArrayList<>(quantities); // Ensure the list is copied
        this.totalAmount = totalAmount;
    }

    /**
     * Views the details of the order, displaying the products and their quantities.
     * The total amount for the order is also displayed.
     */
    public void viewOrderDetails() {
        System.out.println("Order Details:");
        for (Product product : products) {
            System.out.println("- " + product.displayInfo());
        }
        System.out.printf("Total Amount: $%.2f%n", totalAmount);
    }

    /**
     * Displays the details of the order including the products, their quantities, 
     * and the total amount of the order.
     * <p>
     * If the order has no products, it will display a message indicating no items in the order.
     * </p>
     */
    public void displayOrderDetails() {
        System.out.println("Order Details:");
        if (products.isEmpty()) {
            System.out.println("- No items in this order.");
        } else {
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                int quantity = quantities.isEmpty() ? 1 : quantities.get(i); // Default quantity to 1 if quantities are not provided
                System.out.printf("- %d x %s (%s): $%.2f each%n", 
                    quantity, product.getName(), product.getProductCategory(), product.getDiscountedPrice());
            }
        }
        System.out.printf("Total Amount: $%.2f%n", totalAmount);
    }
}
