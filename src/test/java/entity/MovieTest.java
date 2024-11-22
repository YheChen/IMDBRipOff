package entity;
import entity.Review;

import data_access.InMemoryReviewDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import entity.Review;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import use_case.login.LoginUserDataAccessInterface;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {
    @Test
    void ReviewWorksAsExpectedTest(){
        String title = "Harry Potter and the Philosopher's Stone";
        String overview = "intro to the magical world of Harry Potter";
        String releaseDate = "2000/1/1";
        int movieID = 300;

        // Checks that the Movie class functions as expected
        Movie movie = new Movie(title, overview, releaseDate, movieID);
        assertEquals(movie.getTitle(), title);
        assertEquals(movie.getOverview(), overview);
        assertEquals(movie.getReleaseDate(), releaseDate);
        assertEquals(movie.getMovieID(), movieID);
    }
}
