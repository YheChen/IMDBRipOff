package interface_adapter.account;

/**
 * State for account view.
 */
public class AccountState {
    private String username = "";

    public AccountState(AccountState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public AccountState() {

    }

    /**
     * Gets username for the active account.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username for the active account.
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
