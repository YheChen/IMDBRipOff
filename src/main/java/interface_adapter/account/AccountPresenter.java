package interface_adapter.account;

import interface_adapter.ViewManagerModel;
import interface_adapter.browse_review.BrowseReviewViewModel;
import interface_adapter.write_review.WriteReviewViewModel;
import use_case.account.AccountOutputBoundary;

/**
 * Presenter for Account view.
 */
public class AccountPresenter implements AccountOutputBoundary {
    private final BrowseReviewViewModel browseReviewViewModel;
    private final WriteReviewViewModel writeReviewViewModel;
    private final AccountViewModel accountViewModel;
    private final ViewManagerModel viewManagerModel;

    public AccountPresenter(BrowseReviewViewModel browseReviewViewModel,
                                 WriteReviewViewModel writeReviewViewModel,
                                 AccountViewModel accountViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.browseReviewViewModel = browseReviewViewModel;
        this.writeReviewViewModel = writeReviewViewModel;
        this.accountViewModel = accountViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Switches to write view.
     */
    public void switchToWriteView() {
        viewManagerModel.setState(writeReviewViewModel.getViewName());
        System.out.println(writeReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches to account view.
     */
    public void switchToAccountView() {
        viewManagerModel.setState(accountViewModel.getViewName());
        System.out.println(accountViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches to browse view.
     */
    public void switchToBrowseView() {
        viewManagerModel.setState(browseReviewViewModel.getViewName());
        System.out.println(browseReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
