package use_case.browse_reviews;

import entity.Review;

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
     * Gets the total count of reviews
     */
    int count();
}
