package use_case.login;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {

    private LoginInteractor loginInteractor;
    private TestLoginUserDataAccess userDataAccess;
    private TestLoginOutputBoundary outputBoundary;

    @BeforeEach
    void setUp() {
        userDataAccess = new TestLoginUserDataAccess();
        outputBoundary = new TestLoginOutputBoundary();
        loginInteractor = new LoginInteractor(userDataAccess, outputBoundary);
    }

    @Test
    void testExecute_SuccessfulLogin() {
        // Arrange
        userDataAccess.addUser(new User("testUser", "password123"));
        LoginInputData inputData = new LoginInputData("testUser", "password123");

        // Act
        loginInteractor.execute(inputData);

        // Assert
        assertNotNull(outputBoundary.getSuccessOutput());
        assertEquals("testUser", outputBoundary.getSuccessOutput().getUsername());
        assertNull(outputBoundary.getFailureMessage());
        assertEquals("testUser", userDataAccess.getCurrentUsername());
    }

    @Test
    void testExecute_AccountDoesNotExist() {
        // Arrange
        LoginInputData inputData = new LoginInputData("nonExistentUser", "password123");

        // Act
        loginInteractor.execute(inputData);

        // Assert
        assertNull(outputBoundary.getSuccessOutput());
        assertEquals("nonExistentUser: Account does not exist.", outputBoundary.getFailureMessage());
    }

    @Test
    void testExecute_IncorrectPassword() {
        // Arrange
        userDataAccess.addUser(new User("testUser", "password123"));
        LoginInputData inputData = new LoginInputData("testUser", "wrongPassword");

        // Act
        loginInteractor.execute(inputData);

        // Assert
        assertNull(outputBoundary.getSuccessOutput());
        assertEquals("Incorrect password for \"testUser\".", outputBoundary.getFailureMessage());
    }
}
