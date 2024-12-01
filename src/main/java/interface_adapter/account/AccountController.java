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

    /**
     * Switches to write view.
     */
    public void switchToWriteView() {
        accountInteractor.switchToWriteView();
    }

    /**
     * Switches to account view.
     */
    public void switchToAccountView() {
        accountInteractor.switchToAccountView();
    }

    /**
     * Switches to browse view.
     */
    public void switchToBrowseView() {
        accountInteractor.switchToBrowseView();
    }

}
