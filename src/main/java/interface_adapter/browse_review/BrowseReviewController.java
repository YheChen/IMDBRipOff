package interface_adapter.browse_review;

import use_case.browse_reviews.BrowseReviewInputBoundary;
import use_case.browse_reviews.BrowseReviewInputData;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;


/**
 * The controller for the Login Use Case.
 */
public class BrowseReviewController {

    private final BrowseReviewInputBoundary browseReviewUseCaseInteractor;

    public BrowseReviewController(BrowseReviewInputBoundary browseReviewInteractor) {
        this.browseReviewUseCaseInteractor = browseReviewInteractor;
    }

    public void switchToWriteView() {
        browseReviewUseCaseInteractor.switchToWriteView();
    }

    public void switchToAccountView() {
        browseReviewUseCaseInteractor.switchToAccountView();
    }

    /**
     * Executes the Write Review Use Case.
     * @param username the username of the user logging in
     */
//    public void execute(String username) {
//        final BrowseReviewInputData browseReviewInputData = new BrowseReviewInputData(username);
//
//        browseReviewUseCaseInteractor.execute(browseReviewInputData);
//    }
}