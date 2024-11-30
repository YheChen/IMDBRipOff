package use_case.login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginInputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String expectedUsername = "testUser";
        String expectedPassword = "testPassword";

        // Act
        LoginInputData loginInputData = new LoginInputData(expectedUsername, expectedPassword);

        // Assert
        assertEquals(expectedUsername, loginInputData.getUsername());
        assertEquals(expectedPassword, loginInputData.getPassword());
    }

    @Test
    void testEmptyUsernameAndPassword() {
        // Arrange
        String emptyUsername = "";
        String emptyPassword = "";

        // Act
        LoginInputData loginInputData = new LoginInputData(emptyUsername, emptyPassword);

        // Assert
        assertEquals(emptyUsername, loginInputData.getUsername());
        assertEquals(emptyPassword, loginInputData.getPassword());
    }

    @Test
    void testNullUsernameAndPassword() {
        // Arrange
        String nullUsername = null;
        String nullPassword = null;

        // Act
        LoginInputData loginInputData = new LoginInputData(nullUsername, nullPassword);

        // Assert
        assertNull(loginInputData.getUsername());
        assertNull(loginInputData.getPassword());
    }
}
