package persistence;

import model.Review;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Persistence tests
public class JsonTest {
    protected void checkReview(String owner, String cityName, int score, String comment, Review review) {
        assertEquals(owner, review.getOwner());
        assertEquals(cityName, review.getCityName());
        assertEquals(score, review.getScore());
        assertEquals(comment, review.getComment());
        assertEquals(1, review.getTags().size());
        assertEquals(2, review.getRecs().size());
        assertTrue(review.getRecs().contains("rec2"));
    }

    protected void checkReviewEmptyTagRecList(String owner, String cityName, int score, String comment, Review review){
        assertEquals(owner, review.getOwner());
        assertEquals(cityName, review.getCityName());
        assertEquals(score, review.getScore());
        assertEquals(comment, review.getComment());
        assertEquals(0, review.getTags().size());
        assertEquals(0, review.getRecs().size());
        assertFalse(review.getRecs().contains("rec2"));
    }
}
