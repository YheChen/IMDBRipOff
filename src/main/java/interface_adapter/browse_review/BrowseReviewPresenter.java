package interface_adapter.browse_review;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.write_review.WriteReviewViewModel;
import use_case.browse_reviews.BrowseReviewOutputBoundary;
import use_case.browse_reviews.BrowseReviewOutputData;
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
    public void prepareBrowseView(BrowseReviewOutputData response) {
        // On success, switch to the account view.
        System.out.println("prepareBrowseView");
        final BrowseReviewState browseReviewState = browseReviewViewModel.getState();
        String orderBy = response.getOrderBy();
        if (orderBy != null) {
            browseReviewState.setOrderBy(response.getOrderBy());
        }
        String searchText = response.getSearchText();
        if (searchText != null) {
            browseReviewState.setSearchText(response.getSearchText());
        }
        this.browseReviewViewModel.setState(browseReviewState);
        this.browseReviewViewModel.firePropertyChanged();

//        this.viewManagerModel.setState(browseReviewViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();
    }

    public void switchToWriteView() {
        viewManagerModel.setState(writeReviewViewModel.getViewName());
        System.out.println(writeReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToAccountView() {
        viewManagerModel.setState(accountViewModel.getViewName());
        System.out.println(accountViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToBrowseView() {
        viewManagerModel.setState(browseReviewViewModel.getViewName());
        System.out.println(browseReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
