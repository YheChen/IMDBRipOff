package entity;

import java.util.Date;

/**
 * A review entity.
 */
public class Review {

    private static final int USERID_LENGTH = 5;

    private String reviewID;
    private String userID;
    private String mediaID;
    private String title;
    private String content;
    private int rating;
    private Date dateCreated;
    private Date dateUpdated;

    public Review(String userID, String mediaID, String title, String content, int rating, Date dateCreated) {
        this.reviewID = generateReviewID();
        this.userID = userID;
        this.mediaID = mediaID;
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateCreated;
    }

    public Review(String reviewID, String userID, String mediaID, String title, String content, int rating, Date dateCreated) {
        this.reviewID = reviewID;
        this.userID = userID;
        this.mediaID = mediaID;
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateCreated;
    }

    public String getUserID() {
        return userID;
    }

    public String getMediaID() {
        return mediaID;
    }

    public String getReviewID() {
        return reviewID;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public int getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    private String generateReviewID() {
        final String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of alphaNumericString
        final StringBuilder sb = new StringBuilder(USERID_LENGTH);

        for (int i = 0; i < USERID_LENGTH; i++) {

            // generate a random number between
            // 0 to alphaNumericString variable length
            final int index = (int)
                    (alphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(alphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
