package use_case.write_review;

/**
 * The Input Data for the Write Review Case.
 */
public class WriteReviewInputData {
    private final String username;

    public WriteReviewInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }
}
