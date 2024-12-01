package interface_adapter.browse_review;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class BrowseReviewViewModel extends ViewModel<BrowseReviewState> {

    public BrowseReviewViewModel() {
        super("browse reviews");
        setState(new BrowseReviewState());
    }

}
