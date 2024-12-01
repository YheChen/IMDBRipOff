package data_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Movie;

/**
 * In-memory implementation of the DAO for storing media item data.
 * This implementation does NOT persist data between runs of the program.
 */
public class InMemoryMovieDataAccessObject {

    private final Map<String, List<Movie>> moviesByQuery;

    public InMemoryMovieDataAccessObject() {
        this.moviesByQuery = new HashMap<>();
        // Populate with mock data
        seedData();
    }

    /**
     * Searches for movies by a query.
     * @param query the string to search
     * @return a list of movies
     */
    public List<Movie> searchMovies(String query) {
        return moviesByQuery.getOrDefault(query.toLowerCase(), new ArrayList<>());
    }

    private void seedData() {
        // Mock data for "Harry Potter"
        final List<Movie> harryPotterMovies = new ArrayList<>();
        final int philosophersStoneId = 671;
        harryPotterMovies.add(new Movie("Harry Potter and the Philosopher's Stone",
                "Harry discovers he is a wizard and attends Hogwarts.",
                "2001-11-16", philosophersStoneId));
        final int chamberOfSecretsId = 672;
        harryPotterMovies.add(new Movie("Harry Potter and the Chamber of Secrets",
                "Harry faces the mystery of the Chamber of Secrets at Hogwarts.",
                "2002-11-13", chamberOfSecretsId));

        // Mock data for another query
        final List<Movie> lotrMovies = new ArrayList<>();
        final int fotrId = 120;
        lotrMovies.add(new Movie("The Lord of the Rings: The Fellowship of the Ring",
                "Frodo embarks on a journey to destroy the One Ring.",
                "2001-12-19", fotrId));
        final int tttId = 121;
        lotrMovies.add(new Movie("The Lord of the Rings: The Two Towers",
                "The fellowship is broken, but the fight against Sauron continues.",
                "2002-12-18", tttId));

        // Store data in the map
        moviesByQuery.put("harry potter", harryPotterMovies);
        moviesByQuery.put("lord of the rings", lotrMovies);
    }
}
