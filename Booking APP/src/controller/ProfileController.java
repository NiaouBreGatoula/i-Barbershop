package controller;

import view.ProfileScreen;

public class ProfileController {

    private ProfileScreen profileScreen; // Access ProfileScreen

    // Constructor
    public ProfileController(ProfileScreen profileScreen) {
        this.profileScreen = profileScreen;
    }

    // Profile labels with user data
    public void setupLabel() {
        profileScreen.getNameLabel().setText("Όνοματεπώνυμο: " + ApplicationController.currentFirstName +
                " " + ApplicationController.currentLastName);
        profileScreen.getEmailLabel().setText("Ηλ. Διεύθυνση: " + ApplicationController.currentEmail);
        profileScreen.getPhoneNumberLabel().setText("Κινητό Τηλέφωνο: " + ApplicationController.currentPhoneNumber);
    }
}
