package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeScreen extends Screen {
    private JLabel welcomeLabel = new JLabel("Welcome to i-barber");
    private JButton appointmentButton = new JButton("<html>Κλείσε Ραντεβού</html>");
    private JButton viewAppointmentButton = new JButton("<html>Ιστορικό Όλων των Ραντεβού μου</html>");
    private JButton profileButton = new JButton("Το προφίλ μου");
    private JLabel backgroundLabel;
    private ImageIcon backgroundImage = new ImageIcon("images/image15.jpg"); // Replace with your background image path

    private static final Font FONT = new Font("Verdana", Font.PLAIN, 30);

    public HomeScreen() {
        getBackToHomeButton().setVisible(false);
        setLayout(new BorderLayout());

        // Create a layered pane to hold the background image and other components
        JLayeredPane layeredPane = new JLayeredPane();
        add(layeredPane, BorderLayout.CENTER);

        // Set up the background wallpaper
        setBackgroundWallpaper(layeredPane);

        // Create a panel to hold the main components
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false); // Make panel transparent
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);

        // Add welcome label
        welcomeLabel.setBounds(70, 50, 700, 100);
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 50));
        welcomeLabel.setForeground(Color.WHITE);
        mainPanel.add(welcomeLabel);

        // Configure and add appointment button
        configureButton(appointmentButton, mainPanel);

        // Configure and add view appointment button
        configureButton(viewAppointmentButton, mainPanel);

        // Configure and add profile button
        configureButton(profileButton, mainPanel);

        // Add a ComponentListener to handle resizing
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                mainPanel.setBounds(0, 0, getWidth(), getHeight());
                updateBackgroundSize(layeredPane);
                updateButtonPositions();
            }
        });
    }

    private void setBackgroundWallpaper(JLayeredPane layeredPane) {
        // Load the image
        ImageIcon wallpaperIcon = new ImageIcon("images/image15.jpg");
        Image wallpaperImage = wallpaperIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        wallpaperIcon = new ImageIcon(wallpaperImage);

        // Create and set up the label for the image
        backgroundLabel = new JLabel(wallpaperIcon);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());

        // Add the wallpaper label to the layered pane
        layeredPane.add(backgroundLabel, JLayeredPane.FRAME_CONTENT_LAYER);
    }

    private void updateBackgroundSize(JLayeredPane layeredPane) {
        // Resize the background image
        Component[] components = layeredPane.getComponentsInLayer(JLayeredPane.FRAME_CONTENT_LAYER);
        if (components.length > 0 && components[0] instanceof JLabel) {
            JLabel backgroundLabel = (JLabel) components[0];
            ImageIcon backgroundIcon = new ImageIcon("images/image15.jpg");
            Image backgroundImage = backgroundIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            backgroundIcon = new ImageIcon(backgroundImage);
            backgroundLabel.setIcon(backgroundIcon);
            backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        }
    }

    private void configureButton(JButton button, JPanel panel) {
        button.setBackground(new Color(246, 208, 133)); // Initial background color
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Andale Mono", Font.PLAIN, 28)); // Change font and size
        button.setBorder(new EmptyBorder(10, 30, 10, 30)); // Increase padding for a more premium feel
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        setButtonOpacity(button, 0.7f); // Set initial opacity to 70%

        HoverEffect hoverEffect = new HoverEffect(button);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hoverEffect.startHover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hoverEffect.stopHover();
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hoverEffect.stopHover();
                button.setBackground(new Color(246, 208, 133)); // Reset to original color
                // Add your navigation logic here
            }
        });

        button.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(button.getBackground());
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getButtonOpacity(button)));
                g2d.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20); // Maintain rounded corners
                super.paint(g, c);
            }
        });

        panel.add(button);
    }

    private void setButtonOpacity(JButton button, float opacity) {
        button.putClientProperty("opacity", opacity);
        button.repaint();
    }

    private float getButtonOpacity(JButton button) {
        Float opacity = (Float) button.getClientProperty("opacity");
        return opacity != null ? opacity : 1.0f;
    }

    private class HoverEffect {
        private final JButton button;
        private Timer timer;
        private int transitionStep = 0;
        private final int totalSteps = 50; // total steps for transition
        private final int delay = 30; // delay between steps (ms)

        public HoverEffect(JButton button) {
            this.button = button;
        }

        public void startHover() {
            stopHover(); // Ensure any previous timer is stopped
            timer = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (transitionStep <= totalSteps) {
                        float ratio = (float) transitionStep / totalSteps;
                        float opacity = 0.7f + ratio * 0.3f; // Transition from 70% to 100%
                        setButtonOpacity(button, opacity);
                        transitionStep++;
                    } else {
                        timer.stop();
                    }
                }
            });
            transitionStep = 0;
            timer.start();
        }

        public void stopHover() {
            if (timer != null) {
                timer.stop();
            }
            setButtonOpacity(button, 0.7f); // Reset to initial opacity (70%)
        }
    }

    private void updateButtonPositions() {
        int buttonWidth = getWidth() / 5;
        int buttonHeight = getHeight() / 10;
        int buttonY = getHeight() - buttonHeight - 50; // 50px from bottom

        appointmentButton.setBounds(getWidth() / 8, buttonY, buttonWidth, buttonHeight);
        viewAppointmentButton.setBounds(getWidth() / 8 * 3, buttonY, buttonWidth, buttonHeight);
        profileButton.setBounds(getWidth() / 8 * 5, buttonY, buttonWidth, buttonHeight);
    }

    public JLabel getWelcomeLabel() {
        return welcomeLabel;
    }

    public JButton getAppointmentButton() {
        return appointmentButton;
    }

    public JButton getViewAppointmentButton() {
        return viewAppointmentButton;
    }

    public JButton getProfileButton() {
        return profileButton;
    }

    // Custom panel to paint background image
    class BackgroundPanel extends JPanel {
        private ImageIcon backgroundImage;

        public BackgroundPanel(ImageIcon backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
