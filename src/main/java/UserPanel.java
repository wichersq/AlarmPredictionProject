//import javafx.scene.control.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * ShapePanel class displays the shapes
 */
public class UserPanel extends JPanel {
    private Box textFieldBox = Box.createVerticalBox();
    private Box checkBox = Box.createVerticalBox();

    private JTextField addressFrom;
    private JTextField addressTo;
    private JTextField placeName;
    private JTextField date;
    private JTextField time;

    private JSlider importantScale;
    //        private EventModel model;
    private JButton processButton;
    private ArrayList<Listener> listeners;
    private Controller controller;
    //        private DatePicker datePicker;
    private JRadioButton driveJB;
    private JRadioButton bikeJB;
    private JRadioButton walkJB;
    private JRadioButton transitJB;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton editButton;


    /**
     * Constructor
     *
     * @param size size of the panel
     */
    public UserPanel(int size) {
        listeners = new ArrayList<Listener>();
        super.setSize(size, size);
//            controller.addListener(this);
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
        processButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ActionEvent) {
                notifyListener(gatherInfo());
            }
        });
    }

    private ChangedObject gatherInfo() {
        String from = addressFrom.getText();
        String to = addressTo.getText();
        String name = placeName.getText();
//            String[] tempString = dateTime.getText().split(",");
        String dateOfEvent = "Date";
//                    tempString[0];
        String arrivalTime = "Time";
//                    tempString[1];
        GregorianCalendar date = new GregorianCalendar() ;
        try {
            date = createdDate(dateOfEvent, arrivalTime);
        }catch(InvalidObjectException e){
            System.out.println("Print wrong type please type in again");
        }
        String trans = transportPick();
        int scale = importantScale.getValue();
        return new ChangedObject(from, to, name, date, trans, scale);
    }

    private GregorianCalendar createdDate(String convertingDate, String covertingTime)
            throws InvalidObjectException {
        String[] dateArr = convertingDate.split("/");
        String[] timeArr = covertingTime.split(":");
        if(dateArr.length != 3) {
            throw new InvalidObjectException("Invalid Date");
        }
        if(timeArr.length == 2) {
            throw new InvalidObjectException("Invalid Time");
        }
        int month = Integer.parseInt(dateArr[0]);
        int date = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);
        int hour = Integer.parseInt(timeArr[0]);
        int min = Integer.parseInt(timeArr[1]);
        GregorianCalendar newCal = new GregorianCalendar(year,month,date,hour,min);
        return newCal;
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

    private String transportPick() {

        if (bikeJB.isSelected()) {
            return "BIKE";
        } else if (driveJB.isSelected()) {
            return "DRIVE";
        } else if (walkJB.isSelected()) {
            return "WALK";
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
    }

    /**
     * Adds a mouse listener for the panel
     */
    private void allocateTextFields() {
        JLabel from = new JLabel("From:");
        addressFrom = new JTextField();

        JLabel to = new JLabel("To:");
        addressTo = new JTextField();

        JLabel nameLabel = new JLabel("Place Name:");
        placeName = new JTextField();

        JLabel dateLabel = new JLabel("Date(mm/dd/yyyy): ");
        date = new JTextField();

        JLabel timeLabel = new JLabel("Time(hh:mm): ");
        time = new JTextField();

        textFieldBox.add(from);
        textFieldBox.add(addressFrom);

        textFieldBox.add(to);
        textFieldBox.add(addressTo);

        textFieldBox.add(nameLabel);
        textFieldBox.add(placeName);

        textFieldBox.add(dateLabel);
        textFieldBox.add(date);

        textFieldBox.add(timeLabel);
        textFieldBox.add(time);
    }

    private void allocateCheckBox() {
        driveJB = new JRadioButton("Drive");
        driveJB.setSelected(true);
        bikeJB = new JRadioButton("Bike");
        walkJB = new JRadioButton("Walk");
        transitJB = new JRadioButton("Transit");
        ButtonGroup group = new ButtonGroup();
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
