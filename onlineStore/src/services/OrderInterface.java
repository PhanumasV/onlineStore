package services;

/**
 * The {@code OrderInterface} interface defines the contract for viewing order details.
 * <p>
 * This interface is intended to be implemented by classes that represent orders, providing
 * a method to view the details of a specific order.
 * </p>
 */
public interface OrderInterface {

    /**
     * Displays the details of an order.
     * <p>
     * This method is expected to show all relevant information about the order, 
     * such as the products in the order, quantities, pricing, and any other details 
     * specific to the order.
     * </p>
     */
    void viewOrderDetails();
}
