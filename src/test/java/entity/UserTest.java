package entity;
import entity.User;

import data_access.InMemoryReviewDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import entity.Review;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    void UserWorksAsExpectedTest(){
        String username = "Jonathan";
        String password = "redacted";
        String userID = "jo7rt";

        // Checks that the Review class functions as expected
        User new_user = new User(username, password);
        User current_user = new User(userID, username, password);

        assertEquals("Jonathan", current_user.getUsername());
        assertEquals("redacted", current_user.getPassword());
        assertEquals("jo7rt", current_user.getId());
        assertEquals("Jonathan", new_user.getUsername());
        assertEquals("redacted", new_user.getPassword());
        assertNotEquals("jo7rt", new_user.getId());
    }
}
