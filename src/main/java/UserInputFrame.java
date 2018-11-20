

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 */
public class UserInputFrame extends JFrame {
    private JPanel panel;
    private ArrayList<Listener> listeners;
    private Box textFieldBox;
    private Box checkBox;
    private Box buttonBox;
    private Box sliderBox;
    private JTextField addressFrom;
    private JTextField addressTo;
    private JTextField placeName;
    private JFormattedTextField date;
    private JFormattedTextField time;
    private JSlider importantScale;
    private JButton addButton;
    private JButton showButton;
    private JRadioButton driveJB;
    private JRadioButton bikeJB;
    private JRadioButton walkJB;
    private JRadioButton transitJB;
    private Controller controller;
    private GregorianCalendar eventDate;

    /**
     * Constructor
     *
     * @param size size of the panel
     */
    public UserInputFrame(int size) {
        super.setLayout(new BorderLayout());
        super.setBounds(0, 0, size, size);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listeners = new ArrayList<Listener>();
        createPanel();
        this.add(panel);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void createPanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        allocateTextFields();
        allocateCheckBox();
        panel.add(textFieldBox, BorderLayout.NORTH);
        panel.add(checkBox, BorderLayout.EAST);
        addSlider();
        addButtons();

    }

    private void addButtons() {
        buttonBox = Box.createHorizontalBox();
        addButton = new JButton("Add ");
        showButton = new JButton("Show List");
        buttonBox.add(addButton);
        buttonBox.add(showButton);
        panel.add(buttonBox, BorderLayout.SOUTH);
        addButton.addActionListener(ActionEvent -> {
            try {
                createDateTime(date.getText(), time.getText());
                ChangedObject changeOb = gatherInfo();
                notifyListener(changeOb);
            } catch (NumberFormatException e) {
                popUpWarningMessage();
            }
        });
    }


    private ChangedObject gatherInfo() {
        String from = addressFrom.getText();
        String to = addressTo.getText();
        String name = placeName.getText();
        String trans = transportPick();
        int scale = importantScale.getValue();

        return new ChangedObject(from, to, name, eventDate, trans, scale);
    }

    private void createDateTextField() {
        MaskFormatter dateMask = null;
        try {
            dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = new JFormattedTextField(dateMask);

    }

    private void createTimeTextField() {
        MaskFormatter timeMask = null;
        try {
            timeMask = new MaskFormatter("##:##");
            timeMask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }

        time = new JFormattedTextField(timeMask);
    }

    private void createDateTime(String convertingDate, String convertingTime)
            throws NumberFormatException {
        String[] dateArr = convertingDate.split("/");
        int month = Integer.parseInt(dateArr[0]);
        int date = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);

        String[] timeArr = convertingTime.split(":");
        int hour = Integer.parseInt(timeArr[0]);
        int min = Integer.parseInt(timeArr[1]);

        eventDate = new GregorianCalendar(year, month - 1, date, hour, min);
        if (eventDate.before(Calendar.getInstance()) || controller.checkIfTimeOccupied(eventDate.toString())) {
            throw new NumberFormatException("Date Invalid");
        }
    }


    public void setBackToDefault() {
        driveJB.setSelected(true);
        addressFrom.setText("");
        addressTo.setText("");
        placeName.setText("");
        date.setText("");
        time.setText("");
        importantScale.setValue(3);
    }

    private void popUpWarningMessage() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        JOptionPane.showConfirmDialog(null,
                "The time is invalid or already occupied. Please try different time", "Invalid Event Time",
                JOptionPane.DEFAULT_OPTION);
        date.setText("");
        time.setText("");
    }

    private String transportPick() {
        if (bikeJB.isSelected()) {
            return "BIKING";
        } else if (driveJB.isSelected()) {
            return "DRIVING";
        } else if (walkJB.isSelected()) {
            return "WALKING";
        } else
            return "TRANSIT";
    }

    /**
     * adds a listener
     *
     * @param l adding listener
     */
    public void addListener(Listener l) {

        listeners.add(l);
        if (l.getClass() == Controller.class) {
            this.controller = (Controller) l;
        }
    }

    /**
     * Adds a mouse listener for the panel
     */
    private void allocateTextFields() {
        addressFrom = new JTextField();
        addressTo = new JTextField();
        placeName = new JTextField();
        createDateTextField();
        createTimeTextField();
        addTextFieldsToBox();
    }

    public void addActionShowButton(ActionListener e) {
        showButton.addActionListener(e);
    }

    private void addTextFieldsToBox() {
        textFieldBox = Box.createVerticalBox();
        textFieldBox.add(new JLabel("From:"));
        textFieldBox.add(addressFrom);

        textFieldBox.add(new JLabel("To:"));
        textFieldBox.add(addressTo);

        textFieldBox.add(new JLabel("Place Name:"));
        textFieldBox.add(placeName);
        textFieldBox.add(new JLabel("Date(mm/dd/yyyy): "));
        textFieldBox.add(date);
        textFieldBox.add(new JLabel("Time(hh:mm): "));
        textFieldBox.add(time);
    }

    private void allocateCheckBox() {
        checkBox = Box.createVerticalBox();
        ButtonGroup group = new ButtonGroup();

        driveJB = new JRadioButton("Driving");
        bikeJB = new JRadioButton("Biking");
        walkJB = new JRadioButton("Walking");
        transitJB = new JRadioButton("Transit");

        driveJB.setSelected(true);


        group.add(bikeJB);
        group.add(driveJB);
        group.add(walkJB);
        group.add(transitJB);

        checkBox.add(driveJB);
        checkBox.add(bikeJB);
        checkBox.add(walkJB);
        checkBox.add(transitJB);
    }


    private void addSlider() {
        sliderBox = Box.createVerticalBox();
        importantScale = new JSlider(JSlider.HORIZONTAL, 1, 6, 3);
        importantScale.setMinorTickSpacing(1);
        importantScale.setMajorTickSpacing(1);
        importantScale.setPaintTicks(true);
        importantScale.setPaintLabels(true);
        sliderBox.add(new JLabel("Importance Scale"));
        sliderBox.add(importantScale);
        add(sliderBox, BorderLayout.CENTER);

    }


    private void notifyListener(ChangedObject object) {
        for (Listener l : listeners) {
            l.update(object);
        }
    }


}
