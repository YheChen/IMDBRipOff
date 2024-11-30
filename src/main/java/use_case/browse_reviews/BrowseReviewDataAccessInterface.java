package use_case.browse_reviews;

import entity.Review;

import java.util.Collection;

public interface BrowseReviewDataAccessInterface {
    /**
     * Checks if the given review exists.
     * @param id the id of the review to look for.
     * @return true if a review with the given id exists, false otherwise.
     */
    boolean existsByID(String id);

    /**
     * Get review by id
     * @param id
     */
    Review get(String id);

    /**
     * Get all reviews
     */
    Collection<Review> getAll();

    /**
     * Get all reviews, sorted by a parameter
     */
    Collection<Review> getAllSorted(String orderBy, String searchText);

    /**
     * Gets the total count of reviews
     */
    long count();
}