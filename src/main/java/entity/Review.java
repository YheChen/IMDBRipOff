package entity;
import java.time.LocalDate;

public class Review
{
    String reviewID;
    String username;
    String mediaID;
    String content;
    int rating;
    LocalDate dateCreated;
    LocalDate dateUpdated;

    public Review(String username, String mediaID, String content, int rating, LocalDate dateCreated) {
        this.reviewID = generateReviewID();
        this.username = username;
        this.mediaID = mediaID;
        this.content = content;
        this.rating = rating;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateCreated;
    }

    public String getMediaID() {
        return mediaID;
    }

    public String getReviewID() {
        return reviewID;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public LocalDate getDateUpdated() {
        return dateUpdated;
    }

    public int getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }

    private String generateReviewID(){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        final int USERID_LENGTH = 5;
        StringBuilder sb = new StringBuilder(USERID_LENGTH);

        for (int i = 0; i < USERID_LENGTH; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
