package view;

import data_access.InMemoryMovieDataAccessObject;
import data_access.InMemoryReviewDataAccessObject;
import data_access.MovieDataAccessObject;
import entity.Review;
import interface_adapter.browse_review.BrowseReviewViewModel;
import interface_adapter.browse_review.BrowseReviewController;
import interface_adapter.signup.SignupController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class BrowseView extends JPanel{

    private final String viewName = "browse reviews";
    private BrowseReviewController browseReviewController;
    private final JComboBox<String> sort;
    private final JButton toBrowse;
    private final JButton toReview;
    private final JButton toAccount;
    private final JPanel reviewsPanel;

    public BrowseView(BrowseReviewViewModel browseReviewViewModel) throws Exception {
        this.setLayout(new BorderLayout());

        final JPanel topBar = new JPanel();
        toBrowse = new JButton("Browse Reviews"); // not implemented yet
        toReview = new JButton("Write Review");
        toAccount = new JButton("Your Account");

        JLabel searchLabel = new JLabel("Search:");
        JTextField searchBar = new JTextField(22);

        topBar.add(toBrowse);
        topBar.add(toReview);
        topBar.add(searchLabel);
        topBar.add(searchBar);
        topBar.add(toAccount);

        // Set up frame
        JFrame f = new JFrame("Review Portal");
        f.setSize(800, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());

        // main panel setup
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        // Adding topBar to main
        topBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(topBar);

        final JPanel sort_panel = new JPanel();
        final JLabel sort_label = new JLabel("Sort: ");
        sort_panel.add(sort_label);

        String[] filter_choices = new String[]{"Recent", "Highest Score", "Lowest Score"};
        sort = new JComboBox<>(filter_choices);
        main.add(sort_panel);
        main.add(sort);

        reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
        main.add(reviewsPanel);

        populateReviews("recent");

        // Scroll pane
        JScrollPane scroll = new JScrollPane(main);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scroll, BorderLayout.CENTER);
        this.setVisible(true);

        setupButtonActions();
    }

    private void setupButtonActions() {
        toBrowse.addActionListener(evt -> {
            if (browseReviewController == null) {
                System.err.println("BrowseReviewController is not set.");
            } else {
                System.out.println("Browse Reviews button clicked.");
                browseReviewController.switchToBrowseView();
            }
        });

        toReview.addActionListener(evt -> {
            if (browseReviewController == null) {
                System.err.println("BrowseReviewController is not set.");
            } else {
                System.out.println("Write Review button clicked.");
                browseReviewController.switchToWriteView();
            }
        });

        toAccount.addActionListener(evt -> {
            if (browseReviewController == null) {
                System.err.println("BrowseReviewController is not set.");
            } else {
                System.out.println("Account button clicked.");
                browseReviewController.switchToAccountView();
            }
        });

        sort.addItemListener(item -> {
            if (item.getStateChange() == ItemEvent.SELECTED) {
                try {
                    switch ((String)item.getItem()) {
                        case "Most Recent":
                            populateReviews("recent");
                            break;
                        case "Highest Score":
                            populateReviews("highScore");
                            break;
                        case "Lowest Score":
                            populateReviews("lowScore");
                            break;
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void populateReviews(String orderBy) throws Exception {
        InMemoryReviewDataAccessObject reviewDAO = new InMemoryReviewDataAccessObject();
        reviewDAO.seedData();
        MovieDataAccessObject movieDAO = new MovieDataAccessObject();

        reviewsPanel.removeAll();
        Collection<Review> reviews = reviewDAO.getAllSorted(orderBy);
        for (Review review : reviews) {
            reviewsPanel.add(createReviewPanel(
                    movieDAO.MovieNameFromID(review.getMediaID()),
                    review.getUserID(),
                    review.getDateUpdated(),
                    review.getContent(),
                    movieDAO.MoviePosterFromID(review.getMediaID()),
                    review.getRating()
            ));
        }
        reviewsPanel.revalidate();
        reviewsPanel.repaint();
    }


    // Method to create a review panel
    private static JPanel createReviewPanel(String movieTitleText, String usernameText, Date date, String reviewTextContent, String imageURL, int score) {
        JPanel reviewBody = new JPanel();
        reviewBody.setLayout(new BoxLayout(reviewBody, BoxLayout.Y_AXIS));
        reviewBody.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adds padding around the reviewBody

        JLabel movieTitle = new JLabel(movieTitleText);
        movieTitle.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel username = new JLabel("User: " + usernameText);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        JLabel dateLabel = new JLabel(df.format(date));

        JTextArea reviewText = new JTextArea(reviewTextContent);
        reviewText.setLineWrap(true);
        reviewText.setWrapStyleWord(true);
        reviewText.setEditable(false);
        reviewText.setColumns(30);
        JLabel reviewScore = new JLabel("★".repeat(score) + "☆".repeat(5 - score), SwingConstants.CENTER);
        JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
        try {
            ImageIcon normalImage = new ImageIcon(new URL(imageURL));
            ImageIcon scaledImage = new ImageIcon(normalImage.getImage()
                    .getScaledInstance(normalImage.getIconWidth() / 4,
                            normalImage.getIconHeight() / 4, Image.SCALE_SMOOTH));

            imageLabel = new JLabel(scaledImage);
        }
        catch (IOException e) {
            e.printStackTrace();}

        JPanel imageScore = new JPanel();
        imageScore.setLayout(new BoxLayout(imageScore, BoxLayout.Y_AXIS));

        imageScore.add(imageLabel);
        imageScore.add(reviewScore);


        // Align components
        movieTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        username.setAlignmentX(Component.LEFT_ALIGNMENT);
        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        reviewText.setAlignmentX(Component.LEFT_ALIGNMENT);
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);


        // Add components to reviewBody panel
        reviewBody.add(movieTitle);
        reviewBody.add(username);
        reviewBody.add(dateLabel);
        reviewBody.add(Box.createRigidArea(new Dimension(0, 5))); // Adds space between components
        reviewBody.add(reviewText);

        // Resizing elements
        reviewBody.setPreferredSize(new Dimension(500, 150));
        imageLabel.setPreferredSize(new Dimension(169, 250));


        // List score by number of stars
//        for (int i = 0; i < score; i++){
//
//        }

        // Space for both poster and score
        JPanel image = new JPanel();
        image.setLayout(new BoxLayout(image, BoxLayout.Y_AXIS));


        // Create a review block to ensure the poster and reviewBody are flowlayout.
        JPanel review = new JPanel();
        review.add(imageScore);
        review.add(reviewBody);

        return review;

    }

//    private drawStar(){
//
//    }

    public String getViewName() {
        return viewName;
    }

    public void setBrowseController(BrowseReviewController controller) {
        this.browseReviewController = controller;
    }
}