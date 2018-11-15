//import javafx.scene.control.DatePicker;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * ShapePanel class displays the shapes
 */
public class UserPanel extends JPanel {
    private Box textFieldBox;
    private Box checkBox;
    private JTextField addressFrom;
    private JTextField addressTo;
    private JTextField placeName;
    private JFormattedTextField date;
    private JFormattedTextField time;
    private JSlider importantScale;
    private JButton processButton;
    private ArrayList<Listener> listeners;
    private JRadioButton driveJB;
    private JRadioButton bikeJB;
    private JRadioButton walkJB;
    private JRadioButton transitJB;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton editButton;
    private Controller controller;
    private GregorianCalendar eventDate;

    /**
     * Constructor
     *
     * @param size size of the panel
     */
    public UserPanel(int size) {
        listeners = new ArrayList<Listener>();
//            controller.addListener(this);
        this.controller = controller;
        this.setLayout(new BorderLayout());
        allocateTextFields();
        allocateCheckBox();
        this.add(textFieldBox, BorderLayout.NORTH);
        this.add(checkBox, BorderLayout.EAST);
        addSlider();
        addButtons();
        saveButton = new JButton("Save Time");
        cancelButton = new JButton("Cancel");
        editButton = new JButton("Edit");
    }

    private void addButtons() {
        processButton = new JButton("Add ");
        this.add(processButton, BorderLayout.WEST);
        processButton.addActionListener(ActionEvent -> {
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
        //TOdO: check to see if the same date is occupied.
        //TODO: check the date again.
    }


    public void setBackToDefault() {
        driveJB.setSelected(true);
        addressFrom.setText("");
        addressTo.setText("");
        placeName.setText("");
        date.setText("");
        time.setText("");
        importantScale.setValue(0);
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
            return "TRANSITING";
    }

    /**
     * adds a listener
     *
     * @param l adding listener
     */
    public void addListener(Listener l) {
        listeners.add(l);
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

    public void addActionDateTextField(ActionListener e) {
        date.addActionListener(e);
    }

    public void addActionTimeTextField(ActionListener e) {
        date.addActionListener(e);
    }


    private void addSlider() {
        importantScale = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
        importantScale.setMinorTickSpacing(1);
        importantScale.setMajorTickSpacing(1);
        importantScale.setPaintTicks(true);
        importantScale.setPaintLabels(true);
        add(importantScale, BorderLayout.SOUTH);
//            importantScale.addChangeListener(ChangeListener -> {
//                int knobValue = importantScale.getValue();
////            car.update(knobValue - baseValue);
////            label.repaint();
//            });
    }


    private void notifyListener(ChangedObject object) {
        for (Listener l : listeners) {
            l.update(object);
        }
    }

    private void confirmToListener() {
        for (Listener l : listeners) {
//            l.update();
        }
    }

    public void addActionEditButton(ActionListener e) {
        editButton.addActionListener(e);
    }

    public void addActionSaveButton(ActionListener e) {
        saveButton.addActionListener(e);

    }

    public void addActionCancelButton(ActionListener e) {
        cancelButton.addActionListener(e);

    }

    public void popUpMessage() {
        JButton[] buttonList = {saveButton, cancelButton, editButton};
//        JSlider addMoreTime = new JSlider();
        Box messageBox = Box.createVerticalBox();
        messageBox.add(saveButton);
        messageBox.add(cancelButton);
//        messageBox.add(addMoreTime);
        JDialog.setDefaultLookAndFeelDecorated(true);
        JOptionPane.showOptionDialog(null, "Put Result Here", "Recommend Ready Time",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttonList, saveButton);
    }

}
