package structuralAdapterPattern;

import models.Product;

/**
 * The {@code DiscountableAdapter} class adapts a {@link Product} to the {@link Discountable} interface.
 * <p>
 * This adapter allows a {@code Product} object to be treated as a {@code Discountable} object, enabling
 * it to apply discounts and retrieve discounted prices using the methods defined in the {@link Discountable} interface.
 * </p>
 */
public class DiscountableAdapter implements Discountable {
    private Product product; // The product being adapted

    /**
     * Initializes the adapter with a specific {@code Product}.
     * <p>
     * This constructor ensures that the provided product is not {@code null}, as attempting to use a
     * {@code null} product would lead to runtime errors.
     * </p>
     *
     * @param product The product to be adapted into a {@code Discountable} object.
     * @throws IllegalArgumentException If the provided {@code product} is {@code null}.
     */
    public DiscountableAdapter(Product product) {
        // Ensure the product is not null to avoid runtime errors
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        this.product = product;
    }

    /**
     * Applies a discount to the adapted {@code Product}.
     * <p>
     * This method delegates the discount application to the {@code applyDiscount} method of the {@code Product}
     * class, enabling the product to handle its own discount logic.
     * </p>
     *
     * @param percentage The discount percentage to be applied to the product (e.g., 10 for a 10% discount).
     * @throws IllegalArgumentException If the discount percentage is less than 0 or greater than 100.
     */
    @Override
    public void applyDiscount(double percentage) {
        product.applyDiscount(percentage); // Leverage the Product's discount logic
    }

    /**
     * Retrieves the discounted price of the adapted {@code Product}.
     * <p>
     * This method delegates the calculation of the discounted price to the {@code getDiscountedPrice}
     * method of the {@code Product} class, returning the final price after the discount is applied.
     * </p>
     *
     * @return The discounted price of the product after the discount has been applied.
     */
    @Override
    public double getDiscountedPrice() {
        return product.getDiscountedPrice(); // Delegate the calculation to the Product's method
    }
}
