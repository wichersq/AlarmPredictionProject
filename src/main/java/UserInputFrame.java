import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class UserInputFrame provides the window where the user inputs information of the event they are scheduling.
 */
public class UserInputFrame extends JFrame {
    private JPanel panel;
    private ArrayList<Listener> listeners;
    private Box textFieldBox;
    private Box radioButtonBox;
    private Box buttonBox;
    private Box sliderBox;
    private JTextField addressFrom;
    private JTextField addressTo;
    private JTextField eventName;
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
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        listeners = new ArrayList<Listener>();
        createPanel();
        addCloseWindowOption();
        add(panel, BorderLayout.CENTER);
        setVisible(true);

    }

    /**
     * This window makes sure that the user wants to exit the event they created.
     */

    private void addCloseWindowOption() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Are you sure want to close?", null, JOptionPane.YES_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    controller.saveEventsToFile();
                    System.exit(0);
                }
            }
        });
    }

    /**
     * Creates a new JPanel
     */
    private void createPanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        createTextFields();
        createCheckBox();
        createSliderBar();
        createButtons();
        panel.add(textFieldBox, BorderLayout.NORTH);
        panel.add(radioButtonBox, BorderLayout.EAST);
        panel.add(sliderBox, BorderLayout.CENTER);
        panel.add(buttonBox, BorderLayout.SOUTH);
    }


    /**
     * Creates a format text for field
     * @param format
     * @return
     */

    private JFormattedTextField createFormatField(String format) {
        MaskFormatter timeMask = null;
        try {
            timeMask = new MaskFormatter(format);
            timeMask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new JFormattedTextField(timeMask);
    }


    /**
     * Creates a text field within the panel
     */
    private void createTextFields() {
        addressFrom = new JTextField();
        addressTo = new JTextField();
        eventName = new JTextField();
        date = createFormatField("##/##/####");
        time = createFormatField("##:##");
        addTextFieldsToBox();
    }


    /**
     * Creates text boxes within the window
     */
    private void addTextFieldsToBox() {
        textFieldBox = Box.createVerticalBox();

        textFieldBox.add(new JLabel("Event Name:"));
        textFieldBox.add(eventName);

        textFieldBox.add(new JLabel("From:"));
        textFieldBox.add(addressFrom);

        textFieldBox.add(new JLabel("To:"));
        textFieldBox.add(addressTo);


        textFieldBox.add(new JLabel("Date(mm/dd/yyyy): "));
        textFieldBox.add(date);

        textFieldBox.add(new JLabel("Time(hh:mm): "));
        textFieldBox.add(time);
    }


    /**
     * Creates the checkboxes for the user
     */
    private void createCheckBox() {
        ButtonGroup group = new ButtonGroup();
        radioButtonBox = Box.createVerticalBox();
        driveJB = new JRadioButton("Driving", true);
        bikeJB = new JRadioButton("Biking");
        walkJB = new JRadioButton("Walking");
        transitJB = new JRadioButton("Transit");

        group.add(bikeJB);
        group.add(driveJB);
        group.add(walkJB);
        group.add(transitJB);

        radioButtonBox.add(driveJB);
        radioButtonBox.add(bikeJB);
        radioButtonBox.add(walkJB);
        radioButtonBox.add(transitJB);
    }

    /**
     * Creates a slider bar in the panel
     */
    private void createSliderBar() {
        sliderBox = Box.createVerticalBox();
        importantScale = new JSlider(JSlider.HORIZONTAL, 1, 6, 3);
        importantScale.setMinorTickSpacing(1);
        importantScale.setMajorTickSpacing(1);
        importantScale.setPaintTicks(true);
        importantScale.setPaintLabels(true);
        sliderBox.add(new JLabel("Importance Scale"));
        sliderBox.add(importantScale);


    }

    /**
     * Creates "Add" and "Show List" buttons.
     */
    private void createButtons() {
        buttonBox = Box.createHorizontalBox();
        addButton = new JButton("Add ");
        showButton = new JButton("Show List");
        buttonBox.add(addButton);
        buttonBox.add(showButton);
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

    /**
     * Gathers the starting and ending destinations, name, mode of transportation, and prority of the new event.
     *
     * @return returns the new event
     */
    private ChangedObject gatherInfo() {
        String from = addressFrom.getText();
        String to = addressTo.getText();
        String name = eventName.getText();
        String trans = transportPick();
        int scale = importantScale.getValue();

        return new ChangedObject(from, to, name, eventDate, trans, scale);
    }

    /**
     * Creates a place for the user to put the date and time the event is scheduled.
     *
     * @param convertingDate Scheduled date of the event
     * @param convertingTime Scheduled time of the event
     * @throws NumberFormatException If the user inputs an invalid time or date then the program will notify the user
     */
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
        if (eventDate.before(Calendar.getInstance()) || controller.checkIfTimeOccupied(eventDate)) {
            throw new NumberFormatException("Date Invalid");
        }
    }

    /**
     * Allows the user to erase all the inputted information
     */

    public void setBackToDefault() {
        driveJB.setSelected(true);
        addressFrom.setText("");
        addressTo.setText("");
        eventName.setText("");
        date.setText("");
        time.setText("");

        try {
            Method setValueMethod = importantScale.getClass().getDeclaredMethod("setValue", int.class);
            setValueMethod.invoke(importantScale, 3);
        } catch (NoSuchMethodException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        } catch (InvocationTargetException x) {
            x.printStackTrace();
        }
    }

    /**
     * Notifies the user if the date or time is invalid.
     */
    private void popUpWarningMessage() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        JOptionPane.showConfirmDialog(null,
                "The time is invalid or already occupied. Please try different time", "Invalid Event Time",
                JOptionPane.DEFAULT_OPTION);
    }

    /**
     * Allows user to pick a mode of transportation
     *
     * @return returns the mode of transportation
     */
    private String transportPick() {
        if (bikeJB.isSelected()) {
            return TransportationFactory.BIKING_TYPE;
        } else if (driveJB.isSelected()) {
            return TransportationFactory.DRIVING_TYPE;
        } else if (walkJB.isSelected()) {
            return TransportationFactory.WALKING_TYPE;
        } else
            return TransportationFactory.TRANSIT_TYPE;
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
     * Adds action to button
     *
     * @param e action when button is clicked
     */
    public void addActionShowButton(ActionListener e) {
        showButton.addActionListener(e);
    }


    /**
     * @param object
     */
    private void notifyListener(ChangedObject object) {
        for (Listener l : listeners) {
            l.update(object);
        }
    }


}
