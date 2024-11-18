package interface_adapter.write_review;

import interface_adapter.ViewManagerModel;
import interface_adapter.browse_review.BrowseReviewViewModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.browse_review.BrowseReviewState;
import use_case.write_review.WriteReviewOutputData;
import use_case.write_review.WriteReviewOutputBoundary;

public class WriteReviewPresenter implements WriteReviewOutputBoundary {
    private final BrowseReviewViewModel browseReviewViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public WriteReviewPresenter(BrowseReviewViewModel writeReviewViewModel,
                          LoggedInViewModel loggedInViewModel,
                          ViewManagerModel viewManagerModel) {
        this.browseReviewViewModel =  writeReviewViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(WriteReviewOutputData writeReviewOutputData) {
        // On success, switch to the logged in view.


        final BrowseReviewState browseReviewState = browseReviewViewModel.getState();
        this.browseReviewViewModel.setState(browseReviewState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setState(browseReviewViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
