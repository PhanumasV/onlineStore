package models;

/**
 * The {@code ProductReview} class represents a review for a product.
 * <p>
 * This class contains information about the reviewer, the review text, and the rating provided.
 * It is used to store and display reviews given by customers for a specific product.
 * </p>
 */
public class ProductReview {
    private String reviewerName;
    private String reviewText;
    private int rating;

    /**
     * Initializes a new product review with the specified reviewer name, review text, and rating.
     *
     * @param reviewerName The name of the reviewer.
     * @param reviewText The text of the review.
     * @param rating The rating given by the reviewer (typically between 1 and 5).
     */
    public ProductReview(String reviewerName, String reviewText, int rating) {
        this.reviewerName = reviewerName;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    /**
     * Returns a string representation of the product review.
     * <p>
     * This method provides a formatted string including the reviewer's name, rating, and the review text.
     * </p>
     *
     * @return A string that includes the reviewer's name, rating, and review text.
     */
    @Override
    public String toString() {
        return String.format("Reviewer: %s, Rating: %d, Review: %s", reviewerName, rating, reviewText);
    }

    /**
     * Retrieves the review text for this review.
     * <p>
     * If the review text is {@code null}, this method returns a default message indicating no review was provided.
     * </p>
     *
     * @return The review text, or a default message if no review text is provided.
     */
    public String getReviewText() {
        // Return null if reviewText is null, otherwise return the review text itself.
        // Avoid adding only null to prevent NullPointerException errors
        return reviewText != null ? reviewText : "No review provided."; 
    }

    /**
     * Retrieves the rating for this review.
     *
     * @return The rating provided by the reviewer.
     */
    public int getRating() {
        return rating;
    }
}
