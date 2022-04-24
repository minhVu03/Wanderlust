package persistence;

import model.Review;
import model.ReviewHistory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Tests for writing to file
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            ReviewHistory rh = new ReviewHistory();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyReviewHistory() {
        try {
            ReviewHistory rh = new ReviewHistory();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(rh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            rh = reader.read();
            //assertEquals("My work room", rh.getName());
            assertEquals(0, rh.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralReviewHistory() {
        try {
            ReviewHistory rh = new ReviewHistory();
            List<String> recList = new ArrayList<>();
            List<String> tagList = new ArrayList<>();
            recList.add("rec1");
            recList.add("rec2");
            tagList.add("tag1");

            Review r1 = new Review("Minh", "Moscow", 3, "cold");
            r1.setTagList(tagList);
            r1.setRecList(recList);

            Review r2 = new Review("Julie", "Paris", 5, "fancy");
            r2.setTagList(tagList);
            r2.setRecList(recList);

            rh.addReview(r1);
            rh.addReview(r2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralReviewHistory.json");
            writer.open();
            writer.write(rh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralReviewHistory.json");
            rh = reader.read();

            List<Review> reviews = rh.getReviewHistory();
            assertEquals(2, reviews.size());
            checkReview("Minh","Moscow", 3, "cold", reviews.get(0));
            checkReview("Julie","Paris", 5, "fancy", reviews.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
