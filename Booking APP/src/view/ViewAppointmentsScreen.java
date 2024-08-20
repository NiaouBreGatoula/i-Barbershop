package view;

import controller.ApplicationController;
import model.Appointment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ViewAppointmentsScreen extends Screen {

    // Fields
    private JLabel titleLabel = new JLabel(new ImageIcon("images/viewAppointmentsTitle.png"));
    private JList<Appointment> appointmentList = new JList<>(ApplicationController.appointmentListModel);
    private JScrollPane appointmentScroll = new JScrollPane(appointmentList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    public ViewAppointmentsScreen() {
        getBackButton().setVisible(false);
        setBackground(new Color(56, 67, 84));
        setLayout(new BorderLayout());

        // Create layered pane for title and appointments
        JLayeredPane layeredPane = new JLayeredPane();
        add(layeredPane, BorderLayout.CENTER);

        // Set up the title label
        titleLabel.setBounds(680, 0, 1600, 200);
        layeredPane.add(titleLabel, JLayeredPane.PALETTE_LAYER);

        // Set up the appointment list and scroll pane
        appointmentList.setFont(new Font("Verdana", Font.PLAIN, 20));
        appointmentList.setBackground(new Color(209, 204, 192)); // Light color for the list background
        appointmentList.setForeground(new Color(60, 60, 60)); // Darker color for text
        appointmentScroll.setBounds(50, 250, 1500, 600); // Adjusted position
        layeredPane.add(appointmentScroll, JLayeredPane.DEFAULT_LAYER);

        // Add ComponentListener to handle resizing
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents(layeredPane);
            }
        });
    }

    private void resizeComponents(JLayeredPane layeredPane) {
        int width = getWidth();
        int height = getHeight();

        // Adjust title label size and position
        titleLabel.setBounds((width - 1600) / 2, 0, 1600, 200);

        // Adjust appointment scroll pane size and position
        appointmentScroll.setBounds(50, 250, width - 100, height - 300); // Adjusted position and size
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("View Appointments Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);
        frame.add(new ViewAppointmentsScreen());
        frame.setVisible(true);
    }
}
