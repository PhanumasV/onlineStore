package services;

/**
 * The {@code Payment} class represents a payment process using different payment methods.
 * <p>
 * This class handles processing payments based on the selected payment method, such as credit card,
 * PayPal and debit card. It prints the payment details and processes the payment accordingly.
 * </p>
 */
public class Payment {
    // The payment method used
    private String paymentMethod;

    /**
     * Initializes a new payment with the specified payment method.
     * <p>
     * This constructor sets the payment method for the transaction, which can be credit card, PayPal and 
     * debit card.
     * </p>
     *
     * @param paymentMethod The method used to make the payment (e.g., "credit card", "paypal" and "debit card").
     */
    public Payment(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Processes the payment based on the selected payment method.
     * <p>
     * This method prints out the payment details and processes the payment accordingly. It supports
     * multiple payment methods like credit card, PayPal and debit card.
     * </p>
     *
     * @param amount The amount to be paid.
     * @return {@code true} if the payment was processed successfully, {@code false} if the payment method is invalid.
     */
    public boolean processPayment(double amount) {
        if (paymentMethod.equalsIgnoreCase("credit card")) {
            System.out.printf("Processing credit card payment of $%.2f...%n", amount);
        } else if (paymentMethod.equalsIgnoreCase("paypal")) {
            System.out.printf("Processing PayPal payment of $%.2f...%n", amount);
        } else if (paymentMethod.equalsIgnoreCase("debit card")) {
            System.out.printf("Processing Debit card payment of $%.2f...%n", amount);
        } else {
            System.out.println("Invalid payment method selected.");
            return false; // Return false if an invalid method is selected
        }
        System.out.println("Payment successful!");
        return true; // Return true when payment is successful
    }

    /**
     * Retrieves the payment method used for the transaction.
     *
     * @return The payment method (e.g., "credit card", "paypal", "debit card",).
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }
}
