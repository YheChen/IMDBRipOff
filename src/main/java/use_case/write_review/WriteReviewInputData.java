package use_case.write_review;

import java.time.LocalDate;

/**
 * The Input Data for the Write Review Case.
 */
public class WriteReviewInputData {
    private final String username;
    private final String content;
    private final int rating;
    private final String media;
    // add the date here?
    private final LocalDate date;

    public WriteReviewInputData(String username, String content, int rating, String media) {

        this.username = username;
        this.content = content;
        this.rating = rating;
        this.media = media;
        this.date = LocalDate.now();

    }

    String getUsername() {
        return username;
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
