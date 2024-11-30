package use_case.logout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogoutInputDataTest {

    @Test
    void testConstructorAndGetUsername() {
        // Arrange
        String expectedUsername = "testUser";

        // Act
        LogoutInputData inputData = new LogoutInputData(expectedUsername);

        // Assert
        assertNotNull(inputData);
        assertEquals(expectedUsername, inputData.getUsername());
    }

    @Test
    void testNullUsername() {
        // Act
        LogoutInputData inputData = new LogoutInputData(null);

        // Assert
        assertNull(inputData.getUsername());
    }

    @Test
    void testEmptyUsername() {
        // Arrange
        String emptyUsername = "";

        // Act
        LogoutInputData inputData = new LogoutInputData(emptyUsername);

        // Assert
        assertEquals(emptyUsername, inputData.getUsername());
    }
}
