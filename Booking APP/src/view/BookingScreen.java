package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Hashtable;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import com.toedter.calendar.JCalendar;
import java.util.Date;

public class BookingScreen extends Screen {

    // Πεδία
    private JCalendar calendar = new JCalendar();
    private Date today = new Date();
    private JLabel bookAppointmentLabel = new JLabel(new ImageIcon("images/bookAppointmentTitle.png"));
    private JLabel selectDateLabel = new JLabel("Επιλέξτε ημερομηνία");
    private JLabel selectCutLabel = new JLabel("Κούρεμα (€10)");
    private JLabel additionalLabel = new JLabel("Πρόσθετα");
    private JLabel topLabel = new JLabel("Πάνω Μέρος");
    private JLabel sideLabel = new JLabel("Στο Πλάι");
    private JLabel guardLabel = new JLabel("Υψηλη Σκαλα    Χαμηλη Σκάλα");

    private Hashtable<Integer, JLabel> lengthTable = new Hashtable<>();
    private JSlider topLengthSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
    private JCheckBox buzzCutCheckBox = new JCheckBox("Ξυριστική Μηχ.", false);
    private JCheckBox thinOutCheckBox = new JCheckBox("Ψαλίδι", false);
    private JRadioButton fadeButton = new JRadioButton("Fade/Σβήσιμο", false);
    private JRadioButton taperButton = new JRadioButton("Tape/Σταδιακό", false);
    private JCheckBox skinFadeCheckBox = new JCheckBox("Φαλτσέτα");
    private ButtonGroup sideType = new ButtonGroup();
    private JComboBox<String> haircutSelect = new JComboBox<>();
    private JLabel sideToLabel = new JLabel(" σε");
    private JComboBox<String> sideOneSelect = new JComboBox<>();
    private JComboBox<String> sideTwoSelect = new JComboBox<>();
    private JCheckBox designCheckBox = new JCheckBox("Σχέδιο (+€10 - €100)", false);
    private JCheckBox beardCheckBox = new JCheckBox("Γένια (+€5)", false);
    private JCheckBox lineUpCheckBox = new JCheckBox("Χαλαρ. Μασάζ (+€15)", false);
    private JButton bookNowButton = new JButton("Κάντε κράτηση");

    private String[] guardOptions = new String[] {
            "- Επιλογή -", "0", "0.5", "1.0", "1.5", "2.0", "3.0", "4.0", "5.0", "6.0", "7.0", "8.0",
    };

    private JPanel selectionPanel;
    private JPanel topPanel;
    private JPanel sidePanel;
    private JPanel additionalPanel;

    public BookingScreen() {

        getBackButton().setVisible(false);
        setBackground(new Color(46, 54, 61));
        setLayout(null);

        sideType.add(taperButton);
        sideType.add(fadeButton);

        bookAppointmentLabel.setBounds(700, 0, 1600, 200);
        add(bookAppointmentLabel);

        // Δημιουργία panel για τα στοιχεία επιλογής
        selectionPanel = new JPanel();
        selectionPanel.setLayout(null);
        selectionPanel.setBackground(new Color(229, 224, 214));
        selectionPanel.setBounds(100, 150, 1700, 800); // Increased width and height
        selectionPanel.setBorder(BorderFactory.createLineBorder(new Color(30, 75, 135), 2));
        add(selectionPanel);

        // Δημιουργία panel για το πάνω μέρος
        topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(229, 224, 214));
        topPanel.setBounds(50, 60, 400, 200);
        topPanel.setBorder(BorderFactory.createTitledBorder("✂️"));
        selectionPanel.add(topPanel);

        topLabel.setBounds(20, 20, 200, 30);
        topLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        topPanel.add(topLabel);

        lengthTable.put(0, new JLabel("0"));
        lengthTable.put(1, new JLabel("0.5"));
        lengthTable.put(2, new JLabel("1"));
        lengthTable.put(3, new JLabel("1.5"));
        lengthTable.put(4, new JLabel("2"));
        lengthTable.put(5, new JLabel("2.5"));
        lengthTable.put(6, new JLabel("3"));
        lengthTable.put(7, new JLabel("3.5"));
        lengthTable.put(8, new JLabel("4"));
        lengthTable.put(9, new JLabel("4.5"));
        lengthTable.put(10, new JLabel("5"));
        topLengthSlider.setLabelTable(lengthTable);

