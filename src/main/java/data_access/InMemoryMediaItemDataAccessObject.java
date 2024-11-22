package data_access;

import entity.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of the DAO for storing media item data.
 * This implementation does NOT persist data between runs of the program.
 */
public class InMemoryMediaItemDataAccessObject {

    private final Map<String, List<Movie>> moviesByQuery;

    public InMemoryMediaItemDataAccessObject() {
        this.moviesByQuery = new HashMap<>();
        seedData(); // Populate with mock data
    }

    public List<Movie> searchMovies(String query) {
        return moviesByQuery.getOrDefault(query.toLowerCase(), new ArrayList<>());
    }

    private void seedData() {
        // Mock data for "Harry Potter"
        List<Movie> harryPotterMovies = new ArrayList<>();
        harryPotterMovies.add(new Movie("Harry Potter and the Philosopher's Stone",
                "Harry discovers he is a wizard and attends Hogwarts.",
                "2001-11-16", 671));
        harryPotterMovies.add(new Movie("Harry Potter and the Chamber of Secrets",
                "Harry faces the mystery of the Chamber of Secrets at Hogwarts.",
                "2002-11-13", 672));

        // Mock data for another query
        List<Movie> lotrMovies = new ArrayList<>();
        lotrMovies.add(new Movie("The Lord of the Rings: The Fellowship of the Ring",
                "Frodo embarks on a journey to destroy the One Ring.",
                "2001-12-19", 120));
        lotrMovies.add(new Movie("The Lord of the Rings: The Two Towers",
                "The fellowship is broken, but the fight against Sauron continues.",
                "2002-12-18", 121));

        // Store data in the map
        moviesByQuery.put("harry potter", harryPotterMovies);
        moviesByQuery.put("lord of the rings", lotrMovies);
    }
}
