package use_case.logout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogoutOutputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String expectedUsername = "testUser";
        boolean expectedUseCaseFailed = false;

        // Act
        LogoutOutputData outputData = new LogoutOutputData(expectedUsername, expectedUseCaseFailed);

        // Assert
        assertNotNull(outputData);
        assertEquals(expectedUsername, outputData.getUsername());
        assertFalse(outputData.isUseCaseFailed());
    }

    @Test
    void testConstructorAndGettersForFailure() {
        // Arrange
        String expectedUsername = "testUser";
        boolean expectedUseCaseFailed = true;

        // Act
        LogoutOutputData outputData = new LogoutOutputData(expectedUsername, expectedUseCaseFailed);

        // Assert
        assertNotNull(outputData);
        assertEquals(expectedUsername, outputData.getUsername());
        assertTrue(outputData.isUseCaseFailed());
    }

    @Test
    void testNullUsername() {
        // Act
        LogoutOutputData outputData = new LogoutOutputData(null, false);

        // Assert
        assertNull(outputData.getUsername());
        assertFalse(outputData.isUseCaseFailed());
    }

    @Test
    void testEmptyUsername() {
        // Arrange
        String emptyUsername = "";

        // Act
        LogoutOutputData outputData = new LogoutOutputData(emptyUsername, true);

        // Assert
        assertEquals(emptyUsername, outputData.getUsername());
        assertTrue(outputData.isUseCaseFailed());
    }
}
