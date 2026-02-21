package behavioralStrategyPattern;

import java.util.List;
import models.Product;

/**
 * The {@code SearchStrategy} interface defines a contract for implementing different strategies
 * to search through a list of products based on a specific query.
 * <p>
 * Implementations of this interface provide concrete search logic, such as searching by product name
 * or category.
 * </p>
 */
public interface SearchStrategy {

    /**
     * Searches a list of products based on a specific query.
     * <p>
     * The search logic is defined by the implementing class, allowing for different search strategies
     * such as by name or category.
     * </p>
     *
     * @param products The list of products to search through.
     * @param query The search query used to filter the products.
     * @return A list of products that match the search criteria.
     */
    List<Product> search(List<Product> products, String query);
}
