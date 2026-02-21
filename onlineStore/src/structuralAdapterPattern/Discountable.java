package structuralAdapterPattern;

/**
 * The {@code Discountable} interface defines the contract for objects that support discounts.
 * <p>
 * Classes implementing this interface can apply a discount to a product or service and retrieve the 
 * price after the discount is applied.
 * </p>
 */
public interface Discountable {

    /**
     * Applies a discount to the object.
     * <p>
     * The discount is specified as a percentage. The method ensures that the percentage is applied to the
     * price of the object, and the price will reflect the discount once it is calculated.
     * </p>
     *
     * @param percentage The discount percentage to be applied (e.g., 10 for 10% off).
     * @throws IllegalArgumentException If the percentage is less than 0 or greater than 100.
     */
    void applyDiscount(double percentage);

    /**
     * Retrieves the price of the object after the discount has been applied.
     * <p>
     * This method calculates the price after applying the discount. It takes into account the original price
     * and the discount percentage applied.
     * </p>
     *
     * @return The price of the object after the discount is applied.
     */
    double getDiscountedPrice();
}
