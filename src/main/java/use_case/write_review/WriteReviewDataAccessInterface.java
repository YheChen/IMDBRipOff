package use_case.write_review;
import entity.Review;

public interface WriteReviewDataAccessInterface {

    /**
     * Checks if the given review exists.
     * @param id the id of the review to look for.
     * @return true if a review with the given id exists, false otherwise.
     */
    boolean existsByID(String id);

    /**
     * Saves the review.
     * @param review the review to save
     */
    void save(Review review);

    /**
     * Get review by id
     * @param id
     */
    Review get(String id);
}
