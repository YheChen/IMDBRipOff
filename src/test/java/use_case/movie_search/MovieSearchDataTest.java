package use_case.movie_search;

import entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieSearchDataTest {

    private MovieSearchData movieSearchData;

    @BeforeEach
    void setUp() {
        MockMovieDataAccessObject mockDataAccessObject = new MockMovieDataAccessObject("/harry_potter_movies.json");
        movieSearchData = new MovieSearchData(mockDataAccessObject);
    }

    @Test
    void testSearchMovies_ValidQuery_ReturnsResults() throws Exception {
        List<Movie> results = movieSearchData.searchMovies("Harry Potter");
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(20, results.size()); // Adjust based on your actual data
        assertTrue(results.stream().anyMatch(movie -> movie.getTitle().contains("Harry Potter")));
    }

    @Test
    void testSearchMovies_NoResults_ReturnsEmptyList() throws Exception {
        List<Movie> results = movieSearchData.searchMovies("Nonexistent Movie");
        assertNotNull(results);
        assertTrue(results.isEmpty());
    }
    @Test
    void testSearchMovies_PartialMatch_ReturnsResults() throws Exception {
        List<Movie> partialResults = movieSearchData.searchMovies("Potter");
        assertNotNull(partialResults);
        assertFalse(partialResults.isEmpty());
        assertTrue(partialResults.stream().anyMatch(movie -> movie.getTitle().contains("Potter")));
    }
    @Test
    void testSearchMovies_DataAccessThrowsException() {
        MockMovieDataAccessObject mockDataAccessObject = new MockMovieDataAccessObject("/harry_potter_movies.json") {
            @Override
            public List<Movie> searchMovies(String query) {
                if ("ThrowException".equals(query)) {
                    throw new RuntimeException("Simulated data access exception");
                }
                return super.searchMovies(query);
            }
        };

        MovieSearchData movieSearchData = new MovieSearchData(mockDataAccessObject);

        Exception exception = assertThrows(Exception.class, () -> {
            movieSearchData.searchMovies("ThrowException");
        });

        assertTrue(exception.getMessage().contains("Error occurred while searching for movies"));
    }
    @Test
    void testSearchMovies_WhitespaceQuery_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            movieSearchData.searchMovies("   ");
        });
        assertEquals("Query cannot be null or empty.", exception.getMessage());
    }
    @Test
    void testSearchMovies_NullQuery_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            movieSearchData.searchMovies(null);
        });
        assertEquals("Query cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testSearchMovies_EmptyQuery_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            movieSearchData.searchMovies("   "); // Whitespace-only query
        });
        assertEquals("Query cannot be null or empty.", exception.getMessage());
    }
    @Test
    void testSearchMovies_CatchBlockTriggered() {
        // Mock a data access object that throws an exception
        MockMovieDataAccessObject mockDataAccessObject = new MockMovieDataAccessObject("/harry_potter_movies.json") {
            @Override
            public List<Movie> searchMovies(String query) {
                throw new RuntimeException("Simulated exception");
            }
        };

        MovieSearchData movieSearchData = new MovieSearchData(mockDataAccessObject);

        Exception exception = assertThrows(Exception.class, () -> {
            movieSearchData.searchMovies("Valid Query");
        });

        assertTrue(exception.getMessage().contains("Error occurred while searching for movies"));
    }
    @Test
    void testConstructor_NullDataAccessObject_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new MovieSearchData(null); // Pass null to trigger the exception
        });
        assertEquals("MovieDataAccessObject cannot be null.", exception.getMessage());
    }
}
