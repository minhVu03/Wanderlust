package ui;

import model.Profile;
import model.Review;
import model.ReviewHistory;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**Referenced code from:
 https://github.students.cs.ubc.ca/CPSC210/TellerApp
 Persistence components referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 Some code references from different parts of stackoverflow.com

 **/

//Travel recommendations and reviews application that runs on console
public class TravelRecs {
    private static final String JSON_STORE = "./data/reviewsDatabase.json";
    private Scanner input = new Scanner(System.in);
    private ReviewHistory reviewHistory = new ReviewHistory();
    private Profile myProfile = new Profile();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    //MainGui mainUi;


    //MODIFIES: this
    //EFFECTS: class constructor runs the user interface methods of the app
    public TravelRecs() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        //mainUi = new MainGui();
        runTravelRecs();

    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n");
        System.out.println("Explore, Dream, Live!");
        System.out.println("Post a review! -> r");
        System.out.println("Print out current reviews -> p");
        System.out.println("Save reviews -> s");
        //this changes the whole current file to match the data file
        System.out.println("Load data from previously saved file -> l");
        System.out.println("Quit writing reviews? -> q");
        //mainUi.showGui();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runTravelRecs() {
        boolean keepGoing = true;
        String command;

        createProfile();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            //command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
                //auto-save to file at the end
                saveReviewHistory();
                printReviews(reviewHistory.getReviewHistory());
                System.out.println("\nHave fun with all your travels!");
            } else if (command.equals("p")) {
                printReviews(reviewHistory.getReviewHistory());
            } else if (command.equals("s")) {
                saveReviewHistory();
            } else if (command.equals("r")) {
                postReview();
            } else if (command.equals("l")) {
                loadReviewHistory();
            } else {
                System.out.println("Try again, always double check your directions when travelling!");
            }
        }
    }




    //EFFECTS: Create a profile for the user using their username and home city
    private void createProfile() {
        //LogInGui logIn = new LogInGui();
        System.out.println("Welcome to Wanderlust! Let's get you started...");
        System.out.println("What's your name? ");
        myProfile.setUserName(input.nextLine());
        //myProfile.setUserName(logIn.getYourName());
        System.out.println("What's your home city? ");
        myProfile.setHomeCity(input.nextLine());
        //myProfile.setHomeCity(logIn.getHome());
        System.out.println("Hi " + myProfile.getUserName() + "! Your profile has been created. "
                + "Feel free to start sharing your thoughts on " + myProfile.getHomeCity() + " to begin your journey!");

    }


    //EFFECTS: ask user for information and organized them into a review, then add this review to a list of reviews
    private void postReview() {
        int score;
        System.out.println("Enter city name: ");
        String cityName = input.next();
        System.out.println("How would you rate " + cityName + " from 1 to 5? (5 being the best city ever!)");
        while (!input.hasNextInt()) {
            try {
                Integer.parseInt(input.next());
                break;
            } catch (Exception e) {
                System.out.println("Error! Rating must be a number from 1 to 5! Try again :D");
            }
        }
        score = input.nextInt();

        //Write a comment
        System.out.println("What are some things you would like to share about " + cityName + "?");
        String comment = input.next();

        Review myReview = new Review(myProfile.getUserName(),cityName, score, comment);


        addTagsOrRecs(myReview);
        reviewHistory.addReview(myReview);

    }

    //EFFECTS: let the user choose to add a tag or recommendation to their city review and add them to the list
    private void addTagsOrRecs(Review myReview) {
        boolean keepAdding = true;
        while (keepAdding) {
            String tag;
            String rec;
            System.out.println("\nAdd keywords so others can find your review! (t) or add a recommendation (r)?");
            System.out.println("q to complete your review");
            String command = input.next();

            if (command.equals("q")) {
                keepAdding = false;
            } else if (command.equals("r")) {
                System.out.println("A place you enjoyed in " + myReview.getCityName() + ": ");
                rec = input.next();
                myReview.addRec(rec);
                System.out.println("'" + rec + "'" + " has been added to your review as a recommendation!");
            } else if (command.equals("t")) {
                System.out.println("Add a keyword: ");
                tag = input.next();
                myReview.addTag(tag);
                System.out.println("Tag " + "'" + tag + "'" + "has been added to your review!");
            } else {
                System.out.println("Try again! Always double check your destination before getting on a flight!");
            }
        }
    }

   //EFFECTS: print out all the reviews made by the user
    private void printReviews(ArrayList<Review> reviewHistory) {
        System.out.println("Reviews: ");
        if (reviewHistory.size() == 0) {
            System.out.println("You haven't made any reviews yet!");
        } else {
            for (int i = 0; i < reviewHistory.size(); i++) {
                printReviews(reviewHistory.get(i));
                System.out.println("\n");
            }
        }
    }

   //EFFECTS: Print out review written by user
    private void printReviews(Review myReview) {
        //Preview of city review
        System.out.println("---------------------------------------------------------------");
        System.out.println("Posted by: " + myReview.getOwner());
        System.out.println("City: " + myReview.getCityName());
        System.out.println("Score: " + myReview.getScore());
        System.out.println("Your comment: " + myReview.getComment());

        //only print tags and recommendations out if user input them
        if (myReview.getTags().size() > 0) {
            System.out.println("Tags you gave this city: ");
            for (int i = 0; i < myReview.getTags().size(); i++) {
                System.out.println(myReview.getTags().get(i) + " ");
            }
        }
        if (myReview.getRecs().size() > 0) {
            System.out.println("Recommendations: ");
            for (int k = 0; k < myReview.getRecs().size(); k++) {
                System.out.println(myReview.getRecs().get(k) + " ");
            }
        }

    }


    //EFFECTS: save the review history to file
    private void saveReviewHistory() {
        try {
            jsonWriter.open();
            jsonWriter.write(reviewHistory);
            jsonWriter.close();
            System.out.println("Saved all your reviews to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads and prints review database from file
    private void loadReviewHistory() {
        try {
            reviewHistory = jsonReader.read();
            //print file to console
            printReviews(reviewHistory.getReviewHistory());
            System.out.println("Loaded reviews from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
