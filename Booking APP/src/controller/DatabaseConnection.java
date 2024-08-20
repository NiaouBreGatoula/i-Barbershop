package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Appointment;
import model.Account;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/ibarbershop";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static ArrayList<Appointment> fetchAppointments() throws SQLException {
        ArrayList<Appointment> appointmentsList = new ArrayList<>();
        String query = "SELECT * FROM appointments";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                appointmentsList.add(new Appointment(
                        resultSet.getString("top_length"),
                        resultSet.getString("buzz_cut"),
                        resultSet.getString("thin_out"),
                        resultSet.getString("fade_type"),
                        resultSet.getString("taper_type"),
                        resultSet.getString("side_type"),
                        resultSet.getString("design"),
                        resultSet.getString("beard"),
                        resultSet.getString("line_up"),
                        resultSet.getString("appointment_date"),
                        resultSet.getString("time"),
                        resultSet.getString("cost"),
                        resultSet.getString("unique_id")
                ));
            }
        }
        return appointmentsList;
    }

    // Existing methods...

    public static boolean isEmailExists(String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM accounts WHERE email = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public static void insertAccount(Account account) throws SQLException {
        String query = "INSERT INTO accounts (first_name, last_name, email, password, phone_number, uuid) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, account.getFirstName());
            statement.setString(2, account.getLastName());
            statement.setString(3, account.getEmail());
            statement.setString(4, account.getPassword()); // Ensure password is hashed before storing
            statement.setString(5, account.getPhoneNumber());
            statement.setString(6, account.getUUID());
            statement.executeUpdate();
        }
    }

    public static ArrayList<Account> fetchAccounts() throws SQLException {
        ArrayList<Account> accountsList = new ArrayList<>();
        String query = "SELECT * FROM accounts";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                accountsList.add(new Account(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("uuid")
                ));
            }
        }
        return accountsList;
    }

    public static Account verifyAccount(String email, String password) throws SQLException {
        String query = "SELECT * FROM accounts WHERE email = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Account(
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("uuid")
                    );
                }
            }
        }
        return null;
    }

    public static void insertAppointment(Appointment appointment) throws SQLException {
        String query = "INSERT INTO appointments (top_length, buzz_cut, thin_out, fade_type, taper_type, side_type, design, beard, line_up, appointment_date, time, cost, unique_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, appointment.getTopLength());
            statement.setString(2, appointment.getBuzzCut());
            statement.setString(3, appointment.getThinOut());
            statement.setString(4, appointment.getFadeType());
            statement.setString(5, appointment.getTaperType());
            statement.setString(6, appointment.getSideType());
            statement.setString(7, appointment.getDesign());
            statement.setString(8, appointment.getBeard());
            statement.setString(9, appointment.getLineUp());
            statement.setString(10, appointment.getAppointmentDate());
            statement.setString(11, appointment.getTime());
            statement.setString(12, appointment.getCost());
            statement.setString(13, appointment.getUniqueID());
            statement.executeUpdate();
        }
    }
}

