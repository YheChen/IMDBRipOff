package use_case.write_review;

/**
 * Output boundary for write review use case.
 */
public interface WriteReviewOutputBoundary {
    /**
     * Prepares the success view for the Write Review Use Case.
     * @param writeReviewOutputData the output data
     */
    void prepareSuccessView(WriteReviewOutputData writeReviewOutputData);

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
