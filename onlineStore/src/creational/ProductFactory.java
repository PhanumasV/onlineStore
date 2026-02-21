package creational;

import models.Product;

/**
 * The {@code ProductFactory} interface defines a factory method for creating products.
 * <p>
 * Implementations of this interface are responsible for creating specific types of {@link Product}
 * objects with a given name and price.
 * </p>
 */
public interface ProductFactory {

    /**
     * Creates and returns a new {@link Product} with the specified name and price.
     * <p>
     * The type of product created depends on the implementation of this method in a concrete factory class.
     * </p>
     *
     * @param name The name of the product to be created.
     * @param price The price of the product to be created.
     * @return A new {@link Product} object with the specified name and price.
     */
    Product createProduct(String name, double price);
}
