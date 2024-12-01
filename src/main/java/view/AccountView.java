package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interface_adapter.account.AccountController;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.logout.LogoutController;

/**
 * The view that displays options for a logged-in user.
 */
public class AccountView extends JPanel implements ActionListener, PropertyChangeListener {
    public static final int TOPBAR_WIDTH = 1000;
    public static final int TOPBAR_HEIGHT = 50;
    public static final int HEADER_WIDTH = 1000;
    public static final int HEADER_HEIGHT = 30;
    public static final int SUBTITLE_WIDTH = 1000;
    public static final int SUBTITLE_HEIGHT = 30;

    private final String viewName = "account";
    private final AccountViewModel accountViewModel;
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;
    private AccountController accountController;

    private JLabel username;
    private JButton logOut;
    private JButton changePassword;

    private JButton toBrowse;
    private JButton toReview;
    private JButton toAccount;

    public AccountView(AccountViewModel accountViewModel) {
        final JPanel topBar = createTopBar();

        this.accountViewModel = accountViewModel;
        this.accountViewModel.addPropertyChangeListener(this);

        final JPanel header = createHeader();

        final JPanel subtitle = createSubtitle();

        final JPanel buttons = createButtons();

        addActionListeners();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(topBar);
        this.add(header);
        this.add(subtitle);
        this.add(buttons);
    }

    private JPanel createTopBar() {
        final JPanel topBar = new JPanel();
        topBar.setMaximumSize(new Dimension(TOPBAR_WIDTH, TOPBAR_HEIGHT));
        toBrowse = new JButton("Browse Reviews");
        topBar.add(toBrowse);
        toReview = new JButton("Write Review");
        topBar.add(toReview);
        // JLabel searchLabel = new JLabel("Search:");
        // topBar.add(searchLabel);
        // JTextField searchBar = new JTextField(22);
        // topBar.add(searchBar);
        toAccount = new JButton("Your Account");
        topBar.add(toAccount);

        return topBar;
    }

    private JPanel createHeader() {
        final JPanel header = new JPanel();
        header.setMaximumSize(new Dimension(HEADER_WIDTH, HEADER_HEIGHT));
        final Font titleFont = new Font("sans", Font.BOLD, 16);
        final JLabel title = new JLabel("Account");
        title.setFont(titleFont);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        header.add(title);
        return header;
    }

    private JPanel createSubtitle() {
        final JPanel subtitle = new JPanel();
        subtitle.setMaximumSize(new Dimension(SUBTITLE_WIDTH, SUBTITLE_HEIGHT));
        final JLabel usernameInfo = new JLabel("Welcome to the review portal,");
        subtitle.add(usernameInfo);
        username = new JLabel("user");
        subtitle.add(username);
        return subtitle;
    }

    private JPanel createButtons() {
        final JPanel buttons = new JPanel();

        changePassword = new JButton("Change Password");
        changePassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(changePassword);

        logOut = new JButton("Log Out");
        logOut.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(logOut);

        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

        return buttons;
    }

    private void addActionListeners() {
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

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final AccountState currentState = accountViewModel.getState();
                        final String newPassword = JOptionPane.showInputDialog(this, "Enter a new password", null);
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
                        final int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?");
                        if (answer == 0) {
                            logoutController.execute(state.getUsername());
                        }
                    }
                }
        );
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
