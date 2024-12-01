package use_case.account;

/**
 * Output boundary for Account use case.
 */
public interface AccountOutputBoundary {
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
