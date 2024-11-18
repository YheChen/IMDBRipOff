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

    public MovieDataAccessObject(String apiKey) {
        this.client = new OkHttpClient();
        this.apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjOGI0NmI0ZmM3NzFjMjM0Y2MxM2QzMGI4MTQyZTJjZSIsIm5iZiI6MTczMTcwNDU3My4wODY2MTE3LCJzdWIiOiI2NzM3YjU3MTI5NTRkMjY0NzYyNWM1YTkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.ku73LNJs5eaka-bpHVgBPq2clCBliOEc6z4NzdynpJw";
    }

    public List<Movie> searchMovies(String query) throws Exception {
        String url = "https://api.themoviedb.org/3/search/movie?query=" + query + "&include_adult=false&language=en-US&page=1";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to fetch movies: " + response);
            }

            String responseBody = response.body().string();
            return parseMovies(responseBody);
        }
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
}
