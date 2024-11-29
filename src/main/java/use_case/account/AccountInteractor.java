package use_case.account;

import entity.User;
import interface_adapter.browse_review.BrowseReviewPresenter;
import use_case.login.*;


/**
 * The Login Interactor.
 */
public class AccountInteractor implements AccountInputBoundary {
    // private final LoginUserDataAccessInterface userDataAccessObject;
    private final AccountOutputBoundary accountPresenter;

    public AccountInteractor(AccountOutputBoundary accountPresenter) {
        //this.userDataAccessObject = userDataAccessInterface;
        this.accountPresenter = accountPresenter;
    }


    public void switchToWriteView() {
        accountPresenter.switchToWriteView();
    }

    public void switchToAccountView() {
        accountPresenter.switchToAccountView();
    }

    public void switchToBrowseView() {
        accountPresenter.switchToBrowseView();
    }

}
