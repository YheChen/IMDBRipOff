package interface_adapter.write_review;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class WriteReviewViewModel extends ViewModel<WriteReviewState> {

    public WriteReviewViewModel() {
        super("write review");
        setState(new WriteReviewState());
    }

}
