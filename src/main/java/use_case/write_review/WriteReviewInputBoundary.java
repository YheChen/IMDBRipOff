package use_case.write_review;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface WriteReviewInputBoundary {

    /**
     * Executes the write review use case.
     * @param writeReviewInputData the input data
     */
    void execute(WriteReviewInputData writeReviewInputData);

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
