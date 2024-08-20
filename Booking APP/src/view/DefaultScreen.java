package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class DefaultScreen extends JFrame {

    // Screen Size Fields
    public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    // Main panel
    private JPanel mainPanel;

    // Example label
    private JLabel exampleLabel;

    public DefaultScreen() {
        initializeScreen();
        initializeComponents();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("Window resized: " + getSize());
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
    }

    private void initializeScreen() {
        // Setup screen
        setTitle("i-Barber Uniwa");
        setSize(WIDTH, HEIGHT);
        getContentPane().setBackground(new Color(102, 153, 204));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Set window icon
        setIconImage(new ImageIcon("images/logo-2.png").getImage());
    }

    private void initializeComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        exampleLabel = new JLabel("Welcome to i-Barber Uniwa");
        exampleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        exampleLabel.setForeground(Color.WHITE);
         System.out.println("opalakai");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(exampleLabel, gbc);

        mainPanel.setPreferredSize(new Dimension(800, 600));
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
			System.out.println("op");
            DefaultScreen screen = new DefaultScreen();
            screen.setVisible(true);
        });
    }
}
