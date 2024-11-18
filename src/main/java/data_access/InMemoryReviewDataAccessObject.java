package data_access;
import entity.Review;
import entity.User;
import use_case.write_review.WriteReviewDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryReviewDataAccessObject implements WriteReviewDataAccessInterface {

    private final Map<String, Review> reviews = new HashMap<>();
    /**
     * Checks if the given review exists.
     *
     * @param id the id of the review to look for.
     * @return true if a review with the given id exists, false otherwise.
     */
    @Override
    public boolean existsByID(String id) {
        return reviews.containsKey(id);
    }

    /**
     * Saves the review.
     *
     * @param review the review to save (or update an existing review)
     */
    @Override
    public void save(Review review) {
        reviews.put(review.getReviewID(), review);
    }


    /**
     * Deletes the review with the specified ID.
     *
     * @param id the id of the review to delete
     */
    public void delete(String id) {
        reviews.remove(id);
    }


}
