package entity;

import java.util.Date;

public class MediaItem {

    private String mediaID;
    private String title;
    private String type;
    private String genre;
    private Date releaseDate;
    private float averageRating;

    public MediaItem(String mediaID, String title, String type, String genre, Date releaseDate, float averageRating) {
        this.mediaID = mediaID;
        this.title = title;
        this.type = type;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.averageRating = averageRating;
    }
}
