package use_case.write_review;

import entity.Review;

/**
 * Data access interface for write review use case.
 */
public interface WriteReviewDataAccessInterface {
    /**
     * Saves the review.
     * @param review the review to save
     */
    void save(Review review);
}
