package use_case.signup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupOutputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String username = "testUser";
        boolean useCaseFailed = false;

        // Act
        SignupOutputData signupOutputData = new SignupOutputData(username, useCaseFailed);

        // Assert
        assertEquals(username, signupOutputData.getUsername(), "Username should match the value provided in the constructor");
        assertFalse(signupOutputData.isUseCaseFailed(), "useCaseFailed should match the value provided in the constructor");
    }

    @Test
    void testConstructorWithUseCaseFailedTrue() {
        // Arrange
        String username = "testUser";
        boolean useCaseFailed = true;

        // Act
        SignupOutputData signupOutputData = new SignupOutputData(username, useCaseFailed);

        // Assert
        assertEquals(username, signupOutputData.getUsername(), "Username should match the value provided in the constructor");
        assertTrue(signupOutputData.isUseCaseFailed(), "useCaseFailed should match the value provided in the constructor");
    }

    @Test
    void testConstructorWithNullUsername() {
        // Arrange
        String username = null;
        boolean useCaseFailed = false;

        // Act
        SignupOutputData signupOutputData = new SignupOutputData(username, useCaseFailed);

        // Assert
        assertNull(signupOutputData.getUsername(), "Username should be null when null is provided in the constructor");
        assertFalse(signupOutputData.isUseCaseFailed(), "useCaseFailed should match the value provided in the constructor");
    }
}
