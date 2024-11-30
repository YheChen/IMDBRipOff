package use_case.movie_search;

import data_access.MovieDataAccessObject;
import entity.Movie;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MockMovieDataAccessObject extends MovieDataAccessObject {

    private final List<Movie> movies;

    public MockMovieDataAccessObject(String jsonFilePath) {
        this.movies = new ArrayList<>();
        loadMoviesFromJson(jsonFilePath);
    }

    private void loadMoviesFromJson(String jsonFilePath) {
        try (InputStream inputStream = getClass().getResourceAsStream(jsonFilePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + jsonFilePath);
            }
            String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(json);
            JSONArray results = jsonObject.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject movieJson = results.getJSONObject(i);
                Movie movie = new Movie(
                        movieJson.getString("title"),
                        movieJson.getString("overview"),
                        movieJson.getString("release_date"),
                        movieJson.getInt("id")
                );
                movies.add(movie);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load movies from JSON", e);
        }
    }

    @Override
    public List<Movie> searchMovies(String query) {
        List<Movie> matchingMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(query.toLowerCase())) {
                matchingMovies.add(movie);
            }
        }
        return matchingMovies;
    }
}
