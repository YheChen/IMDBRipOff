package use_case.change_password;

import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangePasswordInteractorTest {

    private ChangePasswordInteractor interactor;
    private MockChangePasswordUserDataAccess mockDataAccess;
    private MockChangePasswordOutputBoundary mockPresenter;
    private UserFactory userFactory;

    @BeforeEach
    void setUp() {
        mockDataAccess = new MockChangePasswordUserDataAccess();
        mockPresenter = new MockChangePasswordOutputBoundary();
        userFactory = new UserFactory() {
            @Override
            public User create(String name, String password) {
                return new User(name, password); // Return a simple User object
            }
        };
        interactor = new ChangePasswordInteractor(mockDataAccess, mockPresenter, userFactory);
    }

    @Test
    void testChangePasswordSuccess() {
        // Arrange
        String username = "testUser";
        String newPassword = "newPassword123";
        ChangePasswordInputData inputData = new ChangePasswordInputData(newPassword, username);

        // Act
        interactor.execute(inputData);

        // Assert
        User updatedUser = mockDataAccess.getLastChangedUser();
        assertEquals(username, updatedUser.getUsername());
        assertEquals(newPassword, updatedUser.getPassword());
        assertEquals(username, mockPresenter.getSuccessOutputData().getUsername());
        assertEquals(false, mockPresenter.getSuccessOutputData().isUseCaseFailed());
    }
}
