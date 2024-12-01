package use_case.browse_reviews;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface BrowseReviewInputBoundary {
    /**
     * Executes the browse review use case.
     * @param browseReviewInputData the input data
     */
    void execute(BrowseReviewInputData browseReviewInputData);

    /**
     * Switches to write view.
     */
    void switchToWriteView();

    /**
     * Switches to account view.
     */
    void switchToAccountView();

    /**
     * Switches to browse view.
     */
    void switchToBrowseView();
}
