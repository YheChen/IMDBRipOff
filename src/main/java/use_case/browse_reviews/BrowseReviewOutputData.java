package use_case.browse_reviews;

/**
 * The output data for the write review use case.
 */
public class BrowseReviewOutputData {
//    private final String username;
//    private final String userID;
//    private final int rating;
//    private final String content;
//    private final String mediaID;
//
    private final String orderBy;
    private final String searchText;

    public BrowseReviewOutputData(String orderBy, String searchText){
//        this.username = username;
//        this.userID = userID;
//        this.rating = rating;
//        this.content = content;
//        this.mediaID = mediaID;
        this.orderBy = orderBy;
        this.searchText = searchText;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getSearchText() {
        return searchText;
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
