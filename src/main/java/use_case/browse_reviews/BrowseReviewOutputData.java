package use_case.browse_reviews;

import entity.Review;

import java.util.Collection;

/**
 * The output data for the write review use case.
 */
public class BrowseReviewOutputData {
    private final String orderBy;
    private final String searchText;
    private final Collection<Review> reviews;

    public BrowseReviewOutputData(String orderBy, String searchText, Collection<Review> reviews) {
        this.orderBy = orderBy;
        this.searchText = searchText;
        this.reviews = reviews;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getSearchText() {
        return searchText;
    }

    public Collection<Review> getReviews() {
        return reviews;
    }
}
