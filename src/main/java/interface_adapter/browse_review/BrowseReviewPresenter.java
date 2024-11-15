package interface_adapter.browse_review;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.browse_reviews.BrowseReviewOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.write_review.WriteReviewOutputData;
import use_case.write_review.WriteReviewOutputBoundary;

public class BrowseReviewPresenter implements BrowseReviewOutputBoundary {
    private final BrowseReviewViewModel browseReviewViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public BrowseReviewPresenter(BrowseReviewViewModel browseReviewViewModel,
                                LoggedInViewModel loggedInViewModel,
                                ViewManagerModel viewManagerModel) {
        this.browseReviewViewModel =  browseReviewViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        // On success, switch to the logged in view.

        final BrowseReviewState browseReviewState = browseReviewViewModel.getState();
        this.browseReviewViewModel.setState(browseReviewState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setState(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
