package ui;

import model.Event;
import model.EventLog;
import model.Profile;
import model.ReviewHistory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

/**Referenced code from:
 https://github.students.cs.ubc.ca/CPSC210/TellerApp
Persistence components referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 Some code references from different parts of stackoverflow.com
GridBagLayout code from https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com
 /javase/tutorial/uiswing/examples/layout/GridBagLayoutDemoProject/src/layout/GridBagLayoutDemo.java
 Logging referenced from https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
 **/

//Travel recommendations and reviews application that runs with a GUI
public class TravelRecsGui {
    private static final String JSON_STORE = "./data/reviewsDatabase.json";
    private ReviewHistory reviewHistory = new ReviewHistory();
    private Profile myProfile = new Profile();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JFrame mainFrame = new JFrame();
    private JPanel mainPanel = new JPanel();

    private JTextArea mainText;
    private LogInGui logIn;
    private PrintToMain print;

    private PostReviewWindow myPost;
    private SearchWindow searchWindow;

    GridBagConstraints gbc = new GridBagConstraints();
    JButton postButton;
    JButton printButton;
    JButton saveButton;
    JButton loadButton;
    JButton searchButton;


    //MODIFIES: this
    //EFFECTS: class constructor runs the user interface methods of the app
    public TravelRecsGui() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(MainGui.CREAM);
        //mainPanel.setPreferredSize(new Dimension(1000, 1000));

        logIn = new LogInGui(myProfile);
        logIn.setContinueButton(mainFrame, mainPanel);

        createMenu();
        addMainText();

        //print out logs when main window closes
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("window closed");
                for (Event next : EventLog.getInstance()) {
                    System.out.println(next.toString() + "\n");
                }
            }
        });

    }

    //MODIFIES: this
    //EFFECTS: sets up the main text area of the application
    public void addMainText() {
        mainText = new JTextArea();
        mainText.setText("Explore, Dream, Live!");
        mainText.setEditable(false);
        mainText.setBackground(MainGui.CREAM);
        mainText.setForeground(MainGui.EARTH);
        mainText.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //add print
        print = new PrintToMain(mainText, mainFrame);

        //gbc.ipady = 500;      //adjust height of cell
        //gbc.weightx = 0.0;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 5;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(mainText, gbc);

    }

    //MODIFIES: this
    //EFFECTS: create buttons for the menu bar at the top of the application
    public void createMenu() {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;

        postButton = new JButton("Write Review");
        gbc.gridx = 0;
        mainPanel.add(postButton, gbc);

        printButton = new JButton("Show review preview");
        gbc.gridx = 1;
        mainPanel.add(printButton, gbc);

        saveButton = new JButton("Save current reviews");
        gbc.weightx = 0.5;
        gbc.gridx = 2;
        mainPanel.add(saveButton, gbc);

        loadButton = new JButton("Recover past reviews");
        gbc.gridx = 3;
        mainPanel.add(loadButton, gbc);

        searchButton = new JButton("Search");
        gbc.gridx = 4;
        mainPanel.add(searchButton, gbc);

        buttonControls();
        moreButtonControls();
        formatButtons();

    }

    //EFFECTS: set colours for the buttons
    private void formatButtons() {
        postButton.setBackground(MainGui.ORCHID);
        printButton.setBackground(MainGui.ORCHID);
        saveButton.setBackground(MainGui.ORCHID);
        loadButton.setBackground(MainGui.ORCHID);
        searchButton.setBackground(MainGui.EARTH);

        postButton.setForeground(MainGui.EARTH);
        printButton.setForeground(MainGui.EARTH);
        saveButton.setForeground(MainGui.EARTH);
        loadButton.setForeground(MainGui.EARTH);
        searchButton.setForeground(MainGui.CREAM);
    }

    //EFFECTS: decide what each button does
    private void buttonControls() {
        postButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                postReview();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveReviewHistory();
            }
        });

        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                print.printReviews(reviewHistory.getReviewHistory());
            }
        });

    }

    //EFFECTS: decides what each of the rest of the buttons does
    private void moreButtonControls() {
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadReviewHistory();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchReview();
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: search the database for a chosen city and prints out all reviews of that city
    private void searchReview() {

        try {
            reviewHistory = jsonReader.read();
            //print file to console
            searchWindow = new SearchWindow(reviewHistory, mainText, mainPanel, mainFrame);
            searchWindow.setSearchButton(gbc);
            //System.out.println("Loaded reviews from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: write a review
    private void postReview() {
        myPost = new PostReviewWindow();
        myPost.setSubmitButton(myProfile, reviewHistory);
    }

    //MODIFIES: this
    //EFFECTS: save the review history to file
    private void saveReviewHistory() {
        try {
            jsonWriter.open();
            jsonWriter.write(reviewHistory);
            jsonWriter.close();
            //System.out.println("Saved all your reviews to " + JSON_STORE);
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
            print.printReviews(reviewHistory.getReviewHistory());
            //System.out.println("Loaded reviews from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }



}
