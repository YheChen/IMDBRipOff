package use_case.browse_reviews;

/**
 * The Input Data for the Write Review Case.
 */
public class BrowseReviewInputData {
    private final String username;

    public BrowseReviewInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }
}
