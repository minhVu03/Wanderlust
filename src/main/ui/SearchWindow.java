package ui;

import model.Review;
import model.ReviewHistory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Referenced code from:
 * https://github.students.cs.ubc.ca/CPSC210/TellerApp
 * Persistence components referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 * Some code references from different parts of stackoverflow.com
 **/

//Represents a search class that allows user to search for a city and outputs all reviews of that city
public class SearchWindow {
    private JFrame frame;
    private JPanel panel;
    private JLabel cityLabel;
    private JTextField cityText;
    private JButton searchButton;
    private ReviewHistory database;
    private PrintToMain print;

    private JPanel mainPanel;

    //MODIFIES: this
    //EFFECTS: constructs a search window with a text input element and a button that starts the search
    public SearchWindow(ReviewHistory database, JTextArea mainText, JPanel mainPanel, JFrame mainFrame) {
        this.mainPanel = mainPanel;
        this.print = new PrintToMain(mainText, mainFrame);
        this.frame = new JFrame();
        this.panel = new JPanel();
        this.searchButton = new JButton("Search");
        this.database = database;

        frame.setTitle("Search reviews");
        frame.setSize(500, 1000);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridLayout(0, 1));

        cityLabel = new JLabel("Search for a city:");
        cityText = new JTextField(30);
        panel.add(cityLabel);
        panel.add(cityText);
        panel.add(searchButton);
        setUp();

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: add colours to components
    private void setUp() {
        panel.setBackground(MainGui.ORCHID);
        cityLabel.setForeground(MainGui.EARTH);
        cityText.setBackground(MainGui.CREAM);
        searchButton.setForeground(MainGui.CREAM);
        searchButton.setBackground(MainGui.EARTH);
    }

    //EFFECTS: prints out a list of reviews about the city the user has searched up, and
    //         an image of that city
    //         prints out "no reviews to show" if can't find the city in the database
    public void setSearchButton(GridBagConstraints gbc) {
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ImagePrinter imagePrint = new ImagePrinter(mainPanel, 1, gbc);
                print.printReviews(database.searchReviewHistory(cityText.getText()));
                imagePrint.hideAllImages();
                imagePrint.showImage(cityText.getText());
                frame.dispose();
            }
        });
    }

}
