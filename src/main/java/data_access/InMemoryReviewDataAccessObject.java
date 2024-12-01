package data_access;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Review;
import use_case.browse_reviews.BrowseReviewDataAccessInterface;
import use_case.write_review.WriteReviewDataAccessInterface;

/**
 * DAO for Reviews in memory.
 */
public class InMemoryReviewDataAccessObject implements BrowseReviewDataAccessInterface, WriteReviewDataAccessInterface {

    private final Map<String, Review> reviewsMap = new HashMap<>();

    /**
     * Checks if the given review exists.
     *
     * @param id the id of the review to look for.
     * @return true if a review with the given id exists, false otherwise.
     */
    @Override
    public boolean existsByID(String id) {
        return reviewsMap.containsKey(id);
    }

    @Override
    public Review get(String id) {
        return reviewsMap.get(id);
    }

    @Override
    public Collection<Review> getAll() {
        return reviewsMap.values();
    }

    @Override
    public Collection<Review> getAllSorted(String orderBy, String searchText) {
        final List<Review> reviews = new ArrayList<>(getAll());
        if (searchText != null && !searchText.isEmpty()) {
            reviews.removeIf(review -> {
                final boolean included = review.getContent().toLowerCase().contains(searchText.toLowerCase());
                return !included;
            });
        }
        if (orderBy != null) {
            reviews.sort((review1, review2) -> sortReviews(orderBy, review1, review2));
            if ("recent".equals(orderBy) || "highScore".equals(orderBy)) {
                Collections.reverse(reviews);
            }
        }
        return reviews;
    }

    private int sortReviews(String orderBy, Review review1, Review review2) {
        final int compare;
        switch (orderBy) {
            case "recent":
                compare = review1.getDateUpdated().compareTo(review2.getDateUpdated());
                break;
            case "highScore":
            case "lowScore":
                compare = Integer.compare(review1.getRating(), review2.getRating());
                break;
            default:
                compare = review1.getReviewID().compareTo(review2.getReviewID());
                break;
        }
        return compare;
    }

    @Override
    public long count() {
        return reviewsMap.size();
    }

    /**
     * Saves the review.
     *
     * @param review the review to save (or update an existing review)
     */
    @Override
    public void save(Review review) {
        reviewsMap.put(review.getReviewID(), review);
    }

    /**
     * Deletes the review with the specified ID.
     *
     * @param id the id of the review to delete
     */
    public void delete(String id) {
        reviewsMap.remove(id);
    }

    /**
     * Populates testing data for the DataAccessObject.
     */
    public void seedData() {
        final Date currentDate = new Date();
        final int psRating = 3;
        reviewsMap.put("671", new Review("User1", "671",
                "This is the body of the review for 'Harry Potter and the Philosophers Stone'",
                psRating, currentDate));
        final int csRating = 4;
        reviewsMap.put("672", new Review("User2", "672",
                "This is the body of the review for 'Harry Potter and the Chamber of Secrets'",
                csRating, currentDate));
        final int fotrRating = 5;
        reviewsMap.put("120", new Review("User3", "120",
                "This is the body of the review for 'The Lord of the Rings: The Fellowship of the Ring'",
                fotrRating, currentDate));
        final int tttRating = 2;
        reviewsMap.put("121", new Review("User4", "121",
                "This is the body of the review for 'The Lord of the Rings: The Two Towers'",
                tttRating, currentDate));
    }
}
