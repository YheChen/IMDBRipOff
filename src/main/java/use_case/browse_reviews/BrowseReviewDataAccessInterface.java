package use_case.browse_reviews;

import java.util.Collection;

import entity.Review;

/**
 * Data access interface for Browse view.
 */
public interface BrowseReviewDataAccessInterface {
    /**
     * Checks if the given review exists.
     * @param id the id of the review to look for.
     * @return true if a review with the given id exists, false otherwise.
     */
    boolean existsByID(String id);

    /**
     * Get review by id.
     * @param id a review id
     * @return a review
     */
    Review get(String id);

    /**
     * Get all reviews.
     * @return a collection of reviews
     */
    Collection<Review> getAll();

    /**
     * Get all reviews, sorted by a parameter.
     * @param orderBy a parameter to sort results by
     * @param searchText a substring to search for results with
     * @return a collection of reviews
     */
    Collection<Review> getAllSorted(String orderBy, String searchText);

    /**
     * Gets the total count of reviews.
     * @return a count of reviews
     */
    long count();
}
