package ui;

import javax.swing.*;
import java.awt.*;

/**Referenced code from:
 https://github.students.cs.ubc.ca/CPSC210/TellerApp
 Persistence components referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 Some code references from different parts of stackoverflow.com
 **/

//Represents the main window of the application
public class MainGui {
    static Color EARTH = new Color(63,55,54);
    static Color ORCHID = new Color(193,171,173);
    static Color CREAM = new Color(245, 244, 242);


    private JPanel panel;
    private JFrame frame;

    //MODIFIES: this
    //EFFECTS: constructs a main window with the name "Wanderlust"
    public MainGui(JFrame frame, JPanel panel) {
        this.frame = frame;
        this.panel = panel;

        this.frame.setTitle("WanderLust");
        this.frame.setSize(300,300);
        this.panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.frame.add(panel, BorderLayout.NORTH);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS: make main window visible
    public void showGui() {
        this.frame.pack();
        this.frame.setVisible(true);
    }
}
