package controller;

import model.Account;
import model.ScreenType;
import view.RegisterScreen;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.UUID;

public class RegisterController implements ActionListener {

    private RegisterScreen registerScreen; // Get access to register screen

    // Constructor
    public RegisterController(RegisterScreen registerScreen) {
        this.registerScreen = registerScreen;
        setupListeners();
    }

    // Setup listeners
    public void setupListeners() {
        registerScreen.getSignUpButton().addActionListener(this);
        registerScreen.getShowPassword().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerScreen.getSignUpButton())
            createAccount();
        else if (e.getSource() == registerScreen.getShowPassword())
            showPassword();
    }

    // Write account to the database
    private void createAccount() {
        // Fields to get input values
        String firstName = registerScreen.getFirstNameField().getText();
        String lastName = registerScreen.getLastNameField().getText();
        String email = registerScreen.getEmailField().getText().toLowerCase();
        String password = registerScreen.getPasswordField().getText();
        String cPassword = registerScreen.getConfirmPasswordField().getText();
        String phoneNumber = registerScreen.getPhoneNumberField().getText();
        String uniqueID = UUID.randomUUID().toString();

        // Check if first name and last name fields are empty
        if (firstName.length() == 0 || lastName.length() == 0 || firstName.equals("Εισάγετε το Όνομά σας") || lastName.equals("Εισάγετε το Επώνυμό σας")) {
            JOptionPane.showMessageDialog(null, "Παρακαλώ συμπλήρωσε το όνομα σου!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
            // Check if the email is less than 3 characters long and contains the @ symbol
        } else if (email.length() < 3 || !email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(null, "Παρακαλώ δώσε μου μια έγκυρη Ηλ.Διεύθυνση!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
            // Check if passwords match and if the password is at least 8 characters long
        } else if (!password.equals(cPassword)) {
            JOptionPane.showMessageDialog(null, "Οι κωδικοί που μου έδωσες δεν ταιριάζουν",
                    "Error", JOptionPane.ERROR_MESSAGE);
            registerScreen.getPasswordField().setText(""); // Set password field to blank
            registerScreen.getConfirmPasswordField().setText(""); // Set confirm password field to blank
            return;
        } else if (password.length() < 8) {
            JOptionPane.showMessageDialog(null, "Ο Κωδικός θα πρέπει να είναι των 8 χαρακτήρων",
                    "Error", JOptionPane.ERROR_MESSAGE);
            registerScreen.getPasswordField().setText(""); // Set password field to blank
            registerScreen.getConfirmPasswordField().setText(""); // Set confirm password field to blank
            return;
            // Check if phone number is valid
        } else if (phoneNumber.length() != 10 || !phoneNumber.startsWith("69") || !phoneNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Παρακαλώ δώσε μου ένα έγκυρο ελληνικό τηλέφωνο.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Check if an account already has the same email as inputted
            if (DatabaseConnection.isEmailExists(email)) {
                JOptionPane.showMessageDialog(null, "Το email χρησιμοποιείται ήδη!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create account object
            Account account = new Account(firstName, lastName, email, password, phoneNumber, uniqueID);

            // Insert account into database
            DatabaseConnection.insertAccount(account);

            // Reload accounts from database
            ApplicationController.accountController.importAccounts();

            JOptionPane.showMessageDialog(null, "Εξαιρετικά νέα, " + firstName +
                    "! Ο λογαριασμός σου δημιουργήθηκε με επιτυχία");

            // Switch to login screen
            ApplicationController.switchScreen(ApplicationController.screens[ScreenType.LOGIN_SCREEN.getValue()]);

        } catch (SQLException e) {
            System.err.println("An error occurred: " + e);
        }
    }

    // Show and hide password
    private void showPassword() {
        if (registerScreen.getShowPassword().isSelected()) {
            registerScreen.getPasswordField().setEchoChar((char) 0);
            registerScreen.getConfirmPasswordField().setEchoChar((char) 0);
        } else {
            registerScreen.getPasswordField().setEchoChar('•');
            registerScreen.getConfirmPasswordField().setEchoChar('•');
        }
    }
}
