package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Movie;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * DAO for movies in API.
 */
public class MovieDataAccessObject {

    private final OkHttpClient client;
    private final String apiKey;

    public MovieDataAccessObject() {
        this.client = new OkHttpClient();
        final Dotenv dotenv = Dotenv.configure().load();
        this.apiKey = dotenv.get("TMDB_API_KEY");
    }

    /**
     * Search for movies by a query.
     * @param query the query to search by
     * @return a list of movies
     * @throws Exception throws an exception if the api call fails
     */
    public List<Movie> searchMovies(String query) throws Exception {
        final String url = buildUrl(query);
        final String jsonResponse = fetchResponse(url);
        return parseMovies(jsonResponse);
    }

    private String fetchResponse(String url) throws IOException {
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to fetch movies. HTTP Status: " + response.code());
            }
            return response.body().string();
        }
    }

    private String buildUrl(String query) {
        return "https://api.themoviedb.org/3/search/movie?query="
                + query
                + "&include_adult=false&language=en-US&page=1";
    }

    private List<Movie> parseMovies(String jsonResponse) {
        final List<Movie> movies = new ArrayList<>();
        final JSONObject jsonObject = new JSONObject(jsonResponse);
        final JSONArray results = jsonObject.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            final JSONObject movieJson = results.getJSONObject(i);

            final String title = movieJson.optString("title", "Unknown Title");
            final String overview = movieJson.optString("overview", "");
            final String releaseDate = movieJson.optString("release_date", "");
            final int movieID = movieJson.getInt("id");

            final Movie movie = new Movie(title, overview, releaseDate, movieID);
            movies.add(movie);
        }
        return movies;
    }

    private String buildUrl2(String query) {
        return "https://api.themoviedb.org/3/movie/" + query + "/alternative_titles?country=US";
    }

    /**
     * Fetches the name of a movie by id.
     * @param query the query to search by
     * @return a movie name
     * @throws IOException throws an exception if the api call fails
     */
    public String movieNameFromID(String query) throws IOException {
        final String url = buildUrl2(query);
        final String jsonResponse = fetchResponse(url);
        return findName(jsonResponse);
    }

    /**
     * Extracts movie name from api json response.
     * @param jsonResponse the raw json response
     * @return the name of the movie
     */
    private String findName(String jsonResponse) {
        final JSONObject json = new JSONObject(jsonResponse);
        final JSONArray titles = json.optJSONArray("titles");

        String name = "No matching title found.";
        if (titles != null) {
            for (int i = 0; i < titles.length(); i++) {
                final JSONObject titleObject = titles.getJSONObject(i);
                final String type = titleObject.optString("type", null);
                if ("".equals(type)) {
                    name = titleObject.optString("title", "Unknown Title");
                }
            }
        }
        return name;
    }

    /**
     * Builds an api call url with a query.
     * @param query the query
     * @return the url to call
     */
    public String buildUrl3(String query) {
        return "https://api.themoviedb.org/3/movie/" + query + "/images?language=en";
    }

    /**
     * Gets the movie poster image from a query.
     * @param query the query
     * @return the movie poster image url
     * @throws IOException throws an exception if the api call fails
     */
    public String moviePosterFromID(String query) throws IOException {
        final String url = buildUrl3(query);
        final String jsonResponse = fetchResponse(url);
        return findPoster(jsonResponse);
    }

    /**
     * Extracts movie poster from raw json response.
     * @param jsonResponse the raw json response
     * @return the movie poster url
     */
    private String findPoster(String jsonResponse) {
        final JSONObject jsonObject = new JSONObject(jsonResponse);
        final JSONArray posters = jsonObject.optJSONArray("posters");

        String result = "No poster available";
        if (posters != null && posters.length() > 0) {
            // Get the first poster's file path
            final JSONObject firstPoster = posters.getJSONObject(0);
            final String filePath = firstPoster.optString("file_path", "");
            if (!filePath.isEmpty()) {
                // TMDB base URL for images
                // Adjust size (w500, w300, etc.) as needed
                final String baseUrl = "https://image.tmdb.org/t/p/w500";
                result = baseUrl + filePath;
            }
        }

        return result;
    }

    /**
     * Fetches a list of popular movies.
     * @return a list of movies
     * @throws Exception throws in an exception if the api call fails
     */
    public List<Movie> fetchPopularMovies() throws Exception {
        final String url = "https://api.themoviedb.org/3/movie/popular?language=en-US&page=1";
        final String jsonResponse = fetchResponse(url);
        return parseMovies(jsonResponse);
    }

    /* public static void main(String[] args) {
    // Main method for quick testing
//    public static void main(String[] args) {
//        try {
//            MovieDataAccessObject dao = new MovieDataAccessObject();
//
//            String query = "Harry Potter"; // Replace with your search term
//            List<Movie> movies = dao.searchMovies(query);
//            String movieName = dao.MovieNameFromID("120");
//            MovieDataAccessObject ddao = new MovieDataAccessObject();
//
//            // Fetch poster for a movie by ID
//            String moviePoster = ddao.MoviePosterFromID("672"); // Replace "120" with a valid movie ID
//            System.out.println("Movie Poster URL: " + moviePoster);
//            System.out.println("Movie name: " + movieName);
//
//            // Print the movies
//            for (Movie movie : movies) {
//                System.out.println("Title: " + movie.getTitle());
//                System.out.println("Overview: " + movie.getOverview());
//                System.out.println("Release Date: " + movie.getReleaseDate());
//                System.out.println("id: " + movie.getMovieID());
//                System.out.println();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public static void main(String[] args) {
        try {
            MovieDataAccessObject dao = new MovieDataAccessObject();

            // Fetch popular movies
            List<Movie> popularMovies = dao.fetchPopularMovies();

            // Print the popular movies
            System.out.println("Popular Movies:");
            for (Movie movie : popularMovies) {
                System.out.println("Title: " + movie.getTitle());
                System.out.println("Overview: " + movie.getOverview());
                System.out.println("Release Date: " + movie.getReleaseDate());
                System.out.println("ID: " + movie.getMovieID());
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
