package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import data_access.DBReviewDataAccessObject;
import data_access.InMemoryReviewDataAccessObject;
import data_access.MovieDataAccessObject;
import entity.Review;
import interface_adapter.browse_review.BrowseReviewController;
import interface_adapter.browse_review.BrowseReviewState;
import interface_adapter.browse_review.BrowseReviewViewModel;

/**
 * View for browsing reviews.
 */
public class BrowseView extends JPanel {
    private static final String SORT_RECENT = "Recent";
    private static final String SORT_HIGHSCORE = "Highest Score";
    private static final String SORT_LOWSCORE = "Lowest Score";

    private static final int TOPBAR_WIDTH = 1000;
    private static final int TOPBAR_HEIGHT = 50;
    private static final int REVIEW_PORTAL_FRAME_WIDTH = 800;
    private static final int REVIEW_PORTAL_FRAME_HEIGHT = 500;
    private static final int SORT_PANEL_WIDTH = 1000;
    private static final int SORT_PANEL_HEIGHT = 50;
    private static final int SEARCHBAR_COLUMNS = 22;
    private static final int REVIEW_PANEL_PADDING = 10;
    private static final int MOVIE_TITLE_FONT_SIZE = 14;
    private static final int REVIEW_TEXT_COLUMNS = 30;
    private static final int IMAGE_SCALE_FACTOR = 4;
    private static final int REVIEW_BODY_BOX_WIDTH = 0;
    private static final int REVIEW_BODY_BOX_HEIGHT = 5;
    private static final int REVIEW_BODY_WIDTH = 500;
    private static final int REVIEW_BODY_HEIGHT = 150;
    private static final int IMAGE_LABEL_WIDTH = 169;
    private static final int IMAGE_LABEL_HEIGHT = 250;

    private final String viewName = "browse reviews";
    private BrowseReviewController browseReviewController;
    private JTextField searchBar;
    private final JComboBox<String> sort;
    private JButton toBrowse;
    private JButton toReview;
    private JButton toAccount;
    private final JPanel reviewsPanel;

    public BrowseView(BrowseReviewViewModel browseReviewViewModel) throws Exception {
        this.setLayout(new BorderLayout());

        final JPanel topBar = createTopBar();

        // Set up frame
        final JFrame frame = new JFrame("Review Portal");
        frame.setSize(REVIEW_PORTAL_FRAME_WIDTH, REVIEW_PORTAL_FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // main panel setup
        final JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        // Adding topBar to main
        topBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(topBar);

        final JPanel sortPanel = new JPanel();
        sortPanel.setMaximumSize(new Dimension(SORT_PANEL_WIDTH, SORT_PANEL_HEIGHT));
        final JLabel sortLabel = new JLabel("Sort: ");
        sortPanel.add(sortLabel);

        final String[] sortChoices = new String[]{SORT_RECENT, SORT_HIGHSCORE, SORT_LOWSCORE};
        sort = new JComboBox<>(sortChoices);
        sortPanel.add(sort);

        main.add(sortPanel);

        reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
        main.add(reviewsPanel);

        // Get state
        browseReviewViewModel.addPropertyChangeListener(evt -> updateState(browseReviewViewModel));
        updateState(browseReviewViewModel);

        // Scroll pane
        final JScrollPane scroll = new JScrollPane(main);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scroll, BorderLayout.CENTER);
        this.setVisible(true);

        setupButtonActions();
    }

    private JPanel createTopBar() {
        final JPanel topBar = new JPanel();
        topBar.setMaximumSize(new Dimension(TOPBAR_WIDTH, TOPBAR_HEIGHT));
        toBrowse = new JButton("Browse Reviews");
        toReview = new JButton("Write Review");
        toAccount = new JButton("Your Account");

        final JLabel searchLabel = new JLabel("Search:");
        searchBar = new JTextField(SEARCHBAR_COLUMNS);

        topBar.add(toBrowse);
        topBar.add(toReview);
        topBar.add(searchLabel);
        topBar.add(searchBar);
        topBar.add(toAccount);

        return topBar;
    }

    private void setupButtonActions() {
        toBrowse.addActionListener(evt -> {
            if (browseReviewController != null) {
                System.out.println("Browse Reviews button clicked.");
                browseReviewController.switchToBrowseView();
            }
        });

        toReview.addActionListener(evt -> {
            if (browseReviewController != null) {
                System.out.println("Write Review button clicked.");
                browseReviewController.switchToWriteView();
            }
        });

        toAccount.addActionListener(evt -> {
            if (browseReviewController != null) {
                System.out.println("Account button clicked.");
                browseReviewController.switchToAccountView();
            }
        });

        searchBar.addActionListener(evt -> {
            final String searchText = searchBar.getText();
            browseReviewController.execute(null, searchText);
        });

        sort.addItemListener(this::sortItemListener);
    }

    private void sortItemListener(ItemEvent item) {
        if (item.getStateChange() == ItemEvent.SELECTED) {
            switch ((String) item.getItem()) {
                case SORT_RECENT:
                    browseReviewController.execute("recent", null);
                    break;
                case SORT_HIGHSCORE:
                    browseReviewController.execute("highScore", null);
                    break;
                case SORT_LOWSCORE:
                    browseReviewController.execute("lowScore", null);
                    break;
                default:
                    break;
            }
        }
    }

