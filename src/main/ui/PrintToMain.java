package ui;

import model.Review;

import javax.swing.*;
import java.util.ArrayList;

/**Referenced code from:
 https://github.students.cs.ubc.ca/CPSC210/TellerApp
 Persistence components referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 Some code references from different parts of stackoverflow.com
 **/

//Represents a printing class that prints reviews onto the main text area
public class PrintToMain {
    //ImagePrinter imagePrinter;
    JTextArea mainText;
    JFrame mainFrame;

    //MODIFIES: this
    //EFFECTS: initialize the main text area
    public PrintToMain(JTextArea text, JFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.mainText = text;
    }


    //EFFECTS: print out all the reviews made by the user, resizing frame to fit all data
    public void printReviews(ArrayList<Review> reviewList) {
        mainText.setText("Reviews: ");
        if (reviewList.size() == 0) {
            mainText.append("No reviews to show!");
        } else {
            for (int i = 0; i < reviewList.size(); i++) {
                printReview(reviewList.get(i));
                mainText.append("\n");
            }
        }
        mainFrame.pack();
    }

    //EFFECTS: Print out review written by user
    public void printReview(Review myReview) {
        //Preview of city review
        mainText.append("---------------------------------------------------------------" + "\n");
        mainText.append("Posted by: " + myReview.getOwner() + "\n");
        mainText.append("City: " + myReview.getCityName() + "\n");
        mainText.append("Score: " + myReview.getScore() + "\n");
        mainText.append("Comment: " + myReview.getComment() + "\n");

        //only print tags and recommendations out if user input them
        if (myReview.getTags().size() > 0) {
            mainText.append("Tags (keywords): ");
            for (int i = 0; i < myReview.getTags().size(); i++) {
                mainText.append(myReview.getTags().get(i) + ", ");
            }
            mainText.append("\n");
        }
        if (myReview.getRecs().size() > 0) {
            mainText.append("Recommendations: ");
            for (int k = 0; k < myReview.getRecs().size(); k++) {
                mainText.append(myReview.getRecs().get(k) + ", ");
            }
        }


    }


}
