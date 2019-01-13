import javax.swing.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Class PopUpFrame allows the user to cancel, edit, or save an event once they have inputted all the user information.
 * The slider allows them to add more time if needed.
 */
public class RealAlarmTimePopUp extends PopUpFrame {
    private JButton adjustButton;
    private JTextField timeText;
    private static JLabel adjustedMinute= new JLabel("0 minutes earlier/later");
    private int requestedTotalMinAdj;
    private CalendarEvent currentEvent;
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM dd yyyy - HH:mm");
    private GregorianCalendar calendar;

    /**
     * Allows user to add or subtract time from the estimated time that is generated.
     */
    public RealAlarmTimePopUp() {
        super("", false);
        super.setBounds(0, 0, 450, 280);
        setVisible(false);
        addActionCancelButton(ActionEvent -> hidePopUp());
    }

    /**
     * Create more buttons if needed
     */
    @Override
    protected void createExtraButton() {
        adjustButton = new JButton("Adjust Ready Time");
        buttonsBox.add(adjustButton);
        addActionAdjustButton(e -> {
            int totalRequestedMin = (int) Double.parseDouble(timeText.getText());
            calendar = currentEvent.getAlarmTime();
            adjustedMinute.setText(getAlarmText(totalRequestedMin));
            calendar.add(Calendar.MINUTE, +totalRequestedMin);
            detailMessage.setText(currentEvent.getEventInfo() +
                    String.format("\n\nEdited Alarm: %s", dateTimeFormat.format(calendar.getTime())));

        });
    }

    /**
     * Creates a slider that allows the user to add or subtract time form the estimated time that is generated.
     */
    protected void createSlider() {
        Box titleBox = Box.createHorizontalBox();
        Box titleBox2 = Box.createHorizontalBox();
        timeText = new JTextField("0");
        text = new JLabel("Adjust Alarm Time in Min: ");
        JLabel text2 = new JLabel("Alarm should be: ");
        slider = new JSlider(JSlider.HORIZONTAL, -30, 30, 0);
        slider.addChangeListener(e -> timeText.setText("" + slider.getValue()));
        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        titleBox.add(text);
        titleBox.add(timeText);

        titleBox2.add(text2);
        titleBox2.add(adjustedMinute);

        sliderBox.add(titleBox);
        sliderBox.add(titleBox2);
        sliderBox.add(slider);

    }

    private String getAlarmText(int numOfMin) {
        requestedTotalMinAdj = numOfMin;
        int min = Math.abs(requestedTotalMinAdj % 60);
        int hour = requestedTotalMinAdj / 60;
        return String.format("%d %s %d %s %s", hour, hour > 1 ? "hours" : "hour",
                min, min > 1 ? "minutes" : "minute", numOfMin < 0 ? "earlier" : "later");
    }

    public CalendarEvent hidePopUp() {
        setVisible(false);
        CalendarEvent temp = currentEvent;
        currentEvent = null;
        calendar = null;
        return temp;
    }

    /**
     * Makes the popUp class visible
     *
     * @param event
     */
    public void showPopUp(CalendarEvent event) {
        currentEvent = event;
        detailMessage.setText(currentEvent.getEventInfo());
        requestedTotalMinAdj = 0;
        timeText.setText("");
        adjustedMinute.setText("0 minutes earlier/later");
        super.setVisible(true);
    }

    /**
     * Gets slider value
     *
     * @return slider value
     */
    public int getSliderValue() {
        int value = slider.getValue();
        slider.setValue(0);
        return value;
    }

    public int getRequestedTotalMinAdj() {
        return requestedTotalMinAdj;
    }


    /**
     * Adds action to button.
     *
     * @param e action when button is clicked
     */
    @Override
    public void addActionAdjustButton(ActionListener e) {
        adjustButton.addActionListener(e);
    }
}

