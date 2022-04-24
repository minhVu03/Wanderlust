package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/**Referenced code from:
 https://github.students.cs.ubc.ca/CPSC210/TellerApp
 Persistence components referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 Some code references from different parts of stackoverflow.com
 GridBagLayout code from https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com
 /javase/tutorial/uiswing/examples/layout/GridBagLayoutDemoProject/src/layout/GridBagLayoutDemo.java
 **/

//represents a review with a city, rating, comment, list of recommendations, and list of tags
public class Review implements Writable {

    private String cityName;
    private int score;
    private String comment;
    private String owner;
    private List<String> tags;
    private List<String> recs;

    //REQUIRES: score is between 1 and 5 stars
    //EFFECTS: create a review with the input city name, score, and a comment
    public Review(String owner, String city, int score, String comment) {
        this.owner = owner;
        this.cityName = city;
        this.score = score;
        this.comment = comment;
        tags = new ArrayList<>();
        recs = new ArrayList<>();

    }

    //MODIFIES: recs, this
    //EFFECTS: add a recommendation to visit in the city
    public void addRec(String location) {
        recs.add(location);
    }

    //MODIFIES: tags, this
    //EFFECTS: take in values for tags from the input list
    public void setRecList(List recList) {
        this.recs = recList;
    }

    //EFFECTS: return true if input tag is found, will implement later in search database
    public boolean findRec(String rec) {
        boolean found = false;
        for (int i = 0; i < recs.size(); i++) {
            if (recs.get(i) == rec) {
                found = true;
            }
        }
        return found;
    }


    //MODIFIES: tags, this
    //EFFECTS: add a tag to the list of tags that is related to the city, can be keywords
    public void addTag(String tag) {
        tags.add(tag);
    }

    //MODIFIES: tags, this
    //EFFECTS: take in values for tags from the input list
    public void setTagList(List tagList) {
        this.tags = tagList;
    }


    //EFFECTS: return true if input tag is found, will implement later in search database
    public boolean findTag(String tag) {
        boolean found = false;
        for (int i = 0; i < tags.size(); i++) {
            if (tags.get(i).equals(tag)) {
                found = true;
            }
        }
        return found;
    }

    @Override
    //EFFECTS: input information from this review into data file, categorized
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Posted by", owner);
        json.put("City Name", cityName);
        json.put("Score", score);
        json.put("Comment", comment);
        json.put("Tags", tags);
        json.put("Recs", recs);

        //EventLog.getInstance().logEvent(new Event("EVENT: Added review to file"));

        return json;
    }

    //Getters
    public String getCityName() {
        return cityName;
    }

    public int getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public List<String> getRecs() {
        return recs;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getOwner() {
        return owner;
    }
}
