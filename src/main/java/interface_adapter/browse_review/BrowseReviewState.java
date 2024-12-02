package interface_adapter.browse_review;

import entity.Review;

import java.util.ArrayList;
import java.util.Collection;

/**
 * State for Browse view.
 */
public class BrowseReviewState {
//    private final String username = "";
//    private final String userID = "";
//    private final int rating = 0;
//    private final String content = "";
//    private final String mediaID = "";
    private String orderBy = "recent";
    private String searchText;
    private Collection<Review> reviews = new ArrayList<>();

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public Collection<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<Review> reviews) {
        this.reviews = reviews;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public String getUserID() {
//        return userID;
//    }
//
//    public int getRating() {
//        return rating;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public String getMediaID() {
//        return mediaID;
//    }
}
