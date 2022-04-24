package persistence;

import model.Review;
import model.ReviewHistory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 Persistence components referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 **/

//Represents a class that reads data from file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ReviewHistory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseReviewHistory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ReviewHistory from JSON object and returns it
    private ReviewHistory parseReviewHistory(JSONObject jsonObject) {
        //String name = jsonObject.getString("name");
        ReviewHistory rh = new ReviewHistory();
        addReviews(rh, jsonObject);
        return rh;
    }

    // MODIFIES: rh
    // EFFECTS: parses reviews from JSON object and adds them to review history
    private void addReviews(ReviewHistory rh, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("reviews");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addThingy(rh, nextThingy);
        }
    }

    // MODIFIES: rh
    // EFFECTS: parses thingy from JSON object and adds it to review history
    private void addThingy(ReviewHistory rh, JSONObject jsonObject) {
        //String name = jsonObject.getString("name");
        //Category category = Category.valueOf(jsonObject.getString("category"));
        String userName = jsonObject.getString("Posted by");
        String city = jsonObject.getString("City Name");
        int score = jsonObject.getInt("Score");
        String comment = jsonObject.getString("Comment");
        JSONArray tags = jsonObject.getJSONArray("Tags");
        JSONArray recs = jsonObject.getJSONArray("Recs");

        Review review = new Review(userName,city, score, comment);
        //add tags to the review
        if (tags.length() != 0) {
            for (int i = 0; i < tags.length(); i++) {
                review.addTag(tags.getString(i));
            }
        }
        //add recommendations to review
        if (recs.length() != 0) {
            for (int i = 0; i < recs.length(); i++) {
                review.addRec(recs.getString(i));
            }
        }

        rh.addReview(review);

    }

}
