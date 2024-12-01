package interface_adapter.write_review;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountViewModel;
import interface_adapter.browse_review.BrowseReviewState;
import interface_adapter.browse_review.BrowseReviewViewModel;
import use_case.write_review.WriteReviewOutputBoundary;
import use_case.write_review.WriteReviewOutputData;

/**
 * Presenter for write review use case.
 */
public class WriteReviewPresenter implements WriteReviewOutputBoundary {
    private final BrowseReviewViewModel browseReviewViewModel;
    private final AccountViewModel accountViewModel;
    private final WriteReviewViewModel writeReviewViewModel;
    private final ViewManagerModel viewManagerModel;

    public WriteReviewPresenter(BrowseReviewViewModel browseReviewViewModel,
                          AccountViewModel accountViewModel,
                                WriteReviewViewModel writeReviewViewModel,
                          ViewManagerModel viewManagerModel) {
        this.browseReviewViewModel = browseReviewViewModel;
        this.accountViewModel = accountViewModel;
        this.writeReviewViewModel = writeReviewViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(WriteReviewOutputData writeReviewOutputData) {
        // On success, switch to the logged in view.

        final BrowseReviewState browseReviewState = browseReviewViewModel.getState();
        this.browseReviewViewModel.setState(browseReviewState);

        this.viewManagerModel.setState(browseReviewViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToWriteView() {
        viewManagerModel.setState(writeReviewViewModel.getViewName());
        System.out.println(writeReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToAccountView() {
        viewManagerModel.setState(accountViewModel.getViewName());
        System.out.println(accountViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToBrowseView() {
        viewManagerModel.setState(browseReviewViewModel.getViewName());
        System.out.println(browseReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
