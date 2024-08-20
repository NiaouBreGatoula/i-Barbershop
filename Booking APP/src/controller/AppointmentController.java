package controller;

import model.Appointment;

import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentController {

    public void importAppointments() {
        try {
            // Fetch appointments from the database
            ArrayList<Appointment> appointmentsList = DatabaseConnection.fetchAppointments();

            // Clear existing appointments
            ApplicationController.appointments.clear();

            // Add fetched appointments to ApplicationController's appointments list
            ApplicationController.appointments.addAll(appointmentsList);

            // Clear current appointments
            ApplicationController.currentAppointments.clear();

            // Filter appointments for the current user
            for (Appointment appointment : appointmentsList) {
                if (appointment.getUniqueID().equals(ApplicationController.currentID)) {
                    ApplicationController.currentAppointments.add(appointment);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
