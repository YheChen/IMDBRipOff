package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
    private final JTextArea content; // Made it an instance variable
    private final JComboBox<String> mediaDropdown;
    private final Map<String, Integer> movieMap; // Map to store title -> ID mapping

    private JButton toBrowse;
    private JButton toReview;
    private JButton toAccount;
    private final JButton submitReview;

    public WriteReviewView(WriteReviewViewModel writeReviewViewModel) throws Exception {
        this.movieMap = new HashMap<>(); // Initialize the map
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

        // Add text fields for the media reviewed, its rating, and content (text).
        content = new JTextArea(5, 20); // Initialize content as a JTextArea
        optionalPanel.add(content);

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
        for (Movie movie : allMovies) {
            final String title = movie.getTitle();
            final int id = movie.getMovieID();
            movieMap.put(title, id); // Store the title-to-ID mapping in the map
        }
        return new JComboBox<>(movieMap.keySet().toArray(new String[0])); // Use titles for dropdown
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
                evt -> {
                    // Retrieve values from the UI
                    final String selectedMedia = (String) mediaDropdown.getSelectedItem();
                    final Integer selectedMovieID = movieMap.get(selectedMedia);
                    final Integer selectedRating = (Integer) ratingDropdown.getSelectedItem();
                    final String reviewText = content.getText();

                    // Print the values to the console
                    System.out.println("Movie Name: " + selectedMedia);
                    System.out.println("Movie ID: " + selectedMovieID);
                    System.out.println("Rating: " + selectedRating);
                    System.out.println("Review Content: " + reviewText);

                    // Optionally, display a confirmation dialog
                    JOptionPane.showMessageDialog(
                            this,
                            "Review submitted successfully:\n"
                                    + "Movie: " + selectedMedia + "\n"
                                    + "Rating: " + selectedRating + "\n"
                                    + "Content: " + reviewText
                    );
                    resetWriteReviewScreen();
                }
        );
    }

    private void resetWriteReviewScreen() {
        mediaDropdown.setSelectedIndex(0);
        ratingDropdown.setSelectedIndex(0);
        content.setText("");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "Password updated for " + state.getUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setWriteReviewController(WriteReviewController writeReviewController) {
        this.writeReviewController = writeReviewController;
    }
}
