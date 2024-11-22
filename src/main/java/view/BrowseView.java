package view;

import interface_adapter.browse_review.BrowseReviewViewModel;
import interface_adapter.browse_review.BrowseReviewController;
import interface_adapter.signup.SignupController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class BrowseView {

    private final String viewName = "browse reviews";
    private final JButton toBrowse;
    private final JButton toReview;
    private final JButton toAccount;
    private BrowseReviewController browseReviewController;

    public static void main(String[] args) {

        final JPanel topBar = new JPanel();
        JButton toBrowse = new JButton("Browse Reviews"); // not implemented yet
        topBar.add(toBrowse);
        JButton toReview = new JButton("Write Review");
        topBar.add(toReview);
        JLabel searchLabel = new JLabel("Search:");
        topBar.add(searchLabel);
        JTextField searchBar = new JTextField(22);
        topBar.add(searchBar);
        JButton toAccount = new JButton("Your Account");
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

        // Adding reviews to main
        main.add(createReviewPanel("Movie Title 2", "Username 2", "Date Uploaded 2",
                "Review body text 2"));
        main.add(createReviewPanel("Movie Title 3", "Username 3", "Date Uploaded 3", "Review body text 3"));


        // Scroll pane
        JScrollPane scroll = new JScrollPane(main);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        f.add(scroll, BorderLayout.CENTER);
        f.setVisible(true);
    }

    public BrowseView(BrowseReviewViewModel browseReviewViewModel){
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
                        browseReviewController.switchToWriteView();
                    }
                }
        );

        toAccount.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        browseReviewController.switchToAccountView();
                    }
                }
        );

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

        // Adding reviews (change into loop later)
        main.add(createReviewPanel("Movie Title 2", "Username 2", "Date Uploaded 2",
                "Review body text 2"));
        main.add(createReviewPanel("Movie Title 3", "Username 3", "Date Uploaded 3", "Review body text 3"));


        // Scroll pane
        JScrollPane scroll = new JScrollPane(main);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        f.add(scroll, BorderLayout.CENTER);
        f.setVisible(true);


    }

    // Method to create a review panel
    private static JPanel createReviewPanel(String movieTitleText, String usernameText, String dateText, String reviewTextContent) {
        JPanel reviewBody = new JPanel();
        reviewBody.setLayout(new BoxLayout(reviewBody, BoxLayout.Y_AXIS));
        reviewBody.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adds padding around the reviewBody

        JLabel movieTitle = new JLabel(movieTitleText);
        movieTitle.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel username = new JLabel(usernameText);
        JLabel date = new JLabel(dateText);

        JTextArea reviewText = new JTextArea(reviewTextContent);
        reviewText.setLineWrap(true);
        reviewText.setWrapStyleWord(true);
        reviewText.setEditable(false);
        reviewText.setColumns(30);
        JLabel imageLabel = new JLabel();
        try {
            String imagePath = "https://xl.movieposterdb.com/08_06/2008/468569/xl_468569_fe24b125.jpg?v=2024-11-16%2017:50:15";
            ImageIcon normalImage = new ImageIcon(new URL(imagePath));
            ImageIcon scaledImage = new ImageIcon(normalImage.getImage()
                    .getScaledInstance(normalImage.getIconWidth() / 5,
                            normalImage.getIconHeight() / 5, Image.SCALE_SMOOTH));

            imageLabel = new JLabel(scaledImage);
        }
        catch (IOException e) {
            e.printStackTrace();}

        // Align components
        movieTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        username.setAlignmentX(Component.LEFT_ALIGNMENT);
        date.setAlignmentX(Component.LEFT_ALIGNMENT);
        reviewText.setAlignmentX(Component.LEFT_ALIGNMENT);
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);


        // Add components to reviewBody panel
        reviewBody.add(movieTitle);
        reviewBody.add(username);
        reviewBody.add(date);
        reviewBody.add(Box.createRigidArea(new Dimension(0, 5))); // Adds space between components
        reviewBody.add(reviewText);

        // Resizing elements
        reviewBody.setPreferredSize(new Dimension(500, 150));
        imageLabel.setPreferredSize(new Dimension(169, 250));

        // Create a review block to ensure the poster and reviewBody are flowlayout.
        JPanel review = new JPanel();
        review.add(imageLabel);
        review.add(reviewBody);

        return review;

    }

    public String getViewName() {
        return viewName;
    }

    public void setBrowseController(BrowseReviewController controller) {
        this.browseReviewController = controller;
    }
}