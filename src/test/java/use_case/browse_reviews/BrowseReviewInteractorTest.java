package use_case.browse_reviews;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    /**
     * A mock implementation of `BrowseReviewOutputBoundary`.
     * Captures the output data for verification.
     */
    private static class MockBrowseReviewOutputBoundary implements BrowseReviewOutputBoundary {
        private BrowseReviewOutputData outputData;

        @Override
        public void prepareBrowseView(BrowseReviewOutputData outputData) {
            this.outputData = outputData;
        }

        @Override
        public void switchToWriteView() {
            // Not used in this test
        }

        @Override
        public void switchToAccountView() {
            // Not used in this test
        }

        @Override
        public void switchToBrowseView() {
            // Not used in this test
        }

        public BrowseReviewOutputData getOutputData() {
            return outputData;
        }
    }
}
