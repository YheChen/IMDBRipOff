package use_case.browse_reviews;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import use_case.write_review.WriteReviewOutputBoundary;
import use_case.write_review.WriteReviewOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowseReviewInteractorTest {

    @Test
    void successTest() {

        BrowseReviewOutputBoundary successPresenter = new BrowseReviewOutputBoundary() {

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
    }
}
