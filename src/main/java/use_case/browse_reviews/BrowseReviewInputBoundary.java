package use_case.browse_reviews;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface BrowseReviewInputBoundary {

    /**
     * Executes the browse review use case
     */
    void execute(BrowseReviewInputData browseReviewInputData);

    void switchToWriteView();

    void switchToAccountView();

    void switchToBrowseView();
}
