package model;

public class Account {

    // Fields
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String uuid;

    // Constructor
    public Account(String firstName, String lastName, String email, String password, String phoneNumber, String uuid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.uuid = uuid;
    }

    // Getters
    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getEmail() { return email; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getPassword() { return password; } // Ensure password is hashed before storing

    public String getUUID() { return uuid; }

    // toString method
    @Override
    public String toString() {
        return firstName + ", " + lastName + ", " + email + ", " + phoneNumber + ", " + uuid;
    }
}
