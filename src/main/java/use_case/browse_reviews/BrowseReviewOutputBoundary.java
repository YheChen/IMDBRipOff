package use_case.browse_reviews;

public interface BrowseReviewOutputBoundary {
    void prepareBrowseView(BrowseReviewOutputData response);

    //void prepareWriteReviewView();

    void switchToWriteView();

    void switchToAccountView();

    void switchToBrowseView();
}
