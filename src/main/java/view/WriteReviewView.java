package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import data_access.MovieDataAccessObject;
import entity.Movie;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.write_review.WriteReviewController;
import interface_adapter.write_review.WriteReviewState;
import interface_adapter.write_review.WriteReviewViewModel;

/**
 * The View for when the user is logged into the program.
 */
public class WriteReviewView extends JPanel implements PropertyChangeListener {
    private static final Integer[] RATING_CHOICES = new Integer[]{1, 2, 3, 4, 5};

    private final String viewName = "write review";
    private final WriteReviewViewModel writeReviewViewModel;
    private WriteReviewController writeReviewController;
    private final JComboBox<Integer> ratingDropdown;
    private String[] mediaChoices = new String[]{};
    private JComboBox<String> mediaDropdown;

    private JButton toBrowse;
    private JButton toReview;
    private JButton toAccount;

    // private final JLabel username;

    // private final JButton logOut;
    // private final JButton changePassword;
    private final JButton submitReview;

    public WriteReviewView(WriteReviewViewModel writeReviewViewModel) throws Exception {
        final JPanel topBar = createTopBar();

        this.add(topBar);
        this.writeReviewViewModel = writeReviewViewModel;
        this.writeReviewViewModel.addPropertyChangeListener(this);

        final JPanel mediaPanel = new JPanel();
        final JPanel ratingPanel = new JPanel();
        final JPanel optionalPanel = new JPanel();
        final JLabel media = new JLabel("Media Under Review: ");
        final JLabel rating = new JLabel("Choose a rating: ");
        final JLabel optional = new JLabel("Type your review here (optional)");
        mediaPanel.add(media);
        ratingPanel.add(rating);
        optionalPanel.add(optional);

        // Add text fields for the media reviewed, it's rating and content (text).
        final JTextArea content = new JTextArea();

        ratingDropdown = new JComboBox<>(RATING_CHOICES);

        // Dropdown of all movies
        mediaDropdown = createMediaDropdown();

        final JPanel buttons = new JPanel();
        submitReview = new JButton("Submit Review");
        buttons.add(submitReview);

        addActionListeners();

        // Add everything to the Write Review J Panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(mediaPanel);
        this.add(mediaDropdown);
        this.add(ratingPanel);
        this.add(ratingDropdown);
        this.add(optionalPanel);
        this.add(content);
        this.add(buttons);
        // Modify the size of the J Frame
        // this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    private JPanel createTopBar() {
        final JPanel topBar = new JPanel();
        toBrowse = new JButton("Browse Reviews");
        topBar.add(toBrowse);
        toReview = new JButton("Write Review");
        topBar.add(toReview);

        toAccount = new JButton("Your Account");
        topBar.add(toAccount);
        return topBar;
    }

    private JComboBox<String> createMediaDropdown() throws Exception {
        final MovieDataAccessObject movies = new MovieDataAccessObject();
        final Collection<Movie> allMovies = movies.fetchPopularMovies();
        for (Movie movie: allMovies) {
            final String title = movie.getTitle();
            mediaChoices = Arrays.copyOf(mediaChoices, mediaChoices.length + 1);
            mediaChoices[mediaChoices.length - 1] = title;
        }
        mediaDropdown = new JComboBox<>(mediaChoices);
        return mediaDropdown;
    }

    private void addActionListeners() {
        toReview.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        writeReviewController.switchToWriteView();
                    }
                }
        );

        toAccount.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        writeReviewController.switchToAccountView();
                    }
                }
        );

        toBrowse.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        writeReviewController.switchToBrowseView();
                    }
                }
        );

        // New Code to handle what happens when the user wants to create a new review
        submitReview.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(submitReview)) {

                        final WriteReviewState state = writeReviewViewModel.getState();
                        writeReviewController.execute(state.getUsername(), state.getContent(), state.getRating(),
                                state.getMediaID());
                    }
                }
        );
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        /* if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            // username.setText(state.getUsername());
        }*/
        if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setWriteReviewController(WriteReviewController writeReviewController) {
        this.writeReviewController = writeReviewController;
    }
}
