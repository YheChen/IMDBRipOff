package use_case.browse_reviews;

/**
 * The output data for the write review use case.
 */
public class BrowseReviewOutputData {
    private final String orderBy;
    private final String searchText;

    public BrowseReviewOutputData(String orderBy, String searchText) {
        this.orderBy = orderBy;
        this.searchText = searchText;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getSearchText() {
        return searchText;
    }
}
