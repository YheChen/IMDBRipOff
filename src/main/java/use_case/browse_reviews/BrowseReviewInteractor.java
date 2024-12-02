package use_case.browse_reviews;

import entity.Review;

import java.util.Collection;

/**
 * The Login Interactor.
 */
public class BrowseReviewInteractor implements BrowseReviewInputBoundary {
    private final BrowseReviewOutputBoundary browseReviewPresenter;
    private final BrowseReviewDataAccessInterface reviewDao;

    public BrowseReviewInteractor(BrowseReviewOutputBoundary browseReviewPresenter,
                                  BrowseReviewDataAccessInterface reviewDao) {
        this.browseReviewPresenter = browseReviewPresenter;
        this.reviewDao = reviewDao;
    }

    @Override
    public void execute(BrowseReviewInputData browseReviewInputData) {
        final String orderBy = browseReviewInputData.getOrderBy();
        final String searchText = browseReviewInputData.getSearchText();

        // Fetch reviews
        final Collection<Review> reviews = reviewDao.getAllSorted(orderBy, searchText);

        final BrowseReviewOutputData outputData = new BrowseReviewOutputData(orderBy, searchText, reviews);
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
