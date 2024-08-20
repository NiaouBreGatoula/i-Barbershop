package view;

import javax.swing.border.EmptyBorder;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RegisterScreen extends Screen {

    private JTextField firstNameField = new JTextField();
    private JTextField lastNameField = new JTextField();
    private JTextField emailField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JPasswordField confirmPasswordField = new JPasswordField();
    private JTextField phoneNumberField = new JTextField();
    private JLabel firstNameLabel = new JLabel("Όνομα");
    private JLabel lastNameLabel = new JLabel("Επώνυμο");
    private JLabel emailLabel = new JLabel("Ηλ.Διεύθυνση");
    private JLabel passwordLabel = new JLabel("Κωδικός");
    private JLabel confirmPasswordLabel = new JLabel("Επιβεβαίωση Κωδικού");
    private JLabel phoneNumberLabel = new JLabel("Αριθμός Τηλεφώνου");
    private JCheckBox showPassword = new JCheckBox("Εμφάνιση κωδικού");
    private JButton signUpButton = new JButton("Δημιουργία Λογαριασμού");

    private JLabel topLeftImageLabel;
    private JLabel centerRightImageLabel;
    public static final Font LABEL_FONT = new Font("Verdana", Font.PLAIN, 18);
    public static final Font FIELD_FONT = new Font("Verdana", Font.PLAIN, 16);
    public static final Font CHECKBOX_FONT = new Font("Verdana", Font.PLAIN, 12); // Smaller font for checkbox
    public static final Color LABEL_COLOR = new Color(255, 255, 255);

    public RegisterScreen() {
        getBackToHomeButton().setVisible(false); // Hide back button

        setLayout(new BorderLayout());

        // Create and set up the layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        add(layeredPane, BorderLayout.CENTER);

        // Set up the background image
        setBackgroundWallpaper(layeredPane);

        // Set up the form panel container
        JPanel formContainer = new JPanel(new GridBagLayout());
        formContainer.setOpaque(false); // Make the container transparent
        formContainer.setPreferredSize(new Dimension(400, getHeight())); // Set a preferred width

        // Set up the form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false); // Make the form panel transparent
        addFormComponents(formPanel);

        GridBagConstraints formContainerGbc = new GridBagConstraints();
        formContainerGbc.gridx = 0;
        formContainerGbc.gridy = 0;
        formContainerGbc.weightx = 1;
        formContainerGbc.weighty = 1;
        formContainerGbc.anchor = GridBagConstraints.CENTER;
        formContainerGbc.insets = new Insets(10, 10, 10, 10);
        formContainer.add(formPanel, formContainerGbc); // Center the form in the container

        // Add the form panel container to the layered pane on top of the background
        layeredPane.add(formContainer, JLayeredPane.DEFAULT_LAYER);
        formContainer.setBounds(0, 150, getWidth(), getHeight() - 150); // Adjusted to leave space above the form

        // Add the title image at the top
        addTitleImage(layeredPane);

        // Add a ComponentListener to handle resizing
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formContainer.setBounds(0, 150, getWidth(), getHeight() - 150); // Adjusted to leave space above the form
                updateBackgroundSize(layeredPane);
                updateTitleImageSize(layeredPane);
            }
        });
    }

    private void setBackgroundWallpaper(JLayeredPane layeredPane) {
        // Load the image
        ImageIcon wallpaperIcon = new ImageIcon("images/wallpaper3.jpg");
        Image wallpaperImage = wallpaperIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        wallpaperIcon = new ImageIcon(wallpaperImage);

        // Create and set up the label for the image
        JLabel wallpaperLabel = new JLabel(wallpaperIcon);
        wallpaperLabel.setBounds(0, 0, getWidth(), getHeight());

        // Add the wallpaper label to the layered pane
        layeredPane.add(wallpaperLabel, JLayeredPane.FRAME_CONTENT_LAYER);
    }

    private void addTitleImage(JLayeredPane layeredPane) {
        // Load the title image
        ImageIcon titleIcon = new ImageIcon("images/registerTitle.png");
        JLabel titleLabel = new JLabel(titleIcon);

        // Set the title label bounds
        titleLabel.setBounds(0, 0, titleIcon.getIconWidth(), titleIcon.getIconHeight());

        // Add the title label to the layered pane
        layeredPane.add(titleLabel, JLayeredPane.PALETTE_LAYER);
    }

    private void updateBackgroundSize(JLayeredPane layeredPane) {
        // Resize the background image
        Component[] components = layeredPane.getComponentsInLayer(JLayeredPane.FRAME_CONTENT_LAYER);
        if (components.length > 0 && components[0] instanceof JLabel) {
            JLabel wallpaperLabel = (JLabel) components[0];
            ImageIcon wallpaperIcon = new ImageIcon("images/wallpaper3.jpg");
            Image wallpaperImage = wallpaperIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            wallpaperIcon = new ImageIcon(wallpaperImage);
            wallpaperLabel.setIcon(wallpaperIcon);
            wallpaperLabel.setBounds(0, 0, getWidth(), getHeight());
        }
    }

    private void updateTitleImageSize(JLayeredPane layeredPane) {
        // Resize the title image
        Component[] components = layeredPane.getComponentsInLayer(JLayeredPane.PALETTE_LAYER);
        if (components.length > 0 && components[0] instanceof JLabel) {
            JLabel titleLabel = (JLabel) components[0];
            ImageIcon titleIcon = new ImageIcon("images/registerTitle.png");
            Image titleImage = titleIcon.getImage().getScaledInstance(getWidth(), titleIcon.getIconHeight(), Image.SCALE_SMOOTH); // Resize image proportionally
            titleIcon = new ImageIcon(titleImage);
            titleLabel.setIcon(titleIcon);
            titleLabel.setBounds(0, 0, getWidth(), titleIcon.getIconHeight()); // Adjust bounds for resized image
        }
    }

    private void addFormComponents(JPanel formPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 1: Form Fields
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // First Name Field
        gbc.gridx = 0;
        firstNameLabel.setFont(LABEL_FONT);
        firstNameLabel.setForeground(LABEL_COLOR);
        formPanel.add(firstNameLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        formPanel.add(firstNameField, gbc);
        setupTextField(firstNameField, "Εισάγετε το Όνομά σας");

        // Last Name Field
        gbc.gridx = 0;
        gbc.gridy++;
        lastNameLabel.setFont(LABEL_FONT);
        lastNameLabel.setForeground(LABEL_COLOR);
        formPanel.add(lastNameLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        formPanel.add(lastNameField, gbc);
        setupTextField(lastNameField, "Εισάγετε το Επώνυμό σας");

        // Email Field
        gbc.gridx = 0;
        gbc.gridy++;
        emailLabel.setFont(LABEL_FONT);
        emailLabel.setForeground(LABEL_COLOR);
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        formPanel.add(emailField, gbc);
        setupTextField(emailField, "Εισάγετε την Ηλ.Διεύθυνσή σας");

        // Password Field
        gbc.gridx = 0;
        gbc.gridy++;
        passwordLabel.setFont(LABEL_FONT);
        passwordLabel.setForeground(LABEL_COLOR);
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        formPanel.add(passwordField, gbc);
        setupTextField(passwordField, "Εισάγετε τον Κωδικό σας");

        // Confirm Password Field
        gbc.gridx = 0;
        gbc.gridy++;
        confirmPasswordLabel.setFont(LABEL_FONT);
        confirmPasswordLabel.setForeground(LABEL_COLOR);
        formPanel.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        formPanel.add(confirmPasswordField, gbc);
        setupTextField(confirmPasswordField, "Επιβεβαιώστε τον Κωδικό σας");

        // Phone Number Field
        gbc.gridx = 0;
        gbc.gridy++;
        phoneNumberLabel.setFont(LABEL_FONT);
        phoneNumberLabel.setForeground(LABEL_COLOR);
        formPanel.add(phoneNumberLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        formPanel.add(phoneNumberField, gbc);
        setupTextField(phoneNumberField, "Εισάγετε τον Αριθμό Τηλεφώνου σας");

        // Show Password Checkbox
        gbc.gridx = 1;
        gbc.gridy++;
        showPassword.setFont(CHECKBOX_FONT); // Use the smaller font for checkbox
        showPassword.setForeground(Color.WHITE);
        showPassword.setOpaque(false);
        formPanel.add(showPassword, gbc);

        // Sign Up Button
        gbc.gridy++;
        gbc.weightx = 1;
        signUpButton.setPreferredSize(new Dimension(200, 50));
        formPanel.add(signUpButton, gbc);

        // Customize Sign Up Button
        customizeSignUpButton(signUpButton);
    }

    private void setupTextField(JTextField textField, String placeholder) {
        textField.setFont(FIELD_FONT);
        textField.setForeground(Color.GRAY);
        textField.setBackground(new Color(255, 255, 255));
        textField.setBorder(createRoundedBorder(new Color(0, 0, 0)));
        textField.setCaretColor(Color.BLACK);
        setPlaceholder(textField, placeholder);
        textField.addFocusListener(createFocusAdapter(textField, placeholder));
    }

    private void setPlaceholder(JTextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
    }

    private FocusAdapter createFocusAdapter(JTextField field, String placeholder) {
        return new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getForeground().equals(Color.GRAY)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    setPlaceholder(field, placeholder);
                }
            }
        };
    }

    private Border createRoundedBorder(Color color) {
        return BorderFactory.createCompoundBorder(
                new RoundedBorder(10, color),
                BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    private void customizeSignUpButton(JButton button) {
        button.setFont(new Font("Verdana", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(45, 34, 138));
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setUI(new BasicButtonUI() {
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
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JPasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public JCheckBox getShowPassword() {
        return showPassword;
    }

    public JButton getSignUpButton() {
        return signUpButton;
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
