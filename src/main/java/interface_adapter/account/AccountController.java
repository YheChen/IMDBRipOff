package interface_adapter.account;

import use_case.account.AccountInputBoundary;


/**
 * The controller for the Login Use Case.
 */
public class AccountController {

    private final AccountInputBoundary accountInteractor;

    public AccountController(AccountInputBoundary accountInteractor) {
        this.accountInteractor = accountInteractor;
    }

    public void switchToWriteView() {
        accountInteractor.switchToWriteView();
    }

    public void switchToAccountView() {
        accountInteractor.switchToAccountView();
    }

    public void switchToBrowseView() {
        accountInteractor.switchToBrowseView();
    }

}