package interface_adapter.write_review;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.write_review.WriteReviewInputBoundary;
import use_case.write_review.WriteReviewInputData;

/**
 * The controller for the Login Use Case.
 */
public class WriteReviewController {

    private final WriteReviewInputBoundary writeReviewUseCaseInteractor;

    public WriteReviewController(WriteReviewInputBoundary writeReviewInteractor) {
        this.writeReviewUseCaseInteractor = writeReviewInteractor;
    }

    /**
     * Executes the Write Review Use Case.
     * @param username the username of the user logging in
     */
    public void execute(String username) {
        final WriteReviewInputData writeReviewInputData = new WriteReviewInputData(username);

        writeReviewUseCaseInteractor.execute(writeReviewInputData);
    }
}