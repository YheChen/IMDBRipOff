package use_case.browse_reviews;

public interface BrowseReviewOutputBoundary {
    /**
     * Prepares the success view for the Write Review Use Case
     */
    void prepareAccountView();

    //void prepareWriteReviewView();

    void switchToWriteView();

    void switchToAccountView();

    void switchToBrowseView();
}
