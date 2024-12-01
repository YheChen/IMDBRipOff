package use_case.movie_search;

import java.util.List;

import data_access.MovieDataAccessObject;
import entity.Movie;

/**
 * The Input Data for the Movie_Search Use Case.
 */
public class MovieSearchData implements MovieSearchDataInterface {

    private final MovieDataAccessObject movieDataAccessObject;

    public MovieSearchData(MovieDataAccessObject movieDataAccessObject) {
        if (movieDataAccessObject == null) {
            throw new IllegalArgumentException("MovieDataAccessObject cannot be null.");
        }
        this.movieDataAccessObject = movieDataAccessObject;
    }

    @Override
    public List<Movie> searchMovies(String query) throws Exception {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Query cannot be null or empty.");
        }
        return movieDataAccessObject.searchMovies(query);
    }
}
