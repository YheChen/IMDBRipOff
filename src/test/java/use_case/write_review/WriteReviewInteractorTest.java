package use_case.write_review;

import data_access.InMemoryReviewDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import entity.Review;
import org.junit.jupiter.api.Test;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginUserDataAccessInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WriteReviewInteractorTest {

    @Test
    void successTest() {


        // Create input Data
        WriteReviewInputData writeReviewInputData = new WriteReviewInputData("30ENX3",
                "hello so yeah i really like this move idk why", 5,
                "Harry Potter and the Philosopher's Stone");
        InMemoryReviewDataAccessObject reviewMemObj = new InMemoryReviewDataAccessObject();
        Review review = new Review("94xdj", "4o3me",
                "hello so yeah i really like this move idk why", 5, LocalDate.now());
        reviewMemObj.save(review);

        WriteReviewDataAccessInterface userRepository = new InMemoryReviewDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        WriteReviewOutputBoundary successPresenter = new WriteReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(WriteReviewOutputData writeReviewOutputData) {
                assertEquals(5, reviewMemObj.getReview(review.getReviewID()).getRating()); // Check if the rating is correct
                assertEquals("hello so yeah i really like this move idk why",
                        reviewMemObj.getReview(review.getReviewID()).getContent());
                // Check if the review content is correct
                assertEquals("Harry Potter and the Philosopher's Stone",
                        reviewMemObj.getReview(review.getMediaID()).getContent());
                // Check if the movie/media is correct lo
            }
        };
    }
}