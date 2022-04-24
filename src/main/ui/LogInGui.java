package ui;

import model.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**Referenced code from:
 https://github.students.cs.ubc.ca/CPSC210/TellerApp
 Some code references from different parts of stackoverflow.com
 GridBagLayout code from https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com
 /javase/tutorial/uiswing/examples/layout/GridBagLayoutDemoProject/src/layout/GridBagLayoutDemo.java
 **/

//Represents a log in window where user can input their name and hometown and the information is saved in Profile
public class LogInGui {
    private JPanel panel;
    private JFrame frame;

    JLabel nameLabel;
    JLabel homeTown;
    JTextField textBox;
    JTextField home;

    JButton continueButton;

    Profile myProfile;

    //EFFECTS: constructs and display a log in window with input boxes
    public LogInGui(Profile myProfile) {
        this.frame = new JFrame();
        this.panel = new JPanel();
        this.myProfile = myProfile;
        //Add continue button in log in window
        this.continueButton = new JButton("Continue");
        continueButton.setBackground(MainGui.EARTH);
        continueButton.setForeground(MainGui.CREAM);

        frame.setTitle("WanderLust Log In");
        frame.setSize(300,300);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(MainGui.ORCHID);

        JLabel msg = new JLabel("Please log in to continue \n ");
        JLabel msg2 = new JLabel("                            ");


        msg.setBorder(BorderFactory.createEmptyBorder(20, 0, 0,0));

        setUpWindow();
        saveInfo();
        panel.add(msg);
        panel.add(msg2);

        frame.add(panel, BorderLayout.CENTER);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: Add a continue button to the window and show the main GUI when pressed
    public void setContinueButton(JFrame mainFrame, JPanel mainPanel) {
        panel.add(continueButton);
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainGui ui = new MainGui(mainFrame, mainPanel);
                ui.showGui();
                frame.dispose();
            }
        });
    }

    //EFFECTS: set up text fields and labels for user input
    private void setUpWindow() {
        nameLabel = new JLabel("Name", 10);
        nameLabel.setForeground(MainGui.EARTH);
        textBox = new JTextField(30); //30 sets how wide the text box is
        textBox.setBackground(MainGui.CREAM);
        panel.add(nameLabel);
        panel.add(textBox);

        homeTown = new JLabel("Home city", 10);
        homeTown.setForeground(MainGui.EARTH);
        home = new JTextField(30); //30 sets how wide the text box is
        home.setBackground(MainGui.CREAM);
        panel.add(homeTown);
        panel.add(home);

    }

    //EFFECTS: Save user input information into Profile when continue button is pressed
    private void saveInfo() {
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = textBox.getText();
                String input2 = home.getText();

                myProfile.setUserName(input);
                myProfile.setHomeCity(input2);

            }
        });
    }

}
