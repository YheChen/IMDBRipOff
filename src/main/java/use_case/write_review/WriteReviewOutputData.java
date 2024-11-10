package use_case.write_review;

/**
 * The output data for the write review use case.
 */
public class WriteReviewOutputData {
    private final String username;
    private final String userID;
    private final int rating;
    private final String content;
    private final String mediaID;

    public WriteReviewOutputData(String username, String userID, int rating, String content, String mediaID){
        this.username = username;
        this.userID = userID;
        this.rating = rating;
        this.content = content;
        this.mediaID = mediaID;
    }

    public String getUsername() {
        return username;
    }

    public String getUserID() {
        return userID;
    }

    public int getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public String getMediaID() {
        return mediaID;
    }
}
