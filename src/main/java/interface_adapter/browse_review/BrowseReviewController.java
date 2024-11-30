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

    public void switchToBrowseView() {
        browseReviewUseCaseInteractor.switchToBrowseView();
    }

    public void execute(String orderBy, String searchText) {
        final BrowseReviewInputData browseReviewInputData = new BrowseReviewInputData(orderBy, searchText);

        browseReviewUseCaseInteractor.execute(browseReviewInputData);
    }

//    public void execute(String username) {
//        final BrowseReviewInputData browseReviewInputData = new BrowseReviewInputData(username);
//
//        browseReviewUseCaseInteractor.execute(browseReviewInputData);
//    }
}