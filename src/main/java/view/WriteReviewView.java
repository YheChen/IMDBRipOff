package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import data_access.DBReviewDataAccessObject;
import data_access.MovieDataAccessObject;
import entity.Movie;
import entity.Review;
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

    private JTextField searchBar;
    private JButton toBrowse;
    private JButton toReview;
    private JButton toAccount;
    private final JButton submitReview;
    private final BrowseView browseView;

    @SuppressWarnings({"checkstyle:ExecutableStatementCount", "checkstyle:SuppressWarnings"})
    public WriteReviewView(WriteReviewViewModel writeReviewViewModel, BrowseView browseView) throws Exception {
        this.browseView = browseView;
        this.movieMap = new HashMap<>(); // Initialize the map
        final JPanel topBar = createTopBar();
        this.add(topBar);
        this.writeReviewViewModel = writeReviewViewModel;
        this.writeReviewViewModel.addPropertyChangeListener(this);

        final JPanel mediaPanel = new JPanel();
        final JPanel ratingPanel = new JPanel();
        final JPanel optionalPanel = new JPanel();
        final JLabel media = new JLabel("Search Results: ");
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

    @SuppressWarnings({"checkstyle:TrailingComment", "checkstyle:HiddenField", "checkstyle:LambdaBodyLength", "checkstyle:FinalLocalVariable", "checkstyle:SuppressWarnings", "checkstyle:MagicNumber"})
    private JPanel createTopBar() {
        final JPanel topBar = new JPanel();
        toBrowse = new JButton("Browse Reviews");
        toReview = new JButton("Write Review");
        toAccount = new JButton("Your Account");

        final JLabel searchLabel = new JLabel("Search:");
        searchBar = new JTextField(20); // Initialize search bar

        // Add action listener to handle Enter key press
        searchBar.addActionListener(evt -> {
            String searchText = searchBar.getText();
            if (searchText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter text to search.");
            } else {
                System.out.println("Searching for: " + searchText);
                updateMediaDropdownWithSearch(searchText);
            }
        });

        topBar.add(toBrowse);
        topBar.add(toReview);
        topBar.add(searchLabel);
        topBar.add(searchBar);
        topBar.add(toAccount);
        return topBar;
    }

    private void updateMediaDropdownWithSearch(String query) {
        try {
            // Use MovieDataAccessObject to search movies
            MovieDataAccessObject movieDao = new MovieDataAccessObject();
            Collection<Movie> searchResults = movieDao.searchMovies(query);

            // Clear existing dropdown items and movieMap
            mediaDropdown.removeAllItems();
            movieMap.clear();

            // Populate dropdown and movieMap with search results
            for (Movie movie : searchResults) {
                String title = movie.getTitle();
                int id = movie.getMovieID();
                mediaDropdown.addItem(title); // Add title to dropdown
                movieMap.put(title, id);     // Map title to ID
            }

            // Handle no results case
            if (searchResults.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No movies found for the query: " + query);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching movies: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"checkstyle:FinalLocalVariable", "checkstyle:SuppressWarnings", "checkstyle:TrailingComment", "checkstyle:RightCurly"})
    private void updateMediaDropdown(String searchText) {
        try {
            MovieDataAccessObject movieDao = new MovieDataAccessObject();
            Collection<Movie> matchingMovies = movieDao.searchMovies(searchText);
            mediaDropdown.removeAllItems(); // Clear current dropdown items
            movieMap.clear(); // Clear the existing map

            for (Movie movie : matchingMovies) {
                mediaDropdown.addItem(movie.getTitle());
                movieMap.put(movie.getTitle(), movie.getMovieID()); // Update title-to-ID mapping
            }

            if (matchingMovies.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No movies found matching: " + searchText);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching movies: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"checkstyle:TrailingComment", "checkstyle:SuppressWarnings"})
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

    @SuppressWarnings({"checkstyle:FinalLocalVariable", "checkstyle:TrailingComment", "checkstyle:OperatorWrap", "checkstyle:RightCurly", "checkstyle:IllegalCatch", "checkstyle:CatchParameterName", "checkstyle:SuppressWarnings"})
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
        submitReview.addActionListener(evt -> {
            if (evt.getSource().equals(submitReview)) {
                try {
                    // Retrieve values from the UI
                    String selectedMedia = (String) mediaDropdown.getSelectedItem();
                    Integer selectedRating = (Integer) ratingDropdown.getSelectedItem();
                    String reviewText = content.getText();

                    if (selectedMedia == null) {
                        JOptionPane.showMessageDialog(this, "Please select a movie.");
                        return;
                    }
                    if (selectedRating == null) {
                        JOptionPane.showMessageDialog(this, "Please select a rating.");
                        return;
                    }

                    // Get the movie ID from the movieMap
                    Integer selectedMovieID = movieMap.get(selectedMedia);
                    if (selectedMovieID == null) {
                        JOptionPane.showMessageDialog(this, "Failed to find the ID for the selected movie.");
                        return;
                    }

                    // Generate a unique user ID (replace with the actual logged-in user's ID)
                    String userId = "userId"; // Replace this with actual user ID in your application
                    Date currentDate = new Date(); // Current date

                    // Save the review to MongoDB
                    DBReviewDataAccessObject reviewDao = new DBReviewDataAccessObject();
                    Review review = new Review(
                            java.util.UUID.randomUUID().toString(), // Generate a unique ID
                            userId,
                            String.valueOf(selectedMovieID), // Movie ID mapped from selectedMedia
                            reviewText,
                            selectedRating,
                            currentDate
                    );
                    reviewDao.save(review);

                    // Print details to the terminal
                    System.out.println("Review Details:");
                    System.out.println("User ID: " + userId);
                    System.out.println("Movie ID: " + selectedMovieID);
                    System.out.println("Review Text: " + reviewText);
                    System.out.println("Rating: " + selectedRating);
                    System.out.println("Date: " + currentDate);

                    // Show success message
                    JOptionPane.showMessageDialog(
                            this,
                            "Review submitted successfully:\n" +
                                    "Movie: " + selectedMedia + "\n" +
                                    "Rating: " + selectedRating + "\n" +
                                    "Content: " + reviewText
                    );

                    // Reset the form
                    mediaDropdown.setSelectedIndex(0);
                    ratingDropdown.setSelectedIndex(0);
                    content.setText("");

                    // Refresh BrowseView
                    browseView.refreshReviews();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Failed to submit review: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
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
