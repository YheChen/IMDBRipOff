package interface_adapter.browse_review;

import entity.Review;
import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountViewModel;
import interface_adapter.write_review.WriteReviewViewModel;
import use_case.browse_reviews.BrowseReviewOutputBoundary;
import use_case.browse_reviews.BrowseReviewOutputData;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Presenter for browse view.
 */
public class BrowseReviewPresenter implements BrowseReviewOutputBoundary {
    private final BrowseReviewViewModel browseReviewViewModel;
    private final WriteReviewViewModel writeReviewViewModel;
    private final AccountViewModel accountViewModel;
    private final ViewManagerModel viewManagerModel;

    public BrowseReviewPresenter(BrowseReviewViewModel browseReviewViewModel,
                                 WriteReviewViewModel writeReviewViewModel,
                                 AccountViewModel accountViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.browseReviewViewModel = browseReviewViewModel;
        this.writeReviewViewModel = writeReviewViewModel;
        this.accountViewModel = accountViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareBrowseView(BrowseReviewOutputData response) {
        // On success, switch to the account view.
        System.out.println("prepareBrowseView");
        final BrowseReviewState browseReviewState = browseReviewViewModel.getState();
        final String orderBy = response.getOrderBy();
        if (orderBy != null) {
            browseReviewState.setOrderBy(orderBy);
        }
        final String searchText = response.getSearchText();
        if (searchText != null) {
            browseReviewState.setSearchText(searchText);
        }
        final Collection<Review> reviews = response.getReviews();
        if (reviews != null) {
            browseReviewState.setReviews(reviews);
        }
        this.browseReviewViewModel.setState(browseReviewState);
        this.browseReviewViewModel.firePropertyChanged();
    }

    /**
     * Switches to write view.
     */
    public void switchToWriteView() {
        viewManagerModel.setState(writeReviewViewModel.getViewName());
        System.out.println(writeReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches to account view.
     */
    public void switchToAccountView() {
        viewManagerModel.setState(accountViewModel.getViewName());
        System.out.println(accountViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches to browse view.
     */
    public void switchToBrowseView() {
        viewManagerModel.setState(browseReviewViewModel.getViewName());
        System.out.println(browseReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
