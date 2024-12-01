package use_case.browse_reviews;

/**
 * Output boundary for browse use case.
 */
public interface BrowseReviewOutputBoundary {
    /**
     * Prepares browse view.
     * @param response the output data
     */
    void prepareBrowseView(BrowseReviewOutputData response);

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
