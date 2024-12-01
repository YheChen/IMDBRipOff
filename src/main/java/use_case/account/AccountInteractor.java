package use_case.account;

/**
 * The Login Interactor.
 */
public class AccountInteractor implements AccountInputBoundary {
    // private final LoginUserDataAccessInterface userDataAccessObject;
    private final AccountOutputBoundary accountPresenter;

    public AccountInteractor(AccountOutputBoundary accountPresenter) {
        // this.userDataAccessObject = userDataAccessInterface;
        this.accountPresenter = accountPresenter;
    }

    /**
     * Switches to write view.
     */
    public void switchToWriteView() {
        accountPresenter.switchToWriteView();
    }

    /**
     * Switches to account view.
     */
    public void switchToAccountView() {
        accountPresenter.switchToAccountView();
    }

    /**
     * Switches to browse view.
     */
    public void switchToBrowseView() {
        accountPresenter.switchToBrowseView();
    }

}
