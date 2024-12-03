package use_case.write_review;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class WriteReviewInputDataTest {

    @Test
    void testWriteReviewInputDataInitialization() {
        // Arrange
        String userId = "testUser";
        String title = "testTitle";
        String content = "This is a test review.";
        int rating = 4;
        String media = "Test Movie";

        // Act
        WriteReviewInputData inputData = new WriteReviewInputData(userId, title, content, rating, media);

        // Assert
        assertEquals(userId, inputData.getUserID());
        assertEquals(content, inputData.getContent());
        assertEquals(rating, inputData.getRating());
        assertEquals(media, inputData.getMedia());
        assertNotNull(inputData.getDate()); // Date should be automatically generated
    }

    @Test
    void testWriteReviewInputDataDateGeneratedAutomatically() {
        // Arrange
        String userId = "user123";
        String title = "testTitle";
        String content = "Review content";
        int rating = 5;
        String media = "Media Name";

        // Act
        WriteReviewInputData inputData = new WriteReviewInputData(userId, title, content, rating, media);
        Date now = new Date();

        // Assert
        assertNotNull(inputData.getDate());
        assertTrue(inputData.getDate().before(now) || inputData.getDate().equals(now));
    }

    @Test
    void testWriteReviewInputDataWithEdgeValues() {
        // Arrange
        String userId = "";
        String title = "";
        String content = "";
        int rating = 0;
        String media = "";

        // Act
        WriteReviewInputData inputData = new WriteReviewInputData(userId, title, content, rating, media);

        // Assert
        assertEquals("", inputData.getUserID());
        assertEquals("", inputData.getContent());
        assertEquals(0, inputData.getRating());
        assertEquals("", inputData.getMedia());
        assertNotNull(inputData.getDate());
    }
}
