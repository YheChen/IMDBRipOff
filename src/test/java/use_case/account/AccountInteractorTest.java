package use_case.account;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountViewModel;
import interface_adapter.browse_review.BrowseReviewViewModel;
import interface_adapter.write_review.WriteReviewViewModel;
import org.junit.jupiter.api.Test;
import use_case.write_review.WriteReviewInputBoundary;
import use_case.write_review.WriteReviewInteractor;
import use_case.write_review.WriteReviewOutputBoundary;
import use_case.write_review.WriteReviewOutputData;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountInteractorTest {

    @Test
    void successTest() {
        AccountOutputBoundary successPresenter = new AccountOutputBoundary() {
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

        AccountInputBoundary boundary = new AccountInteractor(successPresenter);

        // Tests changes in state
        boundary.switchToAccountView();
        AccountViewModel accountViewModel = new AccountViewModel();
        assertEquals("account", accountViewModel.getViewName());

        boundary.switchToBrowseView();
        BrowseReviewViewModel browseReviewViewModel = new BrowseReviewViewModel();
        assertEquals("browse reviews", browseReviewViewModel.getViewName());

        boundary.switchToWriteView();
        WriteReviewViewModel writeReviewViewModel = new WriteReviewViewModel();
        assertEquals("write review", writeReviewViewModel.getViewName());
    }
}
