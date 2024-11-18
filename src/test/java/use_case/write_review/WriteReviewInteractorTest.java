package use_case.write_review;

import data_access.InMemoryReviewDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import entity.Review;
import org.junit.jupiter.api.Test;
import use_case.login.LoginUserDataAccessInterface;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WriteReviewInteractorTest {

    @Test
    void successTest() {


        // Create input Data
        WriteReviewInputData writeReviewInputData = new WriteReviewInputData("Test_user_x",
                "hello so yeah i really like this move idk why", 5,
                "Harry Potter and the Philosopher's Stone");

        WriteReviewDataAccessInterface userRepository = new InMemoryReviewDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        WriteReviewOutputBoundary successPresenter = new WriteReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(WriteReviewOutputData review) {
                assertEquals(5, review.getRating()); // Check if the rating is correct
                assertEquals("hello so yeah i really like this move idk why", review.getContent());
                // Check if the review content is correct
                assertEquals("Harry Potter and the Philosopher's Stone" ,review.getMedia());
                // Check if the movie/media is correct lol
                assertEquals("Test_user_x", review.getUsername());
            }
        };
    }
}