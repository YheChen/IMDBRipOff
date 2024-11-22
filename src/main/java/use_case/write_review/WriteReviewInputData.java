package use_case.write_review;

import java.time.LocalDate;

/**
 * The Input Data for the Write Review Case.
 */
public class WriteReviewInputData {
    private final String user_id;
    private final String content;
    private final int rating;
    private final String media;
    // add the date here?
    private final LocalDate date;

    public WriteReviewInputData(String user_id, String content, int rating, String media) {

        this.user_id = user_id;
        this.content = content;
        this.rating = rating;
        this.media = media;
        this.date = LocalDate.now();

    }

    String getUsername() {
        return user_id;
    }

    String getContent() {return content;}

    public int getRating() {
        return rating;
    }

    public String getMedia() {
        return media;
    }

    public LocalDate getDate() {
        return date;
    }
}