        topLengthSlider.setBounds(20, 60, 360, 50);
        topLengthSlider.setBackground(new Color(229, 224, 214));
        topLengthSlider.setMajorTickSpacing(5);
        topLengthSlider.setPaintTicks(true);
        topLengthSlider.setPaintLabels(true);
        topPanel.add(topLengthSlider);

        buzzCutCheckBox.setBounds(20, 120, 200, 30);
        buzzCutCheckBox.setBackground(new Color(229, 224, 214));
        buzzCutCheckBox.setFont(new Font("Verdana", Font.PLAIN, 17));
        topPanel.add(buzzCutCheckBox);

        thinOutCheckBox.setBounds(20, 160, 200, 30);
        thinOutCheckBox.setBackground(new Color(229, 224, 214));
        thinOutCheckBox.setFont(new Font("Verdana", Font.PLAIN, 17));
        topPanel.add(thinOutCheckBox);

        // Δημιουργία panel για το πλάι
        sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBackground(new Color(229, 224, 214));
        sidePanel.setBounds(460, 60, 500, 220);
        sidePanel.setBorder(BorderFactory.createTitledBorder("✂️"));
        selectionPanel.add(sidePanel);

        sideLabel.setBounds(20, 20, 200, 30);
        sideLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        sidePanel.add(sideLabel);

        fadeButton.setBounds(20, 60, 150, 30);
        fadeButton.setBackground(new Color(229, 224, 214));
        fadeButton.setFont(new Font("Verdana", Font.PLAIN, 17));
        sidePanel.add(fadeButton);

        taperButton.setBounds(180, 60, 150, 30);
        taperButton.setBackground(new Color(229, 224, 214));
        taperButton.setFont(new Font("Verdana", Font.PLAIN, 17));
        sidePanel.add(taperButton);

        skinFadeCheckBox.setBounds(340, 60, 150, 30);
        skinFadeCheckBox.setBackground(new Color(229, 224, 214));
        skinFadeCheckBox.setFont(new Font("Verdana", Font.PLAIN, 17));
        // sidePanel.add(skinFadeCheckBox);

        guardLabel.setBounds(20, 100, 250, 30);
        guardLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        sidePanel.add(guardLabel);

        sideOneSelect.setBounds(20, 140, 90, 30);
        sideOneSelect.setEnabled(false);
        sideOneSelect.setBackground(new Color(229, 224, 214));
        sideOneSelect.setFont(new Font("Verdana", Font.PLAIN, 15));
        sideOneSelect.setModel(new DefaultComboBoxModel<>(guardOptions));
        sidePanel.add(sideOneSelect);

        sideToLabel.setBounds(120, 140, 40, 30);
        sideToLabel.setFont(new Font("Verdana", Font.PLAIN, 17));
        sidePanel.add(sideToLabel);

        sideTwoSelect.setBounds(170, 140, 90, 30);
        sideTwoSelect.setEnabled(false);
        sideTwoSelect.setBackground(new Color(229, 224, 214));
        sideTwoSelect.setFont(new Font("Verdana", Font.PLAIN, 15));
        sideTwoSelect.setModel(new DefaultComboBoxModel<>(guardOptions));
        sidePanel.add(sideTwoSelect);

        // Προσθήκη του haircutSelect στο sidePanel
        haircutSelect.setBounds(20, 180, 240, 30);
        haircutSelect.setBackground(new Color(229, 224, 214));
        haircutSelect.setFont(new Font("Verdana", Font.PLAIN, 17));
        haircutSelect.addItem("Επιλέξτε κούρεμα!");
        sidePanel.add(haircutSelect);

        // Δημιουργία panel για τα πρόσθετα
        additionalPanel = new JPanel();
        additionalPanel.setLayout(null);
        additionalPanel.setBackground(new Color(229, 224, 214));
        additionalPanel.setBounds(50, 300, 1400, 200); // Moved below topPanel and sidePanel
        additionalPanel.setBorder(BorderFactory.createTitledBorder("✂️"));
        selectionPanel.add(additionalPanel);

        additionalLabel.setBounds(20, 20, 200, 30);
        additionalLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        additionalPanel.add(additionalLabel);

