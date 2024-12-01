package entity;

/**
 * A movie entity.
 */
public class Movie {
    private final String title;
    private final String overview;
    private final String releaseDate;
    private final int movieID;

    public Movie(String title, String overview, String releaseDate, int movieID) {
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getMovieID() {
        return movieID;
    }
}
