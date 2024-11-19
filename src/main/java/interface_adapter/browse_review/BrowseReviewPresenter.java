package interface_adapter.browse_review;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountViewModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.write_review.WriteReviewViewModel;
import use_case.browse_reviews.BrowseReviewOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.write_review.WriteReviewOutputData;
import use_case.write_review.WriteReviewOutputBoundary;

public class BrowseReviewPresenter implements BrowseReviewOutputBoundary {
    private final BrowseReviewViewModel browseReviewViewModel;
    private final WriteReviewViewModel writeReviewViewModel;
    private final AccountViewModel accountViewModel;
    private final ViewManagerModel viewManagerModel;

    public BrowseReviewPresenter(BrowseReviewViewModel browseReviewViewModel,
                                 WriteReviewViewModel writeReviewViewModel,
                                AccountViewModel accountViewModel,
                                ViewManagerModel viewManagerModel) {
        this.browseReviewViewModel =  browseReviewViewModel;
        this.writeReviewViewModel = writeReviewViewModel;
        this.accountViewModel = accountViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        // On success, switch to the logged in view.

        final BrowseReviewState browseReviewState = browseReviewViewModel.getState();
        this.browseReviewViewModel.setState(browseReviewState);
        this.writeReviewViewModel.firePropertyChanged();

        this.viewManagerModel.setState(writeReviewViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void switchToWriteView() {
        viewManagerModel.setState(writeReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToAccountView() {
        viewManagerModel.setState(accountViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
