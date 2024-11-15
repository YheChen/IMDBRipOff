package use_case.write_review;

import entity.User;
import entity.Review;
import use_case.signup.SignupOutputData;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The Signup Interactor.
 */
public class WriteReviewInteractor implements WriteReviewInputBoundary {
    private final WriteReviewDataAccessInterface reviewDataAccessObject;
    private final WriteReviewOutputBoundary userPresenter;

    public WriteReviewInteractor(WriteReviewDataAccessInterface signupDataAccessInterface,
                            WriteReviewOutputBoundary userPresenter) {
        this.reviewDataAccessObject = signupDataAccessInterface;
        this.userPresenter = userPresenter;
    }


    @Override
    public void execute(WriteReviewInputData writeReviewInputData) {
        final Review review = new Review(writeReviewInputData.getContent(),
                writeReviewInputData.getMedia(), writeReviewInputData.getUsername(), writeReviewInputData.getMedia(),
                writeReviewInputData.getRating(), writeReviewInputData.getDate());
        reviewDataAccessObject.save(review);
        final WriteReviewOutputData writeReviewOutputData = new SignupOutputData(user.getUsername(), false);

    }
}
