package data_access;
import entity.Review;
import entity.User;
import use_case.browse_reviews.BrowseReviewDataAccessInterface;
import use_case.write_review.WriteReviewDataAccessInterface;

import java.util.Date;
import java.util.HashMap;
import java.util.Collection;
import java.util.Map;

public class InMemoryReviewDataAccessObject implements BrowseReviewDataAccessInterface, WriteReviewDataAccessInterface {

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

    @Override
    public Review get(String id) {
        return reviews.get(id);
    }

    @Override
    public Collection<Review> getAll() {
        return reviews.values();
    }

    @Override
    public Collection<Review> getAllSorted(String orderBy) {
        return reviews.values();
    }

    @Override
    public long count() {
        return reviews.size();
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

    /**
     * Populates testing data for the DataAccessObject
     */
    public void seedData() {
        Date currentDate = new Date();
        reviews.put("671", new Review("User1", "671", "This is the body of the review for 'Harry Potter and the Philosophers Stone'", 3, currentDate));
        reviews.put("672", new Review("User2", "672", "This is the body of the review for 'Harry Potter and the Chamber of Secrets'", 4, currentDate));
        reviews.put("120", new Review("User3", "120", "This is the body of the review for 'The Lord of the Rings: The Fellowship of the Ring'", 5, currentDate));
        reviews.put("121", new Review("User4", "121", "This is the body of the review for 'The Lord of the Rings: The Two Towers'", 2, currentDate));
    }
}
