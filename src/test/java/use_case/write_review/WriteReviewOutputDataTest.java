package use_case.write_review;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class WriteReviewOutputDataTest {

    @Test
    void testWriteReviewOutputDataInitialization() {
        // Arrange
        String userId = "testUser";
        String content = "This is a test review.";
        int rating = 5;
        String media = "Test Movie";
        Date date = new Date();

        // Act
        WriteReviewOutputData outputData = new WriteReviewOutputData(userId, content, rating, media, date);

        // Assert
        assertEquals(userId, outputData.getUserID());
        assertEquals(content, outputData.getContent());
        assertEquals(rating, outputData.getRating());
        assertEquals(media, outputData.getMedia());
        assertEquals(date, outputData.getDate());
    }

    @Test
    void testWriteReviewOutputDataWithNullValues() {
        // Arrange
        String userId = null;
        String content = null;
        int rating = 0;
        String media = null;
        Date date = null;

        // Act
        WriteReviewOutputData outputData = new WriteReviewOutputData(userId, content, rating, media, date);

        // Assert
        assertNull(outputData.getUserID());
        assertNull(outputData.getContent());
        assertEquals(0, outputData.getRating());
        assertNull(outputData.getMedia());
        assertNull(outputData.getDate());
    }
}
