package use_case.write_review;

import data_access.InMemoryReviewDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import entity.Review;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginUserDataAccessInterface;

import java.util.Date;

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
                "hello so yeah i really like this move idk why", 5, new Date());
        reviewMemObj.save(review);

        // This creates a successPresenter that tests whether the test case is as we expect.
        WriteReviewOutputBoundary successPresenter = new WriteReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(WriteReviewOutputData writeReviewOutputData) {
                assertEquals(5, reviewMemObj.get(review.getReviewID()).getRating()); // Check if the rating is correct
                assertEquals("hello so yeah i really like this move idk why",
                        reviewMemObj.get(review.getReviewID()).getContent());
                // Check if the review content is correct
                assertEquals("4o3me",
                        reviewMemObj.get(review.getReviewID()).getMediaID());
                // Check if the movie/media is correct lo
            }

            @Override
            public void switchToWriteView() {
                ViewManagerModel viewManagerModel = new ViewManagerModel();
                assertEquals(viewManagerModel.getViewName(), "write reviews");
            }

            @Override
            public void switchToAccountView() {
                ViewManagerModel viewManagerModel = new ViewManagerModel();
                assertEquals(viewManagerModel.getViewName(), "account");
            }

            @Override
            public void switchToBrowseView() {
                ViewManagerModel viewManagerModel = new ViewManagerModel();
                assertEquals(viewManagerModel.getViewName(), "browse reviews");
            }
        };

        WriteReviewInputBoundary interactor = new WriteReviewInteractor(reviewMemObj, successPresenter);
        interactor.execute(writeReviewInputData);


    }
}