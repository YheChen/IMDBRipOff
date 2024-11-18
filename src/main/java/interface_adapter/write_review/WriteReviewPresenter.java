package interface_adapter.write_review;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.write_review.WriteReviewOutputData;
import use_case.write_review.WriteReviewOutputBoundary;

public class WriteReviewPresenter implements WriteReviewOutputBoundary {
    private final WriteReviewViewModel writeReviewViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public WriteReviewPresenter(WriteReviewViewModel writeReviewViewModel,
                          LoggedInViewModel loggedInViewModel,
                          ViewManagerModel viewManagerModel) {
        this.writeReviewViewModel =  writeReviewViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(WriteReviewOutputData writeReviewOutputData) {
        // On success, switch to the logged in view.

        final WriteReviewState writeReviewState = writeReviewViewModel.getState();
        this.writeReviewViewModel.setState(writeReviewState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setState(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
