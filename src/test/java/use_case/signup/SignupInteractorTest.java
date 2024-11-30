package use_case.signup;

import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {

    private SignupInteractor signupInteractor;
    private MockSignupUserDataAccess mockDataAccess;
    private MockSignupOutputBoundary mockPresenter;
    private UserFactory userFactory;

    @BeforeEach
    void setUp() {
        mockDataAccess = new MockSignupUserDataAccess();
        mockPresenter = new MockSignupOutputBoundary();
        userFactory = new UserFactory() {
            @Override
            public User create(String name, String password) {
                return new User(name, password); // Ensure this creates a valid User object
            }
        };
        signupInteractor = new SignupInteractor(mockDataAccess, mockPresenter, userFactory);
    }

    @Test
    void testSuccessfulSignup() {
        // Arrange
        String username = "newUser";
        String password = "password123";
        SignupInputData inputData = new SignupInputData(username, password, password);

        // Act
        signupInteractor.execute(inputData);

        // Assert
        assertTrue(mockDataAccess.existsByName(username));
        User savedUser = mockDataAccess.getUser(username);
        assertNotNull(savedUser); // Ensure the user was saved
        assertEquals(username, savedUser.getUsername());
        assertEquals(username, mockPresenter.getSuccessData().getUsername());
        assertFalse(mockPresenter.getSuccessData().isUseCaseFailed());
    }

    @Test
    void testSignupWithExistingUser() {
        // Arrange
        String username = "existingUser";
        String password = "password123";
        mockDataAccess.save(new User(username, password));
        SignupInputData inputData = new SignupInputData(username, password, password);

        // Act
        signupInteractor.execute(inputData);

        // Assert
        assertEquals("User already exists.", mockPresenter.getErrorMessage());
    }

    @Test
    void testSignupWithNonMatchingPasswords() {
        // Arrange
        String username = "newUser";
        String password = "password123";
        String repeatPassword = "password321";
        SignupInputData inputData = new SignupInputData(username, password, repeatPassword);

        // Act
        signupInteractor.execute(inputData);

        // Assert
        assertEquals("Passwords don't match.", mockPresenter.getErrorMessage());
    }

    @Test
    void testSwitchToLoginView() {
        // Act
        signupInteractor.switchToLoginView();

        // Assert
        assertTrue(mockPresenter.isSwitchedToLoginView());
    }
}
