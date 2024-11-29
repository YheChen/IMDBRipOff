package view;

import interface_adapter.account.AccountController;
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
    private final String viewName = "account";
    private final AccountViewModel accountViewModel;
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;
    private AccountController accountController;

    private final JLabel username;
    private final JButton logOut;
    private final JButton changePassword;

    private final JButton toBrowse;
    private final JButton toReview;
    private final JButton toAccount;

    public AccountView(AccountViewModel accountViewModel) {
        final JPanel topBar = new JPanel();
        toBrowse = new JButton("Browse Reviews"); // not implemented yet
        topBar.add(toBrowse);
        toReview = new JButton("Write Review");
        topBar.add(toReview);
        JLabel searchLabel = new JLabel("Search:");
        topBar.add(searchLabel);
        JTextField searchBar = new JTextField(22);
        topBar.add(searchBar);
        toAccount = new JButton("Your Account");
        topBar.add(toAccount);

        toReview.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        accountController.switchToWriteView();
                    }
                }
        );

        toAccount.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        accountController.switchToAccountView();
                    }
                }
        );

        toBrowse.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        accountController.switchToBrowseView();
                    }
                }
        );

        this.accountViewModel = accountViewModel;
        this.accountViewModel.addPropertyChangeListener(this);

        final JPanel header = new JPanel();
        header.setMaximumSize(new Dimension(1000, 30));
        final Font titleFont = new Font("sans", Font.BOLD, 16);
        final JLabel title = new JLabel("Account");
        title.setFont(titleFont);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        header.add(title);

        final JPanel subtitle = new JPanel();
        subtitle.setMaximumSize(new Dimension(1000, 30));
        final JLabel usernameInfo = new JLabel("Welcome to the review portal,");
        subtitle.add(usernameInfo);
        username = new JLabel("user");
        subtitle.add(username);

        final JPanel buttons = new JPanel();

        changePassword = new JButton("Change Password");
        changePassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(changePassword);

        logOut = new JButton("Log Out");
        logOut.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(logOut);

        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final AccountState currentState = accountViewModel.getState();

                        String newPassword = JOptionPane.showInputDialog(this, "Enter a new password", null);

                        this.changePasswordController.execute(
                                newPassword,
                                currentState.getUsername()
                        );
                    }
                }
        );
        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        final AccountState state = accountViewModel.getState();

                        int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?");
                        if (answer == 0) {
                            logoutController.execute(state.getUsername());
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(topBar);
        this.add(header);
        this.add(subtitle);
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

    public void setAccountController(AccountController accountController) {
        this.accountController = accountController;
    }
}
