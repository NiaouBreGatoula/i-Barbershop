package view;

import javax.swing.*;
import java.awt.*;

public class ProfileScreen extends Screen {

    // Fields
    private JLabel profileTitleLabel = new JLabel(new ImageIcon("images/profileTitle.png"));
    private JLabel nameLabel = new JLabel("Ονοματεπώνυμο: ");
    private JLabel passwordLabel = new JLabel("Κωδικός: •••••••");
    private JLabel emailLabel = new JLabel("Ηλ.Διεύθυνση: ");
    private JLabel phoneNumberLabel = new JLabel("Αρ.Κινητού: ");
    private JLabel iconsLabel = new JLabel(new ImageIcon("images/profileIcons.png"));

    private static final Font FONT = new Font("Verdana", Font.PLAIN, 30);

    public ProfileScreen() {
        getBackButton().setVisible(false);
        setLayout(new BorderLayout());

        // Create layered pane for the background color, image, and components
        JLayeredPane layeredPane = new JLayeredPane();
        add(layeredPane, BorderLayout.CENTER);

        // Set up the background color
        JPanel backgroundColorPanel = new JPanel();
        backgroundColorPanel.setBackground(new Color(56, 67, 84));
        backgroundColorPanel.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(backgroundColorPanel, JLayeredPane.DEFAULT_LAYER);

        // Set up the background wallpaper
        setBackgroundWallpaper(layeredPane);

        // Create panel for the profile information
        JPanel profilePanel = new JPanel();
        profilePanel.setOpaque(false);

        GridBagLayout layout = new GridBagLayout();
        profilePanel.setLayout(layout);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 10, 30, 10);  // Increase vertical spacing
        gbc.gridx = 1;  // Set the gridx to 1 to create a space for the icons on the left
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Add components to profilePanel
        nameLabel.setFont(FONT);
        nameLabel.setForeground(Color.WHITE);
        profilePanel.add(nameLabel, gbc);

        passwordLabel.setFont(FONT);
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridy++;
        profilePanel.add(passwordLabel, gbc);

        emailLabel.setFont(FONT);
        emailLabel.setForeground(Color.WHITE);
        gbc.gridy++;
        profilePanel.add(emailLabel, gbc);

        phoneNumberLabel.setFont(FONT);
        phoneNumberLabel.setForeground(Color.WHITE);
        gbc.gridy++;
        profilePanel.add(phoneNumberLabel, gbc);

        // Add the profile panel to the layered pane on top of the background
        layeredPane.add(profilePanel, JLayeredPane.PALETTE_LAYER);

        // Create and set up the label for the profile title
        profileTitleLabel.setBounds(getWidth() / 4, getHeight() / 20, getWidth() / 2, getHeight() / 5);
        layeredPane.add(profileTitleLabel, JLayeredPane.MODAL_LAYER);

        // Create and set up the label for the profile icons and add it to the highest layer
        iconsLabel.setBounds(getWidth() / 4 - 150, getHeight() / 7, getWidth() / 2, getHeight() / 1 - 120); // Increase height and adjust position
        layeredPane.add(iconsLabel, JLayeredPane.POPUP_LAYER); // Highest layer

        // Add a ComponentListener to handle resizing
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                backgroundColorPanel.setBounds(0, 0, getWidth(), getHeight());
                updateBackgroundSize(layeredPane);
                profilePanel.setBounds(0, 0, getWidth(), getHeight());
                profileTitleLabel.setBounds(getWidth() / 4, getHeight() / 20, getWidth() / 2, getHeight() / 5);
                iconsLabel.setBounds(getWidth() / 4 - 150, getHeight() / 7, getWidth() / 2, getHeight() / 1 - 120); // Increase height and adjust position
            }
        });

        // Set initial bounds
        profilePanel.setBounds(0, 0, getWidth(), getHeight());
    }

    private void setBackgroundWallpaper(JLayeredPane layeredPane) {
        // Load the image
        ImageIcon wallpaperIcon = new ImageIcon("images/profileBackground.jpg");

        // Create and set up the label for the image
        JLabel wallpaperLabel = new JLabel(wallpaperIcon) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image wallpaperImage = wallpaperIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                g.drawImage(wallpaperImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        wallpaperLabel.setBounds(0, 0, getWidth(), getHeight());

        // Add the wallpaper label to the layered pane
        layeredPane.add(wallpaperLabel, JLayeredPane.DEFAULT_LAYER);
    }

    private void updateBackgroundSize(JLayeredPane layeredPane) {
        // Resize the background image
        Component[] components = layeredPane.getComponentsInLayer(JLayeredPane.DEFAULT_LAYER);
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel wallpaperLabel = (JLabel) component;
                wallpaperLabel.setBounds(0, 0, getWidth(), getHeight());
            }
        }
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public JLabel getPhoneNumberLabel() {
        return phoneNumberLabel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Profile Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new ProfileScreen());
        frame.setVisible(true);
    }
}
