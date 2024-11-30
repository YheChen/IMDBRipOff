package interface_adapter.account;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import interface_adapter.browse_review.BrowseReviewViewModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.write_review.WriteReviewViewModel;
import use_case.account.AccountOutputBoundary;
import use_case.browse_reviews.BrowseReviewOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.write_review.WriteReviewOutputData;
import use_case.write_review.WriteReviewOutputBoundary;

public class AccountPresenter implements AccountOutputBoundary {
    private final BrowseReviewViewModel browseReviewViewModel;
    private final WriteReviewViewModel writeReviewViewModel;
    private final AccountViewModel accountViewModel;
    private final ViewManagerModel viewManagerModel;

    public AccountPresenter(BrowseReviewViewModel browseReviewViewModel,
                                 WriteReviewViewModel writeReviewViewModel,
                                 AccountViewModel accountViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.browseReviewViewModel =  browseReviewViewModel;
        this.writeReviewViewModel = writeReviewViewModel;
        this.accountViewModel = accountViewModel;
        this.viewManagerModel = viewManagerModel;
    }

//    @Override
//    public void prepareAccountView() {
//        // On success, switch to the account view.
//
//        final AccountState accountState = accountViewModel.getState();
//        this.accountViewModel.setState(accountState);
//        this.accountViewModel.firePropertyChanged();
//
//        this.viewManagerModel.setState(accountViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();
//    }

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
