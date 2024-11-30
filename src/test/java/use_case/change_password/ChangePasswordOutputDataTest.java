package use_case.change_password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChangePasswordOutputDataTest {

    @Test
    void testChangePasswordOutputData_Success() {
        // Arrange
        String expectedUsername = "testUser";
        boolean expectedUseCaseFailed = false;

        // Act
        ChangePasswordOutputData outputData = new ChangePasswordOutputData(expectedUsername, expectedUseCaseFailed);

        // Assert
        assertEquals(expectedUsername, outputData.getUsername());
        assertFalse(outputData.isUseCaseFailed());
    }

    @Test
    void testChangePasswordOutputData_Failure() {
        // Arrange
        String expectedUsername = "testUser";
        boolean expectedUseCaseFailed = true;

        // Act
        ChangePasswordOutputData outputData = new ChangePasswordOutputData(expectedUsername, expectedUseCaseFailed);

        // Assert
        assertEquals(expectedUsername, outputData.getUsername());
        assertTrue(outputData.isUseCaseFailed());
    }
}