        designCheckBox.setBounds(20, 60, 250, 30);
        designCheckBox.setBackground(new Color(229, 224, 214));
        designCheckBox.setFont(new Font("Verdana", Font.PLAIN, 13));
        additionalPanel.add(designCheckBox);

        beardCheckBox.setBounds(20, 100, 200, 30);
        beardCheckBox.setBackground(new Color(229, 224, 214));
        beardCheckBox.setFont(new Font("Verdana", Font.PLAIN, 13));
        additionalPanel.add(beardCheckBox);

        lineUpCheckBox.setBounds(20, 140, 200, 30);
        lineUpCheckBox.setBackground(new Color(229, 224, 214));
        lineUpCheckBox.setFont(new Font("Verdana", Font.PLAIN, 13));
        additionalPanel.add(lineUpCheckBox);

        selectCutLabel.setBounds(50, 20, 400, 30);
        selectCutLabel.setFont(new Font("Verdana", Font.BOLD, 25));
        selectionPanel.add(selectCutLabel);

        // Κουμπί εγγραφής
        bookNowButton.setBackground(new Color(30, 75, 135));
        bookNowButton.setForeground(Color.WHITE);
        bookNowButton.setFont(new Font("Verdana", Font.PLAIN, 30));
        bookNowButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        bookNowButton.setFocusPainted(false);
        bookNowButton.setBounds(213, 1000, 1505, 50); // Adjusted for the new selectionPanel height
        add(bookNowButton);
        bookNowButton.setUI(new BasicButtonUI() {
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

        // Add ComponentListener to handle resizing
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });
    }

    public void resizeComponents() {
        int width = getWidth();
        int height = getHeight();

        // Adjust bookAppointmentLabel size and position
        bookAppointmentLabel.setBounds((width - 1600) / 2, 0, 1600, 200);

        // Adjust selectionPanel size and position
        selectionPanel.setBounds(100, 150, width - 200, height - 300);

        // Adjust topPanel size and position
        topPanel.setBounds(50, 60, selectionPanel.getWidth() / 3 - 20, 200);

        // Adjust sidePanel size and position
        sidePanel.setBounds(60 + selectionPanel.getWidth() / 3, 60, selectionPanel.getWidth() / 3 - 70, 220);

        // Adjust additionalPanel size and position
        additionalPanel.setBounds(50, 300, selectionPanel.getWidth() / 3 - 20, 200);

        // Adjust bookNowButton size and position
        bookNowButton.setBounds(100, height - 100, width - 200, 50);
    }

    public void enableCalendar() {
        // Adjust calendar size and position
        selectDateLabel.setBounds(2 * (selectionPanel.getWidth() / 3), 20, selectionPanel.getWidth() / 3 - 20, 50);
        selectDateLabel.setFont(new Font("Verdana", Font.BOLD, 25));
        selectionPanel.add(selectDateLabel);

        calendar.setBounds(2 * (selectionPanel.getWidth() / 3), 120, selectionPanel.getWidth() / 3 - 20, 350);
        calendar.setMinSelectableDate(today);
        selectionPanel.add(calendar);

        // Refresh the panel to display the new components
        selectionPanel.revalidate();
        selectionPanel.repaint();
    }

    public JSlider getTopLengthSlider() { return topLengthSlider; }

    public JCheckBox getBuzzCutCheckBox() { return buzzCutCheckBox; }

    public JCheckBox getThinOutCheckBox() { return thinOutCheckBox; }

    public JRadioButton getFadeButton() { return fadeButton; }

    public JRadioButton getTaperButton() { return taperButton; }

    public JComboBox<String> getHaircutSelect() { return haircutSelect; }

    public JCheckBox getDesignCheckBox() { return designCheckBox; }

    public JCheckBox getBeardCheckBox() { return beardCheckBox; }

    public JCheckBox getLineUpCheckBox() { return lineUpCheckBox; }

    public JButton getBookNowButton() { return bookNowButton; }

    public JCheckBox getSkinFadeCheckBox() { return skinFadeCheckBox; }

    public JComboBox<String> getSideOneSelect() { return sideOneSelect; }

    public JComboBox<String> getSideTwoSelect() { return sideTwoSelect; }

    public JCalendar getCalendar() { return calendar; }
}
