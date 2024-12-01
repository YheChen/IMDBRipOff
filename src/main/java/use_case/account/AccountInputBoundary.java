package use_case.account;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface AccountInputBoundary {
    /**
     * Switches to write view.
     */
    void switchToWriteView();

    /**
     * Switches to account view.
     */
    void switchToAccountView();

    /**
     * Switches to browse view.
     */
    void switchToBrowseView();
}
