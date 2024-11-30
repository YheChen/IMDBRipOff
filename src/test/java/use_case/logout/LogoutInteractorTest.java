package use_case.logout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogoutInteractorTest {

    private LogoutInteractor logoutInteractor;
    private MockLogoutUserDataAccess mockUserDataAccess;
    private MockLogoutOutputBoundary mockOutputBoundary;

    @BeforeEach
    void setUp() {
        mockUserDataAccess = new MockLogoutUserDataAccess();
        mockOutputBoundary = new MockLogoutOutputBoundary();
        logoutInteractor = new LogoutInteractor(mockUserDataAccess, mockOutputBoundary);
    }

    @Test
    void testExecuteSuccessfulLogout() {
        // Arrange
        String currentUsername = "testUser";
        mockUserDataAccess.setCurrentUsername(currentUsername);
        LogoutInputData inputData = new LogoutInputData(currentUsername);

        // Act
        logoutInteractor.execute(inputData);

        // Assert
        // Check that the current username is set to null
        assertNull(mockUserDataAccess.getCurrentUsername(), "Current username should be null after logout");

        // Verify that the output data contains the correct username
        LogoutOutputData outputData = mockOutputBoundary.getOutputData();
        assertNotNull(outputData, "Output data should not be null");
        assertEquals(currentUsername, outputData.getUsername(), "Output data should contain the correct username");
        assertFalse(outputData.isUseCaseFailed(), "Use case should indicate success");
    }

    @Test
    void testExecuteWithNullUsername() {
        // Arrange
        mockUserDataAccess.setCurrentUsername("testUser");
        LogoutInputData inputData = new LogoutInputData(null);

        // Act
        logoutInteractor.execute(inputData);

        // Assert
        // Check that the current username is set to null
        assertNull(mockUserDataAccess.getCurrentUsername(), "Current username should be null after logout");

        // Verify that the output data contains null username
        LogoutOutputData outputData = mockOutputBoundary.getOutputData();
        assertNotNull(outputData, "Output data should not be null");
        assertNull(outputData.getUsername(), "Output data should allow null username");
        assertFalse(outputData.isUseCaseFailed(), "Use case should indicate success");
    }
}
