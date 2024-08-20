package view;

import javax.swing.border.EmptyBorder;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginScreen extends Screen {

    private JLabel loginTitleLabel = new JLabel(new ImageIcon("images/I-BARBER.png"));
    private JLabel loginTitleLabe2 = new JLabel(new ImageIcon("images/I-BARBER-DEC.png"));
    private JLabel loginTitleLabe3 = new JLabel(new ImageIcon("images/I-BARBER-UN.png"));
    private JLabel wallpaperLabel;
    private JTextField emailField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JCheckBox showPassword;
    private JButton registerButton = new JButton("Εγγραφή");
    private JButton loginButton = new JButton("Σύνδεση");
    private JLabel emailLabel = new JLabel("Ηλ.Διεύθυνση");
    private JLabel passwordLabel = new JLabel("Κωδικός");

    public static final Font FONT_EP = new Font("Verdana", Font.PLAIN, 16);
    public static final Font FONT_SH = new Font("Verdana", Font.PLAIN, 12);

    public LoginScreen() {
        getBackButton().setVisible(false);
        getBackToHomeButton().setVisible(false);

        setLayout(new BorderLayout());

        setBackground(new Color(45, 34, 138));

        // Create layered pane for the background and components
        JLayeredPane layeredPane = new JLayeredPane();
        add(layeredPane, BorderLayout.CENTER);

        // Set up the background wallpaper
        setBackgroundWallpaper(layeredPane);

        // Create panel for the top part (images)
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
        imagePanel.setOpaque(false);
        imagePanel.setBackground(new Color(45, 34, 138));

        // Add title images with spacing
        loginTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginTitleLabe2.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginTitleLabe3.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePanel.add(Box.createVerticalStrut(20)); // Add space before the first image
        imagePanel.add(loginTitleLabel);
        imagePanel.add(Box.createVerticalStrut(5)); // Add space between images
        imagePanel.add(loginTitleLabe2);
        imagePanel.add(Box.createVerticalStrut(10)); // Add space between images
        imagePanel.add(loginTitleLabe3);

        layeredPane.add(imagePanel, JLayeredPane.PALETTE_LAYER);
        imagePanel.setBounds(0, 0, getWidth(), 250); // Adjust height to accommodate all images

        // Create panel for login details
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST; // Align labels to the left
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(FONT_EP);
        loginPanel.add(emailLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        emailField.setForeground(Color.GRAY);
        emailField.setPreferredSize(new Dimension(540, 50));
        emailField.setFont(FONT_EP);
        emailField.setBackground(new Color(255, 255, 255));
        emailField.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(10, new Color(128, 128, 128)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        emailField.setOpaque(true);
        emailField.setCaretColor(Color.WHITE);
        setEmailPlaceholder();
        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailField.getForeground().equals(Color.GRAY)) {
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
                emailField.setBorder(BorderFactory.createCompoundBorder(
                        new RoundedBorder(10, new Color(241, 255, 102)),
                        BorderFactory.createEmptyBorder(5, 15, 5, 15)));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emailField.getText().isEmpty()) {
                    setEmailPlaceholder();
                }
                emailField.setBorder(BorderFactory.createCompoundBorder(
                        new RoundedBorder(10, new Color(128, 128, 128)),
                        BorderFactory.createEmptyBorder(5, 15, 5, 15)));
            }
        });
        loginPanel.add(emailField, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST; // Align labels to the left
        passwordLabel.setFont(FONT_EP);
        passwordLabel.setForeground(Color.WHITE);
        loginPanel.add(passwordLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        passwordField.setForeground(Color.GRAY);
        passwordField.setPreferredSize(new Dimension(540, 50));
        passwordField.setFont(FONT_EP);
        passwordField.setBackground(new Color(255, 255, 255));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(10, new Color(128, 128, 128)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        passwordField.setOpaque(true);
        passwordField.setCaretColor(Color.WHITE);
        setPasswordPlaceholder();
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passwordField.getForeground().equals(Color.GRAY)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('\u2022');
                }
                passwordField.setBorder(BorderFactory.createCompoundBorder(
                        new RoundedBorder(10, new Color(241, 255, 102)),
                        BorderFactory.createEmptyBorder(5, 15, 5, 15)));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    setPasswordPlaceholder();
                }
                passwordField.setBorder(BorderFactory.createCompoundBorder(
                        new RoundedBorder(10, new Color(128, 128, 128)),
                        BorderFactory.createEmptyBorder(5, 15, 5, 15)));
            }
        });
        loginPanel.add(passwordField, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        showPassword = new JCheckBox("Εμφάνισε Κωδικό");
        showPassword.setForeground(Color.WHITE);
        showPassword.setBackground(new Color(45, 34, 138));
        showPassword.setFont(FONT_SH);
        showPassword.setOpaque(false);
        loginPanel.add(showPassword, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;

        // Add a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 105, 0)); // Center alignment with horizontal gap

        loginButton.setBackground(new Color(45, 34, 138));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Verdana", Font.BOLD, 16));
        loginButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        loginButton.setFocusPainted(false);
        loginButton.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g;
                ButtonModel model = ((AbstractButton) c).getModel();

                Color color1 = new Color(48, 114, 163);
                Color color2 = new Color(0, 78, 120);
                if (model.isRollover()) {
                    color1 = color1.brighter();
                    color2 = color2.brighter();
                }
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, c.getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);
                super.paint(g, c);
            }
        });
        buttonPanel.add(loginButton);

        registerButton.setBackground(new Color(45, 34, 138));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Verdana", Font.BOLD, 16));
        registerButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        registerButton.setFocusPainted(false);
        registerButton.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g;
                ButtonModel model = ((AbstractButton) c).getModel();

                Color color1 = new Color(48, 114, 163);
                Color color2 = new Color(0, 78, 120);
                if (model.isRollover()) {
                    color1 = color1.brighter();
                    color2 = color2.brighter();
                }
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, c.getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);
                super.paint(g, c);
            }
        });
        buttonPanel.add(registerButton);

        gbc.gridwidth = 2; // Span the button panel across two columns
        loginPanel.add(buttonPanel, gbc);

        layeredPane.add(loginPanel, JLayeredPane.DEFAULT_LAYER);
        loginPanel.setBounds(0, 190, getWidth(), getHeight() - 230);

        // Add a ComponentListener to handle resizing
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                imagePanel.setBounds(0, 0, getWidth(), 250);
                loginPanel.setBounds(0, 190, getWidth(), getHeight() - 230);
                updateBackgroundSize(layeredPane);
            }
        });
    }

    private void setBackgroundWallpaper(JLayeredPane layeredPane) {
        // Load the image
        ImageIcon wallpaperIcon = new ImageIcon("images/wallpaper4.jpg");
        Image wallpaperImage = wallpaperIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        wallpaperIcon = new ImageIcon(wallpaperImage);

        // Create and set up the label for the image
        wallpaperLabel = new JLabel(wallpaperIcon);
        wallpaperLabel.setBounds(0, 0, getWidth(), getHeight());

        // Add the wallpaper label to the layered pane
        layeredPane.add(wallpaperLabel, JLayeredPane.FRAME_CONTENT_LAYER);
    }

    private void updateBackgroundSize(JLayeredPane layeredPane) {
        // Resize the background image
        Component[] components = layeredPane.getComponentsInLayer(JLayeredPane.FRAME_CONTENT_LAYER);
        if (components.length > 0 && components[0] instanceof JLabel) {
            JLabel wallpaperLabel = (JLabel) components[0];
            ImageIcon wallpaperIcon = new ImageIcon("images/wallpaper4.jpg");
            Image wallpaperImage = wallpaperIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            wallpaperIcon = new ImageIcon(wallpaperImage);
            wallpaperLabel.setIcon(wallpaperIcon);
            wallpaperLabel.setBounds(0, 0, getWidth(), getHeight());
        }
    }

    private void setEmailPlaceholder() {
        emailField.setText("Εισάγετε την ηλεκτρονική σας διεύθυνση");
        emailField.setForeground(Color.GRAY);
        emailField.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(10, new Color(128, 128, 128)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));
    }

    private void setPasswordPlaceholder() {
        passwordField.setText("Εισάγετε τον κωδικό σας");
        passwordField.setForeground(Color.GRAY);
        passwordField.setEchoChar((char) 0);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(10, new Color(128, 128, 128)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JCheckBox getShowPassword() {
        return showPassword;
    }

    private class RoundedBorder implements Border {
        private int radius;
        private Color color;

        RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(color);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
