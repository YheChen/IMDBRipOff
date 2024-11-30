package use_case.signup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupInputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String username = "testUser";
        String password = "securePassword";
        String repeatPassword = "securePassword";

        // Act
        SignupInputData signupInputData = new SignupInputData(username, password, repeatPassword);

        // Assert
        assertEquals(username, signupInputData.getUsername(), "Username should match the value provided in the constructor");
        assertEquals(password, signupInputData.getPassword(), "Password should match the value provided in the constructor");
        assertEquals(repeatPassword, signupInputData.getRepeatPassword(), "RepeatPassword should match the value provided in the constructor");
    }

    @Test
    void testConstructorWithNullValues() {
        // Arrange
        String username = null;
        String password = null;
        String repeatPassword = null;

        // Act
        SignupInputData signupInputData = new SignupInputData(username, password, repeatPassword);

        // Assert
        assertNull(signupInputData.getUsername(), "Username should be null when null is provided in the constructor");
        assertNull(signupInputData.getPassword(), "Password should be null when null is provided in the constructor");
        assertNull(signupInputData.getRepeatPassword(), "RepeatPassword should be null when null is provided in the constructor");
    }

    @Test
    void testConstructorWithEmptyStrings() {
        // Arrange
        String username = "";
        String password = "";
        String repeatPassword = "";

        // Act
        SignupInputData signupInputData = new SignupInputData(username, password, repeatPassword);

        // Assert
        assertEquals(username, signupInputData.getUsername(), "Username should match the empty string provided in the constructor");
        assertEquals(password, signupInputData.getPassword(), "Password should match the empty string provided in the constructor");
        assertEquals(repeatPassword, signupInputData.getRepeatPassword(), "RepeatPassword should match the empty string provided in the constructor");
    }
}
