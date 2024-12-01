package use_case.browse_reviews;

/**
 * The Input Data for the Write Review Case.
 */
public class BrowseReviewInputData {
    private final String orderBy;
    private final String searchText;

    public BrowseReviewInputData(String orderBy, String searchText) {
        this.orderBy = orderBy;
        this.searchText = searchText;
    }

    String getOrderBy() {
        return orderBy;
    }

    String getSearchText() {
        return searchText;
    }
}
