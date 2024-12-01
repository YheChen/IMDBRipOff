package use_case.browse_reviews;

/**
 * The Login Interactor.
 */
public class BrowseReviewInteractor implements BrowseReviewInputBoundary {
    private final BrowseReviewOutputBoundary browseReviewPresenter;

    public BrowseReviewInteractor(BrowseReviewOutputBoundary browseReviewPresenter) {
        this.browseReviewPresenter = browseReviewPresenter;
    }

    @Override
    public void execute(BrowseReviewInputData browseReviewInputData) {
        final String orderBy = browseReviewInputData.getOrderBy();
        final String searchText = browseReviewInputData.getSearchText();
        final BrowseReviewOutputData outputData = new BrowseReviewOutputData(orderBy, searchText);
        browseReviewPresenter.prepareBrowseView(outputData);
    }

    /**
     * Switches to write view.
     */
    public void switchToWriteView() {
        browseReviewPresenter.switchToWriteView();
    }

    /**
     * Switches to account view.
     */
    public void switchToAccountView() {
        browseReviewPresenter.switchToAccountView();
    }

    /**
     * Switches to browse view.
     */
    public void switchToBrowseView() {
        browseReviewPresenter.switchToBrowseView();
    }

}
