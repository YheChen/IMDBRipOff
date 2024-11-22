package use_case.browse_reviews;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface BrowseReviewInputBoundary {

    /**
     * Executes the write review use case.
     */
//    void execute(BrowseReviewInputData writeReviewInputData);

    void switchToWriteView();

    void switchToAccountView();
}
