package data_access;

import entity.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.ArrayList;
import java.util.List;

public class MovieDataAccessObject {

    private final OkHttpClient client;
    private final String apiKey;

    public MovieDataAccessObject() {
        this.client = new OkHttpClient();
        Dotenv dotenv = Dotenv.configure().load();
        this.apiKey = dotenv.get("TMDB_API_KEY");
    }

    public List<Movie> searchMovies(String query) throws Exception {
        String url = buildUrl(query);
        String jsonResponse = fetchResponse(url);
        return parseMovies(jsonResponse);
    }

    private String fetchResponse(String url) throws Exception {
        Request request = new Request.Builder()
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
        return "https://api.themoviedb.org/3/search/movie?query=" + query + "&include_adult=false&language=en-US&page=1";
    }

    private List<Movie> parseMovies(String jsonResponse) {
        List<Movie> movies = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray results = jsonObject.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject movieJson = results.getJSONObject(i);

            String title = movieJson.optString("title", "Unknown Title");
            String overview = movieJson.optString("overview", "");
            String releaseDate = movieJson.optString("release_date", "");
            int movieID = movieJson.getInt("id");

            Movie movie = new Movie(title, overview, releaseDate, movieID);
            movies.add(movie);
        }
        return movies;
    }

    private String buildUrl2(String query) {
        return "https://api.themoviedb.org/3/movie/" + query + "/alternative_titles?country=US";
    }

    public String MovieNameFromID(String query) throws Exception {
        String url = buildUrl2(query);
        String jsonResponse = fetchResponse(url);
        return findName(jsonResponse);
    }

    private String findName(String jsonResponse) {
        JSONObject json = new JSONObject(jsonResponse);
        JSONArray titles = json.optJSONArray("titles");

        if (titles != null) {
            for (int i = 0; i < titles.length(); i++) {
                JSONObject titleObject = titles.getJSONObject(i);
                String type = titleObject.optString("type", null);
                if ("".equals(type)) {
                    return titleObject.optString("title", "Unknown Title");
                }
            }
        }
        return "No matching title found.";
    }

    public String buildUrl3(String query) {
        return "https://api.themoviedb.org/3/movie/" + query + "/images?language=en";
    }

    public String MoviePosterFromID(String query) throws Exception {
        String url = buildUrl3(query);
        String jsonResponse = fetchResponse(url);
        return findPoster(jsonResponse);
    }

    private String findPoster(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray posters = jsonObject.optJSONArray("posters");

        if (posters != null && posters.length() > 0) {
            // Get the first poster's file path
            JSONObject firstPoster = posters.getJSONObject(0);
            String filePath = firstPoster.optString("file_path", "");
            if (!filePath.isEmpty()) {
                // TMDB base URL for images
                String baseUrl = "https://image.tmdb.org/t/p/w500"; // Adjust size (w500, w300, etc.) as needed
                return baseUrl + filePath;
            }
        }

        return "No poster available.";
    }

    // Main method for quick testing
    public static void main(String[] args) {
        try {
            MovieDataAccessObject dao = new MovieDataAccessObject();
            String query = "Harry Potter"; // Replace with your search term
            List<Movie> movies = dao.searchMovies(query);
            String movieName = dao.MovieNameFromID("120");
            MovieDataAccessObject ddao = new MovieDataAccessObject();

            // Fetch poster for a movie by ID
            String moviePoster = ddao.MoviePosterFromID("672"); // Replace "120" with a valid movie ID
            System.out.println("Movie Poster URL: " + moviePoster);
            System.out.println("Movie name: " + movieName);

            // Print the movies
            for (Movie movie : movies) {
                System.out.println("Title: " + movie.getTitle());
                System.out.println("Overview: " + movie.getOverview());
                System.out.println("Release Date: " + movie.getReleaseDate());
                System.out.println("id: " + movie.getMovieID());
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
