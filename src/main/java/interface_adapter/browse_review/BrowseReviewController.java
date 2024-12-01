package interface_adapter.browse_review;

import use_case.browse_reviews.BrowseReviewInputBoundary;
import use_case.browse_reviews.BrowseReviewInputData;

/**
 * The controller for the Login Use Case.
 */
public class BrowseReviewController {

    private final BrowseReviewInputBoundary browseReviewUseCaseInteractor;

    public BrowseReviewController(BrowseReviewInputBoundary browseReviewInteractor) {
        this.browseReviewUseCaseInteractor = browseReviewInteractor;
    }

    /**
     * Switches to write view.
     */
    public void switchToWriteView() {
        browseReviewUseCaseInteractor.switchToWriteView();
    }

    /**
     * Switches to account view.
     */
    public void switchToAccountView() {
        browseReviewUseCaseInteractor.switchToAccountView();
    }

    /**
     * Switches to browse view.
     */
    public void switchToBrowseView() {
        browseReviewUseCaseInteractor.switchToBrowseView();
    }

    /**
     * Updates the state of the BrowseView.
     * @param orderBy a parameter to order the results by
     * @param searchText a substring to search for results with
     */
    public void execute(String orderBy, String searchText) {
        final BrowseReviewInputData browseReviewInputData = new BrowseReviewInputData(orderBy, searchText);

        browseReviewUseCaseInteractor.execute(browseReviewInputData);
    }
}
