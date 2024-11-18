package use_case.movie_search;

import entity.Movie;
import data_access.MovieDataAccessObject;

import java.util.List;

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

        try {
            return movieDataAccessObject.searchMovies(query);
        } catch (Exception e) {
            throw new Exception("Error occurred while searching for movies with query: " + query, e);
        }
    }
}
