package creational;

import models.Clothing;
import models.Product;

/**
 * The {@code ClothingFactory} class implements the {@link ProductFactory} interface.
 * This factory class is responsible for creating {@link Clothing} products.
 * <p>
 * It provides the implementation for creating a new clothing item with a specified name and price.
 * </p>
 */
public class ClothingFactory implements ProductFactory {

    /**
     * Creates and returns a new {@link Clothing} product with the specified name and price.
     *
     * @param name The name of the clothing product.
     * @param price The price of the clothing product.
     * @return A new {@link Clothing} object with the specified name and price.
     */
    @Override
    public Product createProduct(String name, double price) {
        return new Clothing(name, price);
    }
}
