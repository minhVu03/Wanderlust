package ui;

import model.Profile;
import model.Review;
import model.ReviewHistory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**Referenced code from:
 https://github.students.cs.ubc.ca/CPSC210/TellerApp
 Persistence components referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 Some code references from different parts of stackoverflow.com
 **/

//Represents a window used for writing a review of a city
public class PostReviewWindow {
    private JPanel panel;
    private JFrame frame;
    private JButton submitButton = new JButton("Submit");

    JLabel cityLabel;
    JLabel ratingLabel;
    JLabel commentLabel;
    JLabel tagsLabel;
    JLabel recsLabel;
    JTextField cityText;
    JTextField ratingText;
    JTextField commentText;
    JTextField tagsText;
    JTextField recsText;

    //MODIFIES: this
    //EFFECTS: constructs a window with fields for city name, rating, comment, tags and recommendations
    public PostReviewWindow() {
        this.frame = new JFrame();
        this.panel = new JPanel();

        frame.setTitle("Post Review");
        frame.setSize(500, 1000);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridLayout(0, 1));

        setUpText();

        frame.add(panel, BorderLayout.NORTH);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: when submit button is pressed, all input information is saved into a Review and added to ReviewHistory
    public void setSubmitButton(Profile myProfile, ReviewHistory reviewHistory) {
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Review myReview = new Review(myProfile.getUserName(), cityText.getText(),
                        Integer.parseInt(ratingText.getText()), commentText.getText());
                //System.out.println(myProfile.getUserName());
                //System.out.println(cityText.getText());

                //add tags, read in string items separated by comma as list elements
                String tags = tagsText.getText();
                myReview.setTagList(Arrays.asList(tags.split(",")));

                //add recommendations
                String recs = recsText.getText();
                myReview.setRecList(Arrays.asList(recs.split(",")));

                reviewHistory.addReview(myReview);

                //close the window after user finished submitting their review
                frame.dispose();

            }
        });
    }

    //EFFECTS: add components to the writing window, assign variables to each user input
    private void setUpText() {
        cityLabel = new JLabel("City", 10);
        cityText = new JTextField(30);
        panel.add(cityLabel);
        panel.add(cityText);

        ratingLabel = new JLabel("Rating (out of 5)", 10);
        ratingText = new JTextField("0",30);
        panel.add(ratingLabel);
        panel.add(ratingText);

        commentLabel = new JLabel("Comment", 10);
        commentText = new JTextField(30);
        panel.add(commentLabel);
        panel.add(commentText);

        tagsLabel = new JLabel("Keywords for your city (separated by commas)", 10);
        tagsText = new JTextField(30);
        panel.add(tagsLabel);
        panel.add(tagsText);

        recsLabel = new JLabel("Recommendations in the city (separated by commas)", 10);
        recsText = new JTextField(30);
        panel.add(recsLabel);
        panel.add(recsText);

        panel.add(submitButton);
        submitButton.setBackground(Color.PINK);
        setUpColors();
    }

    //EFFECTS: add colours to components
    private void setUpColors() {
        panel.setBackground(MainGui.ORCHID);
        cityLabel.setForeground(MainGui.EARTH);
        ratingLabel.setForeground(MainGui.EARTH);
        commentLabel.setForeground(MainGui.EARTH);
        tagsLabel.setForeground(MainGui.EARTH);
        recsLabel.setForeground(MainGui.EARTH);

        cityText.setForeground(MainGui.EARTH);
        ratingText.setForeground(MainGui.EARTH);
        commentText.setForeground(MainGui.EARTH);
        tagsText.setForeground(MainGui.EARTH);
        recsText.setForeground(MainGui.EARTH);

        cityText.setBackground(MainGui.CREAM);
        ratingText.setBackground(MainGui.CREAM);
        commentText.setBackground(MainGui.CREAM);
        tagsText.setBackground(MainGui.CREAM);
        recsText.setBackground(MainGui.CREAM);

        submitButton.setForeground(MainGui.CREAM);
        submitButton.setBackground(MainGui.EARTH);


    }

}
