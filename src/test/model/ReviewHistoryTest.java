package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Tests for ReviewHistory class
public class ReviewHistoryTest {
    private ReviewHistory myReviews;
    private ArrayList<Review> filtered;

    @BeforeEach
    void setUp() {
        myReviews = new ReviewHistory();
    }

    @Test
    void testConstructor() {
        assertEquals(0, myReviews.getSize());
    }

    @Test
    void addReviewTest() {
        Review testReview = new Review("Minh","Ottawa", 4, "comment here");
        myReviews.addReview(testReview);
        assertEquals(1, myReviews.getSize());
        assertEquals(testReview, myReviews.getReviewHistory().get(0));
    }

    @Test
    void searchReviewHistoryTest() {
        Review testReview = new Review("Minh","Ottawa", 4, "comment here");
        Review testReview1 = new Review("Minh","Vancouver", 4, "comment here");
        Review testReview2 = new Review("Minh","Ottawa", 4, "comment here");

        myReviews.addReview(testReview);
        myReviews.addReview(testReview1);
        myReviews.addReview(testReview2);

        filtered = myReviews.searchReviewHistory("Ottawa");

        assertEquals(2, filtered.size());
        assertTrue(filtered.contains(testReview));
        assertTrue(filtered.contains(testReview2));


    }
}
