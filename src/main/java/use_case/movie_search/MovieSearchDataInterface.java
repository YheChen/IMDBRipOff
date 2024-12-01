package use_case.movie_search;

import java.util.List;

import entity.Movie;

/**
 * Interface for the Movie_Search Use Case.
 * Defines the contract for searching movies by a query.
 */
public interface MovieSearchDataInterface {

    /**
     * Searches for movies based on the provided query string.
     *
     * @param query the search query (e.g., movie title or keywords)
     * @return a list of movies matching the query
     * @throws Exception if an error occurs during the search
     */
    List<Movie> searchMovies(String query) throws Exception;
}
