package use_case.browse_reviews;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowseReviewInteractorTest {

    @Test
    void testExecute() {
        // Arrange: Mock Output Boundary
        MockBrowseReviewOutputBoundary mockOutputBoundary = new MockBrowseReviewOutputBoundary();
        BrowseReviewInteractor interactor = new BrowseReviewInteractor(mockOutputBoundary);

        // Input Data
        String orderBy = "date";
        String searchText = "Harry Potter";
        BrowseReviewInputData inputData = new BrowseReviewInputData(orderBy, searchText);

        // Act: Execute the interactor
        interactor.execute(inputData);

        // Assert: Verify that the output data matches the input
        BrowseReviewOutputData outputData = mockOutputBoundary.getOutputData();
        assertEquals(orderBy, outputData.getOrderBy());
        assertEquals(searchText, outputData.getSearchText());
    }

    @Test
    void testSwitchToWriteView() {
        // Arrange
        MockBrowseReviewOutputBoundary mockOutputBoundary = new MockBrowseReviewOutputBoundary();
        BrowseReviewInteractor interactor = new BrowseReviewInteractor(mockOutputBoundary);

        // Act
        interactor.switchToWriteView();

        // Assert
        assertTrue(mockOutputBoundary.isSwitchedToWriteView());
    }

    @Test
    void testSwitchToAccountView() {
        // Arrange
        MockBrowseReviewOutputBoundary mockOutputBoundary = new MockBrowseReviewOutputBoundary();
        BrowseReviewInteractor interactor = new BrowseReviewInteractor(mockOutputBoundary);

        // Act
        interactor.switchToAccountView();

        // Assert
        assertTrue(mockOutputBoundary.isSwitchedToAccountView());
    }

    @Test
    void testSwitchToBrowseView() {
        // Arrange
        MockBrowseReviewOutputBoundary mockOutputBoundary = new MockBrowseReviewOutputBoundary();
        BrowseReviewInteractor interactor = new BrowseReviewInteractor(mockOutputBoundary);

        // Act
        interactor.switchToBrowseView();

        // Assert
        assertTrue(mockOutputBoundary.isSwitchedToBrowseView());
    }

    /**
     * A mock implementation of `BrowseReviewOutputBoundary`.
     * Captures the output data for verification and tracks view switches.
     */
    private static class MockBrowseReviewOutputBoundary implements BrowseReviewOutputBoundary {
        private BrowseReviewOutputData outputData;
        private boolean switchedToWriteView = false;
        private boolean switchedToAccountView = false;
        private boolean switchedToBrowseView = false;

        @Override
        public void prepareBrowseView(BrowseReviewOutputData outputData) {
            this.outputData = outputData;
        }

        @Override
        public void switchToWriteView() {
            switchedToWriteView = true;
        }

        @Override
        public void switchToAccountView() {
            switchedToAccountView = true;
        }

        @Override
        public void switchToBrowseView() {
            switchedToBrowseView = true;
        }

        public BrowseReviewOutputData getOutputData() {
            return outputData;
        }

        public boolean isSwitchedToWriteView() {
            return switchedToWriteView;
        }

        public boolean isSwitchedToAccountView() {
            return switchedToAccountView;
        }

        public boolean isSwitchedToBrowseView() {
            return switchedToBrowseView;
        }
    }
}
