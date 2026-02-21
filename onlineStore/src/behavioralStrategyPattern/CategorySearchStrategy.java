package behavioralStrategyPattern;

import java.util.List;
import java.util.stream.Collectors;
import models.Product;

/**
 * The {@code CategorySearchStrategy} class implements the {@link SearchStrategy} interface.
 * This class provides the search functionality to filter products based on their category.
 * <p>
 * It searches through a list of products and returns a list of products that match the given category.
 * </p>
 */
public class CategorySearchStrategy implements SearchStrategy {
    
    /**
     * Searches for products that belong to the specified category.
     * <p>
     * This method filters the provided list of products, checking if the product's category matches the given query.
     * The category comparison is case-insensitive.
     * </p>
     *
     * @param products The list of products to be searched.
     * @param query The category to search for.
     * @return A list of products that match the specified category.
     * @throws IllegalArgumentException If the {@code products} list or the {@code query} is {@code null}.
     */
    @Override
    public List<Product> search(List<Product> products, String query) {
        // Avoids runtime exceptions
        if (products == null || query == null) {
            throw new IllegalArgumentException("Products list and query cannot be null.");
        }

        // Filters based on category to return a match
        return products.stream()
                       .filter(item -> item.getProductCategory().equalsIgnoreCase(query))
                       .collect(Collectors.toList());
    }
}
