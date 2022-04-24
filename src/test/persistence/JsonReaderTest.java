package persistence;

import model.Review;
import model.ReviewHistory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Tests for reading from file
class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ReviewHistory rh = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyReviewHistory.json");
        try {
            ReviewHistory rh = reader.read();
            //assertEquals("My review history", rh.getReviewHistory());
            assertEquals(0, rh.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralReviewHistory.json");
        List<String> recList = new ArrayList<>();
        List<String> tagList = new ArrayList<>();
        recList.add("rec1");
        recList.add("rec2");
        tagList.add("tag1");
        List<String> emptyTags = new ArrayList<>();
        List<String> emptyRecs = new ArrayList<>();

        try {
            ReviewHistory rh = reader.read();
            //assertEquals("My work room", rh.getName());
            List<Review> reviews = rh.getReviewHistory();
            assertEquals(2, reviews.size());
            checkReview("Minh","Ottawa", 4, "nice", reviews.get(0));
            checkReviewEmptyTagRecList("Minh","Vancouver", 3, "pretty", reviews.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }




}
