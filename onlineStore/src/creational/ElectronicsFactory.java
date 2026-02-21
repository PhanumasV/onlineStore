package creational;

import models.Electronics;
import models.Product;

/**
 * The {@code ElectronicsFactory} class implements the {@link ProductFactory} interface.
 * This factory class is responsible for creating {@link Electronics} products.
 * <p>
 * It provides the implementation for creating a new electronic product with a specified name and price.
 * </p>
 */
public class ElectronicsFactory implements ProductFactory {

    /**
     * Creates and returns a new {@link Electronics} product with the specified name and price.
     *
     * @param name The name of the electronic product.
     * @param price The price of the electronic product.
     * @return A new {@link Electronics} object with the specified name and price.
     */
    @Override
    public Product createProduct(String name, double price) {
        return new Electronics(name, price);
    }
}
