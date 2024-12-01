package use_case.write_review;

import java.util.Date;

/**
 * The Input Data for the Write Review Case.
 */
public class WriteReviewInputData {
    private final String userId;
    private final String content;
    private final int rating;
    private final String media;
    // add the date here?
    private final Date date;

    public WriteReviewInputData(String userId, String content, int rating, String media) {

        this.userId = userId;
        this.content = content;
        this.rating = rating;
        this.media = media;
        this.date = new Date();

    }

    String getUserID() {
        return userId;
    }

    String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }

    public String getMedia() {
        return media;
    }

    public Date getDate() {
        return date;
    }
}
