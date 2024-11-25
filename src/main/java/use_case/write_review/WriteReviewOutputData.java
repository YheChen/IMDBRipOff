package use_case.write_review;

import java.util.Date;

/**
 * The output data for the write review use case.
 */
public class WriteReviewOutputData {
    private final String user_id;
    private final String content;
    private final int rating;
    private final String media;
    // add the date here?
    private final Date date;
    private boolean success;

    public WriteReviewOutputData(String user_id, String content, int rating, String media, Date date,
                                 Boolean success){
        this.user_id = user_id;
        this.content = content;
        this.rating = rating;
        this.media = media;
        this.date = date;
    }

    public boolean isSuccess() {
        return success;
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
        return user_id;
    }
}
