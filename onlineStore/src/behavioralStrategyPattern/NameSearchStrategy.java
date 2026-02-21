package behavioralStrategyPattern;

import java.util.List;
import java.util.stream.Collectors;
import models.Product;

/**
 * The {@code NameSearchStrategy} class implements the {@link SearchStrategy} interface.
 * This class provides functionality to search products based on their name.
 * <p>
 * It searches through a list of products and returns a list of products whose names contain
 * the specified query string. The search is case-insensitive.
 * </p>
 */
public class NameSearchStrategy implements SearchStrategy {

    /**
     * Searches for products whose names contain the specified query.
     * <p>
     * This method filters the provided list of products, checking if the product's name
     * contains the given query string. The comparison is case-insensitive.
     * </p>
     *
     * @param products The list of products to be searched.
     * @param query The query string to search for in the product names.
     * @return A list of products whose names contain the specified query.
     * @throws IllegalArgumentException If the {@code products} list or the {@code query} is {@code null}.
     */
    @Override
    public List<Product> search(List<Product> products, String query) {
        // To validate inputs to avoid runtime exceptions 
        if (products == null || query == null) {
            throw new IllegalArgumentException("Products list and query cannot be null.");
        }

        // Search for the products based on the name
        return products.stream()
                       .filter(item -> item.getName().toLowerCase().contains(query.toLowerCase()))
                       .collect(Collectors.toList());
    }
}

