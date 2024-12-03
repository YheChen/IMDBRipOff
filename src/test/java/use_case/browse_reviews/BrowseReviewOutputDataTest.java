package use_case.browse_reviews;

import entity.Review;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class BrowseReviewOutputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String orderBy = "rating";
        String searchText = "Lord of the Rings";
        Collection<Review> reviews = new ArrayList<>();

        // Act
        BrowseReviewOutputData outputData = new BrowseReviewOutputData(orderBy, searchText, reviews);

        // Assert
        assertEquals(orderBy, outputData.getOrderBy(), "OrderBy should match the value provided in the constructor");
        assertEquals(searchText, outputData.getSearchText(), "SearchText should match the value provided in the constructor");
        assertEquals(reviews, outputData.getReviews(), "Reviews should match the value provided in the constructor");
    }

    @Test
    void testConstructorWithNullValues() {
        // Arrange
        String orderBy = null;
        String searchText = null;
        Collection<Review> reviews = null;

        // Act
        BrowseReviewOutputData outputData = new BrowseReviewOutputData(orderBy, searchText, reviews);

        // Assert
        assertNull(outputData.getOrderBy(), "OrderBy should be null when null is provided in the constructor");
        assertNull(outputData.getSearchText(), "SearchText should be null when null is provided in the constructor");
        assertNull(outputData.getReviews(), "Reviews should be null when null is provided in the constructor");
    }

    @Test
    void testConstructorWithEmptyStrings() {
        // Arrange
        String orderBy = "";
        String searchText = "";
        Collection<Review> reviews = new ArrayList<>();

        // Act
        BrowseReviewOutputData outputData = new BrowseReviewOutputData(orderBy, searchText, reviews);

        // Assert
        assertEquals(orderBy, outputData.getOrderBy(), "OrderBy should match the empty string provided in the constructor");
        assertEquals(searchText, outputData.getSearchText(), "SearchText should match the empty string provided in the constructor");
    }
}
