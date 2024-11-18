package data_access;

import entity.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieDataAccessObject {

    private final OkHttpClient client;
    private final String apiKey;

    public MovieDataAccessObject() {
        this.client = new OkHttpClient();
        // Replace this with your actual API key or load it from an environment variable
        this.apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjOGI0NmI0ZmM3NzFjMjM0Y2MxM2QzMGI4MTQyZTJjZSIsIm5iZiI6MTczMTcwNDU3My4wODY2MTE3LCJzdWIiOiI2NzM3YjU3MTI5NTRkMjY0NzYyNWM1YTkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.ku73LNJs5eaka-bpHVgBPq2clCBliOEc6z4NzdynpJw";
    }

    public List<Movie> searchMovies(String query) throws Exception {
        String url = buildUrl(query);
        String jsonResponse = fetchResponse(url);
        return parseMovies(jsonResponse);
    }

    public String getRawJsonResponse(String query) throws Exception {
        String url = buildUrl(query);
        return fetchResponse(url);
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

            Movie movie = new Movie(title, overview, releaseDate);
            movies.add(movie);
        }
        return movies;
    }

    // Main method for quick testing
    public static void main(String[] args) {
        try {
            MovieDataAccessObject dao = new MovieDataAccessObject();
            String query = "Harry Potter"; // Replace with your search term
            List<Movie> movies = dao.searchMovies(query);

            // Print the movies
            for (Movie movie : movies) {
                System.out.println("Title: " + movie.getTitle());
                System.out.println("Overview: " + movie.getOverview());
                System.out.println("Release Date: " + movie.getReleaseDate());
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
