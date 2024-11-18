package entity;

public class Movie {
    private final String title;
    private final String overview;
    private final String releaseDate;

    public Movie(String title, String overview, String releaseDate) {
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
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
}
