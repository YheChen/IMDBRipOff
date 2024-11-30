package use_case.write_review;

import data_access.InMemoryReviewDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import entity.Review;
import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountViewModel;
import interface_adapter.browse_review.BrowseReviewViewModel;
import interface_adapter.write_review.WriteReviewViewModel;
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
                assertEquals("hello so yeah i really like this move idk why", writeReviewOutputData.getContent());
                assertEquals(5, writeReviewOutputData.getRating());
                assertEquals("Harry Potter and the Philosopher's Stone", writeReviewOutputData.getMedia());
                assertEquals("30ENX3", writeReviewOutputData.getUserID());
                assertEquals(new Date().toString(), writeReviewOutputData.getDate().toString());
            }

            @Override
            public void switchToWriteView() {
                         }

            @Override
            public void switchToAccountView() {

            }

            @Override
            public void switchToBrowseView() {
            }
        };

        WriteReviewInputBoundary boundary = new WriteReviewInteractor(reviewMemObj, successPresenter);

        // Tests changes in state
        boundary.execute(writeReviewInputData);
        boundary.switchToAccountView();
        AccountViewModel accountViewModel = new AccountViewModel();
        assertEquals( "account", accountViewModel.getViewName());

        boundary.switchToBrowseView();
        BrowseReviewViewModel browseReviewViewModel = new BrowseReviewViewModel();
        assertEquals( "browse reviews", browseReviewViewModel.getViewName());

        boundary.switchToWriteView();
        WriteReviewViewModel writeReviewViewModel = new WriteReviewViewModel();
        assertEquals( "write review", writeReviewViewModel.getViewName());



//        boundary.switchToBrowseView();
//        assertEquals("browse reviews", viewManagerModel.getViewName());
//
//        boundary.switchToWriteView();
//        assertEquals("write reviews", viewManagerModel.getViewName());



    }
}