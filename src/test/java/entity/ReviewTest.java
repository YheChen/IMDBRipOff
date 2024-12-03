package entity;

import entity.Review;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest {

    @Test
    void reviewWithGeneratedIDWorksAsExpectedTest() {
        // Arrange
        final int rating = 5;
        String userID = "user123";
        String title = "Harry Potter and ___";
        String mediaID = "movie456";
        String content = "A great movie!";
        Date dateCreated = new Date();

        // Act
        Review review = new Review(userID, mediaID, title, content, rating, dateCreated);

        // Assert
        assertEquals(content, review.getContent());
        assertEquals(userID, review.getUserID());
        assertEquals(mediaID, review.getMediaID());
        assertEquals(dateCreated, review.getDateCreated());
        assertEquals(dateCreated, review.getDateUpdated());
        assertEquals(rating, review.getRating());
        assertEquals(title, review.getTitle());
        assertNotNull(review.getReviewID());
        assertEquals(5, review.getReviewID().length());
    }

    @Test
    void reviewWithCustomIDWorksAsExpectedTest() {
        // Arrange
        String reviewID = "custom123";
        String userID = "user789";
        String mediaID = "movie123";
        String content = "An okay movie.";
        int rating = 3;
        Date dateCreated = new Date();

        // Act
        Review review = new Review(reviewID, userID, mediaID, content, rating, dateCreated);

        // Assert
        assertEquals(reviewID, review.getReviewID());
        assertEquals(content, review.getContent());
        assertEquals(userID, review.getUserID());
        assertEquals(mediaID, review.getMediaID());
        assertEquals(dateCreated, review.getDateCreated());
        assertEquals(dateCreated, review.getDateUpdated());
        assertEquals(rating, review.getRating());
    }

    @Test
    void generateReviewIDCreatesUniqueID() {
        // Arrange
        Review review1 = new Review("user1", "media1", "title1", "Content 1", 4, new Date());
        Review review2 = new Review("user2", "media2", "title2","Content 2",  5, new Date());

        // Act
        String id1 = review1.getReviewID();
        String id2 = review2.getReviewID();

        // Assert
        assertNotEquals(id1, id2);
        assertEquals(5, id1.length());
        assertEquals(5, id2.length());
    }
}
