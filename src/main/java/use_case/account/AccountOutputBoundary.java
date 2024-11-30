package use_case.account;

public interface AccountOutputBoundary {
    /**
     * Prepares the success view for the Write Review Use Case
     */
//    void prepareAccountView();

    //void prepareWriteReviewView();

    void switchToWriteView();

    void switchToAccountView();

    void switchToBrowseView();
}
