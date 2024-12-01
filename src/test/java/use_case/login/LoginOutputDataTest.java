package use_case.login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginOutputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String expectedUsername = "testUser";
        boolean expectedUseCaseFailed = false;

        // Act
        LoginOutputData loginOutputData = new LoginOutputData(expectedUsername, expectedUseCaseFailed);

        // Assert
        assertEquals(expectedUsername, loginOutputData.getUsername());
        assertFalse(loginOutputData.isUseCaseFailed());
    }

    @Test
    void testUseCaseFailedTrue() {
        // Arrange
        String username = "testUser";
        boolean useCaseFailed = true;

        // Act
        LoginOutputData loginOutputData = new LoginOutputData(username, useCaseFailed);

        // Assert
        assertEquals(username, loginOutputData.getUsername());
        assertTrue(loginOutputData.isUseCaseFailed());
    }

    @Test
    void testNullUsername() {
        // Arrange
        String nullUsername = null;
        boolean useCaseFailed = false;

        // Act
        LoginOutputData loginOutputData = new LoginOutputData(nullUsername, useCaseFailed);

        // Assert
        assertNull(loginOutputData.getUsername());
        assertFalse(loginOutputData.isUseCaseFailed());
    }
}