    private void updateState(BrowseReviewViewModel browseReviewViewModel) {
        final BrowseReviewState state = browseReviewViewModel.getState();
        final String orderBy = state.getOrderBy();
        switch (orderBy) {
            case "recent":
                sort.setSelectedItem(SORT_RECENT);
                break;
            case "highScore":
                sort.setSelectedItem(SORT_HIGHSCORE);
                break;
            case "lowScore":
                sort.setSelectedItem(SORT_LOWSCORE);
                break;
            default:
                break;
        }
        final String searchText = state.getSearchText();
        try {
            populateReviews(orderBy, searchText);
        }
        catch (IOException err) {
            throw new RuntimeException(err);
        }
    }

    private void populateReviews(String orderBy, String searchText) throws IOException {
        final DBReviewDataAccessObject reviewDao = new DBReviewDataAccessObject();
        final MovieDataAccessObject movieDao = new MovieDataAccessObject();

        reviewsPanel.removeAll();
        final Collection<Review> reviews = reviewDao.getAllSorted(orderBy, searchText);
        for (Review review : reviews) {
            reviewsPanel.add(createReviewPanel(
                    movieDao.movieNameFromID(review.getMediaID()),
                    review.getUserID(),
                    review.getDateUpdated(),
                    review.getContent(),
                    movieDao.moviePosterFromID(review.getMediaID()),
                    review.getRating()
            ));
        }
        reviewsPanel.revalidate();
        reviewsPanel.repaint();
    }

    private static JPanel createReviewPanelBody() {
        final JPanel reviewBody = new JPanel();
        reviewBody.setLayout(new BoxLayout(reviewBody, BoxLayout.Y_AXIS));
        // Adds padding around the reviewBody
        reviewBody.setBorder(BorderFactory.createEmptyBorder(
                REVIEW_PANEL_PADDING,
                REVIEW_PANEL_PADDING,
                REVIEW_PANEL_PADDING,
                REVIEW_PANEL_PADDING));
        return reviewBody;
    }

    private static JTextArea createReviewPanelText(String reviewTextContent) {
        final JTextArea reviewText = new JTextArea(reviewTextContent);
        reviewText.setLineWrap(true);
        reviewText.setWrapStyleWord(true);
        reviewText.setEditable(false);
        reviewText.setColumns(REVIEW_TEXT_COLUMNS);
        return reviewText;
    }

    private static JLabel createReviewPanelImageLabel(String imageUrl) {
        JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
        try {
            final ImageIcon normalImage = new ImageIcon(new URL(imageUrl));
            final ImageIcon scaledImage = new ImageIcon(normalImage.getImage()
                    .getScaledInstance(normalImage.getIconWidth() / IMAGE_SCALE_FACTOR,
                            normalImage.getIconHeight() / IMAGE_SCALE_FACTOR, Image.SCALE_SMOOTH));

            imageLabel = new JLabel(scaledImage);
        }
        catch (IOException err) {
            err.printStackTrace();
        }
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        imageLabel.setPreferredSize(new Dimension(IMAGE_LABEL_WIDTH, IMAGE_LABEL_HEIGHT));
        return imageLabel;
    }

    private static JPanel createReviewPanelImageScore(String imageUrl, int score) {
        final JLabel reviewScore = new JLabel(
                "\u2605".repeat(score) + "\u2606".repeat(5 - score),
                SwingConstants.CENTER);

        final JLabel imageLabel = createReviewPanelImageLabel(imageUrl);

        final JPanel imageScore = new JPanel();
        imageScore.setLayout(new BoxLayout(imageScore, BoxLayout.Y_AXIS));

        imageScore.add(imageLabel);
        imageScore.add(reviewScore);
        return imageScore;
    }

    private static JPanel createReviewPanel(String movieTitleText, String usernameText, Date date,
                                            String reviewTextContent, String imageUrl, int score) {
        final JPanel reviewBody = createReviewPanelBody();

        final JLabel movieTitle = new JLabel(movieTitleText);
        movieTitle.setFont(new Font("Arial", Font.BOLD, MOVIE_TITLE_FONT_SIZE));
        final JLabel username = new JLabel("User: " + usernameText);
        final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        final JLabel dateLabel = new JLabel(df.format(date));

        final JTextArea reviewText = createReviewPanelText(reviewTextContent);
        final JPanel imageScore = createReviewPanelImageScore(imageUrl, score);

        // Align components
        movieTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        username.setAlignmentX(Component.LEFT_ALIGNMENT);
        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        reviewText.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add components to reviewBody panel
        reviewBody.add(movieTitle);
        reviewBody.add(username);
        reviewBody.add(dateLabel);
        reviewBody.add(Box.createRigidArea(new Dimension(REVIEW_BODY_BOX_WIDTH, REVIEW_BODY_BOX_HEIGHT)));
        reviewBody.add(reviewText);

        // Resizing elements
        reviewBody.setPreferredSize(new Dimension(REVIEW_BODY_WIDTH, REVIEW_BODY_HEIGHT));

        // Space for both poster and score
        final JPanel image = new JPanel();
        image.setLayout(new BoxLayout(image, BoxLayout.Y_AXIS));

        // Create a review block to ensure the poster and reviewBody are flowlayout.
        final JPanel review = new JPanel();
        review.add(imageScore);
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
