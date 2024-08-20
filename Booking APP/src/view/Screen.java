package view;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public abstract class Screen extends JPanel {

    // Fields
    private JButton backButton;
    private JButton backToHomeButton;

    public Screen() {

      //  setLayout(null); // Absolute positioning
        setBounds(0, 0, DefaultScreen.WIDTH, DefaultScreen.HEIGHT); // Screen size

        // Create back buttons
        ImageIcon backImage = new ImageIcon("images/backButton.png");
        backButton = new JButton(backImage);
        backButton.setBounds(40, 20, backImage.getIconWidth(), backImage.getIconHeight());
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        add(backButton);

        backToHomeButton = new JButton(backImage);
        backToHomeButton.setBounds(40, 20, backImage.getIconWidth(), backImage.getIconHeight());
        backToHomeButton.setContentAreaFilled(false);
        backToHomeButton.setBorderPainted(false);
        add(backToHomeButton);
		

    }


    public JButton getBackButton() {
        return backButton;
    }

    public JButton getBackToHomeButton() {
        return backToHomeButton;
    }
	

}
