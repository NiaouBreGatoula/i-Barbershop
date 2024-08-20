package controller;

import model.Account;
import model.ScreenType;
import view.LoginScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginController implements ActionListener {

    private LoginScreen loginScreen; // Access login screen
    private String emailInput; // Get email input

    // Constructor
    public LoginController(LoginScreen loginScreen) {
        this.loginScreen = loginScreen;
        setupListeners();
    }

    // Setup listeners
    public void setupListeners() {
        loginScreen.getLoginButton().addActionListener(this);
        loginScreen.getRegisterButton().addActionListener(this);
        loginScreen.getShowPassword().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginScreen.getLoginButton()) { // User clicked login button
            loginAccount(); // Call loginAccount method
        } else if (e.getSource() == loginScreen.getRegisterButton()) { // User clicked register button
            // Switch to register screen
            ApplicationController.switchScreen(ApplicationController.screens[ScreenType.REGISTER_SCREEN.getValue()]);
        } else if (e.getSource() == loginScreen.getShowPassword()) // User checked show password box
            showPassword();
    }

    // Logging into account by verifying with the database
    // Check if the inputted account exists and set the account information to the current account
    private void loginAccount() {
        boolean accountFound = false; // Mark when an account is logged in
        emailInput = loginScreen.getEmailField().getText().toLowerCase(); // Get email input
        String passwordInput = loginScreen.getPasswordField().getText(); // Get password input

        try {
            // Fetch the account from the database
            Account account = DatabaseConnection.verifyAccount(emailInput, passwordInput);

            if (account != null) {
                accountFound = true; // Found account login

                // Switch to home screen
                ApplicationController.switchScreen(ApplicationController.screens[ScreenType.HOME_SCREEN.getValue()]);

                ApplicationController.currentAppointments.clear(); // Clear current appointments
                ApplicationController.appointments.clear(); // Clear appointments

                // Set the account information to the logged in account
                ApplicationController.currentFirstName = account.getFirstName();
                ApplicationController.currentLastName = account.getLastName();
                ApplicationController.currentEmail = account.getEmail();
                ApplicationController.currentPhoneNumber = account.getPhoneNumber();
                ApplicationController.currentID = account.getUUID();
                ApplicationController.homeController.setupLabel();
                ApplicationController.profileController.setupLabel();

                // Reload imports (adjust this to load from database if necessary)
                ApplicationController.appointmentController.importAppointments();

                // Show login prompt
                JOptionPane.showMessageDialog(null, 
                    "<html><body style='text-align: center; color: green; background-color: #e0ffe0; padding: 10px; border: 2px solid green;'>"
                    + "<h2>🎉 Επιτυχής Σύνδεση!</h2>"
                    + "<p>Καλώς ήρθατε στην εφαρμογή!</p>"
                    + "</body></html>", 
                    "Καλώς Ήρθατε", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Show error message if an account is not found
                JOptionPane.showMessageDialog(null, 
                    "<html><body style='text-align: center;'>"
                    + "<h2>Ουπςς! 😬</h2>"
                    + "<p>Η ηλεκτρονική σας διεύθυνση ή ο κωδικός πρόσβασής σας είναι λανθασμένος.</p>"
                    + "<p>Παρακαλώ δοκιμάστε ξανά.</p>"
                    + "</body></html>", 
                    "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                loginScreen.getPasswordField().setText("");
            }

        } catch (SQLException e) {
            System.err.println("An error occurred: " + e);
        }
    }

    // Show and hide password
    private void showPassword() {
        if (loginScreen.getShowPassword().isSelected())
            loginScreen.getPasswordField().setEchoChar((char) 0);
        else
            loginScreen.getPasswordField().setEchoChar('•');
    }
}
