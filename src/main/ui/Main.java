package ui;

import java.io.FileNotFoundException;

/**Referenced code from:
 https://github.students.cs.ubc.ca/CPSC210/TellerApp
 Some code references from different parts of stackoverflow.com
 **/

//Represents the main class that runs the program
public class Main {
    public static void main(String[] args) {
        try {
            new TravelRecsGui();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run travel recs application: file not found");
        }

    }
}
