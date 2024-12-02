package interface_adapter.write_review;

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
     * @param content the content of the review (optional)
     * @param rating the rating of the review out of 5
     * @param media the media being reviewed
     */
    public void execute(String username, String title, String content, int rating, String media) {
        final WriteReviewInputData writeReviewInputData = new WriteReviewInputData(username, title, content, rating, media);

        writeReviewUseCaseInteractor.execute(writeReviewInputData);
    }

    /**
     * Switches to write view.
     */
    public void switchToWriteView() {
        writeReviewUseCaseInteractor.switchToWriteView();
    }

    /**
     * Switches to account view.
     */
    public void switchToAccountView() {
        writeReviewUseCaseInteractor.switchToAccountView();
    }

    /**
     * Switches to browse view.
     */
    public void switchToBrowseView() {
        writeReviewUseCaseInteractor.switchToBrowseView();
    }
}
