package use_case.write_review;

import java.util.Date;

/**
 * The output data for the write review use case.
 */
public class WriteReviewOutputData {
    private final String userId;
    private final String content;
    private final int rating;
    private final String media;
    // add the date here?
    private final Date date;

    public WriteReviewOutputData(String userId, String content, int rating, String media, Date date) {
        this.userId = userId;
        this.content = content;
        this.rating = rating;
        this.media = media;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public int getRating() {
        return rating;
    }

    public String getMedia() {
        return media;
    }

    public String getUserID() {
        return userId;
    }
}
