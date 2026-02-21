package behavioralObserverPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code OrderHistory} class is responsible for managing a collection of past orders.
 * It allows adding new orders to the history and displaying the details of all past orders.
 * <p>
 * This class provides methods to add an order and to display the history of all orders,
 * including their details.
 * </p>
 */
public class OrderHistory {
    private List<Order> orders = new ArrayList<>(); // List of past orders

    /**
     * Adds an order to the history.
     * <p>
     * This method adds the specified order to the list of past orders. A message is printed 
     * to indicate that the order has been added.
     * </p>
     *
     * @param order The order to be added to the history.
     */
    public void addOrder(Order order) {
        orders.add(order);
        System.out.println("Order added to history.");
    }

    /**
     * Displays the history of all placed orders.
     * <p>
     * This method checks if any orders are present in the history. If no orders are available,
     * it prints a message indicating that no orders have been placed yet. Otherwise, it displays
     * the details of each order in the history.
     * </p>
     */
    public void displayOrderHistory() {
        if (orders.isEmpty()) {
            System.out.println("No orders have been placed yet.");
            return;
        }

        for (int i = 0; i < orders.size(); i++) {
            System.out.printf("Order %d:%n", i + 1);
            orders.get(i).displayOrderDetails();
            System.out.println(); // Blank line for readability
        }
    }
}
