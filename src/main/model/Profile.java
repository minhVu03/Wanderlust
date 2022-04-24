package model;

/**Referenced code from:
 https://github.students.cs.ubc.ca/CPSC210/TellerApp
 Persistence components referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 Some code references from different parts of stackoverflow.com
 GridBagLayout code from https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com
 /javase/tutorial/uiswing/examples/layout/GridBagLayoutDemoProject/src/layout/GridBagLayoutDemo.java
 **/

//Represents a user profile with a username and a home city
public class Profile {
    private String userName;
    private String homeCity;
    //private ArrayList<Review> myReviewList = new ArrayList<>();

    //REQUIRES: Username has non-zero length
    //EFFECTS: Create a profile for the user
    public Profile() {

    }

    //Getters and setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setHomeCity(String city) {
        this.homeCity = city;
    }

    public String getUserName() {
        return userName;
    }

    public String getHomeCity() {
        return homeCity;
    }
}
