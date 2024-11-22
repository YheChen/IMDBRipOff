package data_access;

import entity.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class MovieNameFromidDAO {
    private final OkHttpClient client;
    private final String apiKey;

    public MovieNameFromidDAO() {
        this.client = new OkHttpClient();
        // Replace this with your actual API key or load it from an environment variable
        this.apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjOGI0NmI0ZmM3NzFjMjM0Y2MxM2QzMGI4MTQyZTJjZSIsIm5iZiI6MTczMTcwNDU3My4wODY2MTE3LCJzdWIiOiI2NzM3YjU3MTI5NTRkMjY0NzYyNWM1YTkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.ku73LNJs5eaka-bpHVgBPq2clCBliOEc6z4NzdynpJw";
    }

    public String MovieNameFromID(String query) throws Exception {
        String url = buildUrl(query);
        String jsonResponse = fetchResponse(url);
        return findName(jsonResponse);
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
        return "https://api.themoviedb.org/3/movie/" + query + "/alternative_titles?country=US";
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

    public static void main(String[] args) {
        MovieNameFromidDAO dao = new MovieNameFromidDAO();
        try {
            // Test with movie ID 120
            String movieName = dao.MovieNameFromID("120");
            System.out.println("Movie name: " + movieName);
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
