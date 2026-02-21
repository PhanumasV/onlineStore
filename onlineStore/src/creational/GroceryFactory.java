package creational;

import models.Grocery;
import models.Product;

/**
 * The {@code GroceryFactory} class implements the {@link ProductFactory} interface.
 * This factory class is responsible for creating {@link Grocery} products.
 * <p>
 * It provides the implementation for creating a new grocery product with a specified name and price.
 * </p>
 */
public class GroceryFactory implements ProductFactory {

    /**
     * Creates and returns a new {@link Grocery} product with the specified name and price.
     *
     * @param name The name of the grocery product.
     * @param price The price of the grocery product.
     * @return A new {@link Grocery} object with the specified name and price.
     */
    @Override
    public Product createProduct(String name, double price) {
        return new Grocery(name, price);
    }
}
