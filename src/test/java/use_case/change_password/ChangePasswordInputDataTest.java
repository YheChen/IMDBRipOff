package use_case.change_password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangePasswordInputDataTest {

    @Test
    void testChangePasswordInputData() {
        // Arrange
        String expectedPassword = "newPassword123";
        String expectedUsername = "testUser";

        // Act
        ChangePasswordInputData inputData = new ChangePasswordInputData(expectedPassword, expectedUsername);

        // Assert
        assertEquals(expectedPassword, inputData.getPassword());
        assertEquals(expectedUsername, inputData.getUsername());
    }
}
