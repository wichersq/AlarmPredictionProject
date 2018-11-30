import javax.swing.*;
import java.awt.event.ActionListener;


/**
 * Class PopUpFrame allows the user to cancel, edit, or save an event once they have inputted all the user information.
 * The slider allows them to add more time if needed.
 */
public class OfflinePopUpFrame extends PopUpFrame {
    private JButton addTimeButton;
    /**
     * Allows user to add or subtract time from the estimated time that is generated.
     */
    public OfflinePopUpFrame(String message) {
       super(message);
    }

    @Override
    protected void createExtraButton() {
        addTimeButton = new JButton("Add Ready Time");
        buttonsBox.add(addTimeButton);
    }


    /**
     * Creates a slider that allows the user to add or subtract time form the estimated time that is generated.
     */
    protected void createSlider() {
        timeText = new JLabel("Time adjust: 0");
        text = new JLabel("Can't Predict Alarm. Please add your alarm:");
        slider = new JSlider(JSlider.HORIZONTAL, 0, 2*60, 2);
        slider.addChangeListener(e -> timeText.setText(String.format("Time adjust:%d minutes", (int)slider.getValue())));
        slider.setMinorTickSpacing(10);
        slider.setMajorTickSpacing(20);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        sliderBox.add(text);
        sliderBox.add(timeText);
        sliderBox.add(slider);

    }

    /**
     * Shows the popUp with new message
     * @param alarmStr alarm in string format
     */
    public void showPopUp(String alarmStr) {
        detailMessage.setText(alarmStr);
        slider.setValue(2);
        setVisible(true);
    }

    /**
     * Gets slider value
     * @return slider value
     */
    public int getSliderValue() {
        int value = slider.getValue();
        slider.setValue(0);
        return value;
    }

    @Override
    /**
     * Adds action for the adjustButton
     */
    protected void addActionAdjustButton(ActionListener e) {
        addTimeButton.addActionListener(e);
    }

}


