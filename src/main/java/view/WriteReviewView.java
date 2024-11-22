package view;

import java.awt.*;
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
import interface_adapter.signup.SignupState;
import interface_adapter.write_review.WriteReviewController;
import interface_adapter.write_review.WriteReviewState;
import interface_adapter.write_review.WriteReviewViewModel;

/**
 * The View for when the user is logged into the program.
 */
public class WriteReviewView extends JPanel implements PropertyChangeListener {

    private final String viewName = "Write Review";
    private final WriteReviewViewModel writeReviewViewModel;
    private final Integer[] rating_choices;
    private WriteReviewController writeReviewController;
    private final JComboBox<Integer> rating_dropdown;

    // private final JLabel username;

    // private final JButton logOut;
    // private final JButton changePassword;
    private final JButton submitReview;

    public WriteReviewView(WriteReviewViewModel writeReviewViewModel) {
        this.writeReviewViewModel = writeReviewViewModel;
        this.writeReviewViewModel.addPropertyChangeListener(this);

        final JPanel media_panel = new JPanel();
        final JPanel rating_panel = new JPanel();
        final JPanel optional_panel = new JPanel();
        final JLabel media = new JLabel("Media Under Review: ");
        final JLabel rating = new JLabel("Choose a rating: ");
        final JLabel optional = new JLabel("Type your review here (optional)");
        media_panel.add(media);
        rating_panel.add(rating);
        optional_panel.add(optional);

        // Add text fields for the media reviewed, it's rating and content (text).
        final JTextArea content = new JTextArea();

        rating_choices = new Integer[]{1, 2, 3, 4, 5};
        rating_dropdown = new JComboBox<>(rating_choices);

        final JPanel buttons = new JPanel();
        submitReview = new JButton("Submit Review");
        buttons.add(submitReview);

//        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
//
//            private void documentListenerHelper() {
//                final WriteReviewState currentState = writeReviewViewModel.getState();
//            }
//
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//        });


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
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(media_panel);
        this.add(rating_panel);
        this.add(rating_dropdown);
        this.add(optional_panel);
        this.add(content);
        this.add(buttons);
        // Modify the size of the J Frame
//        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

//    private void addMediaListener() {
//        mediaField.addActionListener( // This creates an anonymous subclass of ActionListener and instantiates it.
//                evt -> {
//
//                    final WriteReviewState state = writeReviewViewModel.getState();
//                    writeReviewController.execute(state.getUsername(), state.getContent(), state.getRating(),
//                            state.getMediaID());
//
//
//
//                        // logoutController.execute(username); // Change to WriteReview Controller when necessary
//                        // 1. get the state out of the loggedInViewModel. It contains the username.
//                        // 2. Execute the logout Controller.
//                    }
//                );
//    }


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

//    public void setChangePasswordController(ChangePasswordController changePasswordController) {
//        this.changePasswordController = changePasswordController;
//    }

    public void setWriteReviewController(WriteReviewController writeReviewController) {
        this.writeReviewController = writeReviewController;
    }
}
