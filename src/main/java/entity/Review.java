package main.java.entity;
import java.util.Date;

public class Review
{
    String reviewID;
    String userID;
    String mediaID;
    String content;
    int rating;
    Date dateCreated;
    Date dateUpdated;

    public Review(String reviewID, String userID, String mediaID, String content, int rating, Date dateCreated) {
        this.reviewID = reviewID;
        this.userID = userID;
        this.mediaID = mediaID;
        this.content = content;
        this.rating = rating;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateCreated;
    }

}
