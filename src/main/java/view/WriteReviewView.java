package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.logout.LogoutController;
import interface_adapter.write_review.WriteReviewController;
import interface_adapter.write_review.WriteReviewState;
import interface_adapter.write_review.WriteReviewViewModel;

/**
 * The View for when the user is logged into the program.
 */
public class WriteReviewView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final WriteReviewViewModel writeReviewViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;
    private WriteReviewController writeReviewController;
    private final int SCREEN_WIDTH = 500;
    private final int SCREEN_HEIGHT = 800;

    // private final JLabel username;

    // private final JButton logOut;

    private final JTextField passwordInputField = new JTextField(15);
    // private final JButton changePassword;
    private final JButton submitReview;

    public WriteReviewView(WriteReviewViewModel writeReviewViewModel) {
        this.writeReviewViewModel = writeReviewViewModel;
        this.writeReviewViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Write Review");
        final JLabel user = new JLabel("User: ");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add text fields for the media reviewed, it's rating and content (text).
        final JTextField mediaSearch = new JTextField(20);
        final JTextField rating = new JTextField(20);
        final JTextArea content = new JTextArea();


        final LabelTextPanel mediaField = new LabelTextPanel(
                new JLabel("Media Reviewed: "), mediaSearch);
        final LabelTextPanel ratingField = new LabelTextPanel(
                new JLabel("Your Rating: "), rating);

        final JPanel buttons = new JPanel();
        submitReview = new JButton("Submit Review");
        buttons.add(submitReview);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final WriteReviewState currentState = writeReviewViewModel.getState();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });


        // New Code to handle what happens when the user wants to create a new review
        submitReview.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(submitReview)) {

                        final WriteReviewState state = writeReviewViewModel.getState();
                        writeReviewController.execute(state.getUsername(), state.getContent(), state.getRating(),
                                state.getMediaID());



                        // logoutController.execute(username); // Change to WriteReview Controller when necessary
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                    }
                }
        );

        // Add everything to the Write Review J Panel
        this.add(title);
        this.add(mediaField);
        this.add(ratingField);
        this.add(new JLabel("Type your review here (optional)"));
        this.add(content);
        this.add(buttons);

        // Modify the size of the J Frame
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            // username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setWriteReviewController(WriteReviewController writeReviewController) {
        this.writeReviewController = writeReviewController;
    }
}
