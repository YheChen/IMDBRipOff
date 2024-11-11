package view;

import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.logout.LogoutController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view that displays options for a logged-in user
 */
public class AccountView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "account view";
    private final AccountViewModel accountViewModel;
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;

    private final JLabel username;
    private final JButton logOut;
    private final JButton changePassword;

    public AccountView(AccountViewModel accountViewModel) {
        this.accountViewModel = accountViewModel;

        final JLabel title = new JLabel("Account");
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        changePassword = new JButton("Change Password");
        buttons.add(changePassword);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
//                    if (evt.getSource().equals(changePassword)) {
//                        final AccountState currentState = accountViewModel.getState();
//
//                        this.changePasswordController.execute(
//                                currentState.getUsername(),
//                                currentState.getPassword()
//                        );
//                    }
                }
        );

        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {

                        final AccountState state = accountViewModel.getState();
                        final String username = state.getUsername();

                        logoutController.execute(username);
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                    }
                }
        );

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final AccountState state = (AccountState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final AccountState state = (AccountState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }
}
