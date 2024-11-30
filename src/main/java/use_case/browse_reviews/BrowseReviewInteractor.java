package use_case.browse_reviews;

import entity.User;
import interface_adapter.browse_review.BrowseReviewPresenter;
import use_case.login.*;


/**
 * The Login Interactor.
 */
public class BrowseReviewInteractor implements BrowseReviewInputBoundary {
    // private final LoginUserDataAccessInterface userDataAccessObject;
    private final BrowseReviewOutputBoundary browseReviewPresenter;

    public BrowseReviewInteractor(//LoginUserDataAccessInterface userDataAccessInterface,
                                  BrowseReviewOutputBoundary browseReviewPresenter) {
        //this.userDataAccessObject = userDataAccessInterface;
        this.browseReviewPresenter = browseReviewPresenter;
    }

    @Override
    public void execute(BrowseReviewInputData browseReviewInputData) {
        String orderBy = browseReviewInputData.getOrderBy();
        String searchText = browseReviewInputData.getSearchText();
        final BrowseReviewOutputData outputData = new BrowseReviewOutputData(orderBy, searchText);
        browseReviewPresenter.prepareBrowseView(outputData);
    }
//     execute method might not be needed for interactor because no output

//    @Override
//    public void execute(BrowseReviewInputData browseReviewInputData){
//        final String username = browseReviewInputData.getUsername();
//        if (!userDataAccessObject.existsByName(username)) {
//            // loginPresenter.prepareFailView(username + ": Account does not exist."); // Eventually this will
//            // change to WriteReviewPresenter
//        }
//        else {
//
//             final User user = userDataAccessObject.get(WriteReviewInputData.getUsername());
//              final WriteReviewOutputData writeReviewOutputData = new WriteReviewOutputData()
//
//             userDataAccessObject.setCurrentUsername(user.getUsername());
//             final LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), false);
//             loginPresenter.prepareSuccessView(loginOutputData); // change to view presenter
//        }

    public void switchToWriteView() {
        browseReviewPresenter.switchToWriteView();
    }

    public void switchToAccountView() {
        browseReviewPresenter.switchToAccountView();
    }

    public void switchToBrowseView() {
        browseReviewPresenter.switchToBrowseView();
    }

}
