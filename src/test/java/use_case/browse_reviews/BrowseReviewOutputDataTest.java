package use_case.browse_reviews;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrowseReviewOutputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String orderBy = "rating";
        String searchText = "Lord of the Rings";

        // Act
        BrowseReviewOutputData outputData = new BrowseReviewOutputData(orderBy, searchText);

        // Assert
        assertEquals(orderBy, outputData.getOrderBy(), "OrderBy should match the value provided in the constructor");
        assertEquals(searchText, outputData.getSearchText(), "SearchText should match the value provided in the constructor");
    }

    @Test
    void testConstructorWithNullValues() {
        // Arrange
        String orderBy = null;
        String searchText = null;

        // Act
        BrowseReviewOutputData outputData = new BrowseReviewOutputData(orderBy, searchText);

        // Assert
        assertNull(outputData.getOrderBy(), "OrderBy should be null when null is provided in the constructor");
        assertNull(outputData.getSearchText(), "SearchText should be null when null is provided in the constructor");
    }

    @Test
    void testConstructorWithEmptyStrings() {
        // Arrange
        String orderBy = "";
        String searchText = "";

        // Act
        BrowseReviewOutputData outputData = new BrowseReviewOutputData(orderBy, searchText);

        // Assert
        assertEquals(orderBy, outputData.getOrderBy(), "OrderBy should match the empty string provided in the constructor");
        assertEquals(searchText, outputData.getSearchText(), "SearchText should match the empty string provided in the constructor");
    }
}