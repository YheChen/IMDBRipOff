package view;

import javax.swing.*;
import java.awt.*;

public class BrowseView {

    public static void main(String[] args) {
        // Set up frame
        JFrame f = new JFrame("Review Portal");
        f.setSize(800, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());

        // Review panel setup
        JPanel reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));

        // Example of a single review panel
        JPanel review = createReviewPanel("Movie Title", "Username", "Date Uploaded",
                "Review body text");

        reviewsPanel.add(review); // Add single review to reviews panel
        reviewsPanel.add(createReviewPanel("Movie Title 2", "Username 2", "Date Uploaded 2",
                "Review body text 2"));
        reviewsPanel.add(createReviewPanel("Movie Title 3", "Username 3", "Date Uploaded 3", "Review body text 3"));


        // Scroll pane
        JScrollPane scroll = new JScrollPane(reviewsPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        f.add(scroll, BorderLayout.CENTER);
        f.setVisible(true);
    }

    // Method to create a review panel
    private static JPanel createReviewPanel(String movieTitleText, String usernameText, String dateText, String reviewTextContent) {
        JPanel review = new JPanel();
        review.setLayout(new BoxLayout(review, BoxLayout.Y_AXIS));
        review.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adds padding around the review

        JLabel movieTitle = new JLabel(movieTitleText);
        movieTitle.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel username = new JLabel(usernameText);
        JLabel date = new JLabel(dateText);

        JTextArea reviewText = new JTextArea(reviewTextContent);
        reviewText.setLineWrap(true);
        reviewText.setWrapStyleWord(true);
        reviewText.setEditable(false);
        reviewText.setColumns(30);

        // Align components
        movieTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        username.setAlignmentX(Component.LEFT_ALIGNMENT);
        date.setAlignmentX(Component.LEFT_ALIGNMENT);
        reviewText.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add components to review panel
        review.add(movieTitle);
        review.add(username);
        review.add(date);
        review.add(Box.createRigidArea(new Dimension(0, 5))); // Adds space between components
        review.add(reviewText);

        // Resize the panel so it works in the scrollable.
        review.setPreferredSize(new Dimension(500, 150));

        return review;
    }
}