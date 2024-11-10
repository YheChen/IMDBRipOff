package use_case.write_review;

import entity.User;
import interface_adapter.write_review.WriteReviewPresenter;
import use_case.login.*;

/**
 * The Login Interactor.
 */
public class WriteReviewInteractor implements WriteReviewInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final WriteReviewPresenter writeReviewPresenter;

    public WriteReviewInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           WriteReviewPresenter writeReviewPresenter) {
        this.userDataAccessObject = userDataAccessInterface;
        this.writeReviewPresenter = writeReviewPresenter;
    }

    @Override
    public void execute(WriteReviewInputData writeReviewInputData){
        final String username = writeReviewInputData.getUsername();
        if (!userDataAccessObject.existsByName(username)) {
            // loginPresenter.prepareFailView(username + ": Account does not exist."); // Eventually this will
            // change to WriteReviewPresenter
        }
        else {

            // final User user = userDataAccessObject.get(WriteReviewInputData.getUsername());
           //  final WriteReviewOutputData writeReviewOutputData = new WriteReviewOutputData()

            // userDataAccessObject.setCurrentUsername(user.getUsername());
            // final LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), false);
            // loginPresenter.prepareSuccessView(loginOutputData); // change to view presenter
        }
    }
}
