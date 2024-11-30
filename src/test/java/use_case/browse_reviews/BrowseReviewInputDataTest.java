package use_case.browse_reviews;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrowseReviewInputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String orderBy = "date";
        String searchText = "Harry Potter";

        // Act
        BrowseReviewInputData inputData = new BrowseReviewInputData(orderBy, searchText);

        // Assert
        assertEquals(orderBy, inputData.getOrderBy(), "OrderBy should match the value provided in the constructor");
        assertEquals(searchText, inputData.getSearchText(), "SearchText should match the value provided in the constructor");
    }

    @Test
    void testConstructorWithNullValues() {
        // Arrange
        String orderBy = null;
        String searchText = null;

        // Act
        BrowseReviewInputData inputData = new BrowseReviewInputData(orderBy, searchText);

        // Assert
        assertNull(inputData.getOrderBy(), "OrderBy should be null when null is provided in the constructor");
        assertNull(inputData.getSearchText(), "SearchText should be null when null is provided in the constructor");
    }

    @Test
    void testConstructorWithEmptyStrings() {
        // Arrange
        String orderBy = "";
        String searchText = "";

        // Act
        BrowseReviewInputData inputData = new BrowseReviewInputData(orderBy, searchText);

        // Assert
        assertEquals(orderBy, inputData.getOrderBy(), "OrderBy should match the empty string provided in the constructor");
        assertEquals(searchText, inputData.getSearchText(), "SearchText should match the empty string provided in the constructor");
    }
}
