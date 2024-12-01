package interface_adapter.account;

import interface_adapter.ViewModel;

/**
 * View model for the account view.
 */
public class AccountViewModel extends ViewModel<AccountState> {
    public AccountViewModel() {
        super("account");
        setState(new AccountState());
    }
}
