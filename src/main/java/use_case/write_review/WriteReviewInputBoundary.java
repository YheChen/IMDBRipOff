package use_case.write_review;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface WriteReviewInputBoundary {

    /**
     * Executes the write review use case.
     */
    void execute(WriteReviewInputData writeReviewInputData);
}
