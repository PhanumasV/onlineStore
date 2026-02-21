package creational;

import java.util.ArrayList;
import java.util.List;
import models.Product;

/**
 * The {@code SingletonStore} class represents a store that uses the Singleton design pattern
 * to ensure only one instance of the store exists throughout the application.
 * <p>
 * This class provides methods to add, retrieve, display, and update products in the store.
 * </p>
 */
public class SingletonStore {
    // Singleton instance for the store
    private static SingletonStore instance;

    // List to store all products
    private List<Product> products;

    /**
     * Private constructor to enforce the Singleton pattern.
     * <p>
     * Initializes the product list to an empty {@code ArrayList}.
     * </p>
     */
    private SingletonStore() {
        products = new ArrayList<>();
    }

    /**
     * Retrieves the single instance of the {@code SingletonStore}.
     * <p>
     * If the instance does not exist, it is created.
     * </p>
     *
     * @return The single instance of the {@code SingletonStore}.
     */
    public static SingletonStore getInstance() {
        if (instance == null) {
            instance = new SingletonStore();
        }
        return instance;
    }

    /**
     * Adds a single product to the store.
     *
     * @param product The product to be added.
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Adds multiple products to the store.
     *
     * @param newProducts A list of products to be added.
     */
    public void addProducts(List<Product> newProducts) {
        products.addAll(newProducts);
    }

    /**
     * Retrieves the list of all products in the store.
     *
     * @return A list of all {@code Product} objects in the store.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Displays all products in the store.
     * <p>
     * If no products are available, a message is displayed indicating that the store is empty.
     * Otherwise, it prints the details of each product.
     * </p>
     */
    public void displayProducts() {
        List<Product> products = SingletonStore.getInstance().getProducts();
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Available products:");
            for (Product product : products) {
                System.out.println(product.displayInfo());
            }
        }
    }

    /**
     * Updates an existing product in the store or adds it if it doesn't exist.
     * <p>
     * This method checks if a product with the same name already exists in the store.
     * If it exists, the product is updated. Otherwise, the new product is added to the store.
     * </p>
     *
     * @param updatedProduct The updated product to be added or updated in the store.
     */
    public void updateProduct(Product updatedProduct) {
        if (updatedProduct == null) {
            System.out.println("Invalid product. Update operation skipped.");
            return;
        }

        boolean productUpdated = false;

        for (int i = 0; i < products.size(); i++) {
            Product currentProduct = products.get(i);

            if (currentProduct.getName().equalsIgnoreCase(updatedProduct.getName())) {
                products.set(i, updatedProduct);
                productUpdated = true;
                System.out.println("Product updated successfully: " + updatedProduct.getName());
                break;
            }
        }

        if (!productUpdated) {
            products.add(updatedProduct);
            System.out.println("Product added successfully: " + updatedProduct.getName());
        }
    }
}
