package use_case.account;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface AccountInputBoundary {

    /**
     * Executes the write review use case.
     */
//    void execute(BrowseReviewInputData writeReviewInputData);

    void switchToWriteView();

    void switchToAccountView();

    void switchToBrowseView();
}
