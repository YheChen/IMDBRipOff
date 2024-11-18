package data_access;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieDataAccessObjectTest {

    @Test
    void testGetRawJsonResponse() throws Exception {
        String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjOGI0NmI0ZmM3NzFjMjM0Y2MxM2QzMGI4MTQyZTJjZSIsIm5iZiI6MTczMTcwNDU3My4wODY2MTE3LCJzdWIiOiI2NzM3YjU3MTI5NTRkMjY0NzYyNWM1YTkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.ku73LNJs5eaka-bpHVgBPq2clCBliOEc6z4NzdynpJw";
        MovieDataAccessObject dao = new MovieDataAccessObject(apiKey);

        String query = "Harry Potter";
        String rawJson = dao.getRawJsonResponse(query);

        System.out.println("Raw JSON Response:");
        System.out.println(rawJson);

        assertNotNull(rawJson, "The JSON response should not be null");
        assertFalse(rawJson.isEmpty(), "The JSON response should not be empty");
        assertTrue(rawJson.contains("\"results\""), "Response should contain 'results' field");
    }
}
