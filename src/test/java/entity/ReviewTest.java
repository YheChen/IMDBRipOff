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

import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest {
    @Test
    void ReviewWorksAsExpectedTest(){
        final int rating = 1;
        String userID = "bl76544";
        String mediaID = "bl5434";
        String content = "This movie was the best movie I've ever watched!";
        LocalDate dateCreated = LocalDate.now();

        // Checks that the Review class functions as expected
        Review review = new Review(userID, mediaID, content, rating, dateCreated);
        assertEquals(review.getContent(), content);
        assertEquals(review.getUserID(), userID);
        assertEquals(review.getMediaID(), mediaID);
        assertEquals(review.getDateCreated(), dateCreated);
        assertEquals(review.getRating(), rating);
    }
}
