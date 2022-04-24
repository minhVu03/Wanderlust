package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

/**Referenced code from:
 https://github.students.cs.ubc.ca/CPSC210/TellerApp
 Persistence components referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 Some code references from different parts of stackoverflow.com
 GridBagLayout code from https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com
 /javase/tutorial/uiswing/examples/layout/GridBagLayoutDemoProject/src/layout/GridBagLayoutDemo.java
 //Logging referenced from https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
 **/

//Represents a database of all the reviews that were made
public class ReviewHistory implements Writable {
    private ArrayList<Review> myReviewHistory;
    private ArrayList<Review> filtered = new ArrayList<>();

    //MODIFIES: this
    //EFFECTS: Create a review database that stores all the reviews the user made
    public ReviewHistory() {
        myReviewHistory = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add a review to the review history
    public void addReview(Review review) {
        myReviewHistory.add(review);
        EventLog.getInstance().logEvent(new Event("A review for " + review.getCityName()
                + " was added to the current database (aka ReviewHistory)"));
    }

    //EFFECTS: search for a city and return a list of reviews of that city
    public ArrayList<Review> searchReviewHistory(String cityName) {
        for (Review review : myReviewHistory) {
            if (cityName.toLowerCase().equals(review.getCityName().toLowerCase())) {
                filtered.add(review);
            }
        }
        EventLog.getInstance().logEvent(new Event("Searched for " + cityName));
        return filtered;
    }

    @Override
    //EFFECTS: makes a big category that stores all the reviews in data file
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("reviews", reviewsToJson());
        return json;
    }

    // EFFECTS: returns data input as a JSON array
    private JSONArray reviewsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Review r : myReviewHistory) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }

    //Getters//

    public ArrayList<Review> getReviewHistory() {
        return myReviewHistory;
    }

    public int getSize() {
        return myReviewHistory.size();
    }


}
