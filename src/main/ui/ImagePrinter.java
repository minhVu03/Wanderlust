package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Referenced code from:
 * https://github.students.cs.ubc.ca/CPSC210/TellerApp
 * Some code references from different parts of stackoverflow.com
 * GridBagLayout code from https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com
 * /javase/tutorial/uiswing/examples/layout/GridBagLayoutDemoProject/src/layout/GridBagLayoutDemo.java
 **/

//Represents an object that prints out images according to chosen city
public class ImagePrinter extends Canvas {
    JPanel mainPanel;
    BufferedImage ottawaBuff;
    BufferedImage vancouverBuff;
    BufferedImage sfBuff;
    JLabel ottawaImage;
    JLabel sfImage;
    JLabel vancouverImage;
    GridBagConstraints gbc;

    //EFFECTS: Constructs labels from image files
    public ImagePrinter(JPanel mainPanel, int gridY, GridBagConstraints gbc) {
        this.gbc = gbc;
        this.mainPanel = mainPanel;
        try {
            ottawaBuff = ImageIO.read(new File("src/main/Images/ottawa.jpg"));
            vancouverBuff = ImageIO.read(new File("src/main/Images/van.jpg"));
            sfBuff = ImageIO.read(new File("src/main/Images/sf.jpg"));
        } catch (IOException ex) {
            System.out.println("Could not load image");
        }
        ottawaImage = new JLabel(new ImageIcon(ottawaBuff));
        vancouverImage = new JLabel(new ImageIcon(vancouverBuff));
        sfImage = new JLabel(new ImageIcon(sfBuff));

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = gridY + 1;

        this.mainPanel.add(ottawaImage, gbc);
        this.mainPanel.add(sfImage, gbc);
        this.mainPanel.add(vancouverImage, gbc);

        hideAllImages();

    }

    //EFFECTS: make images visible according to chosen city
    public void showImage(String city) {
        if (city.toLowerCase().equals("ottawa")) {
            ottawaImage.setVisible(true);

        } else if (city.toLowerCase().equals("san francisco")) {
            sfImage.setVisible(true);

        } else if (city.toLowerCase().equals("vancouver")) {
            vancouverImage.setVisible(true);

        }
    }

    //EFFECTS: make images invisible
    public void hideAllImages() {
        ottawaImage.setVisible(false);
        vancouverImage.setVisible(false);
        sfImage.setVisible(false);
        
    }
}