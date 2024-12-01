package use_case.write_review;

import entity.User;
import entity.Review;
import use_case.signup.SignupOutputData;
import use_case.signup.SignupUserDataAccessInterface;

// Pull request for pull review
/**
 * The Signup Interactor.
 */
// Code Review
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
        final Review review = new Review(writeReviewInputData.getUserID(),
                writeReviewInputData.getMedia(), writeReviewInputData.getContent(), writeReviewInputData.getRating(),
                writeReviewInputData.getDate());
        reviewDataAccessObject.save(review);

        final WriteReviewOutputData writeReviewOutputData =
                new WriteReviewOutputData(review.getUserID(), review.getContent(),
                        review.getRating(), review.getMediaID(), review.getDateCreated());
        userPresenter.prepareSuccessView(writeReviewOutputData);
    }

    public void switchToWriteView() {
        userPresenter.switchToWriteView();
    }

    public void switchToAccountView() {
        userPresenter.switchToAccountView();
    }

    public void switchToBrowseView() {
        userPresenter.switchToBrowseView();
    }
}
