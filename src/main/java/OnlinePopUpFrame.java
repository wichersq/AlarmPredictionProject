import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class PopUpFrame allows the user to cancel, edit, or save an event once they have inputted all the user information.
 * The slider allows them to add more time if needed.
 */
public class OnlinePopUpFrame extends PopUpFrame {
    private JButton adjustButton;


    /**
     * Allows user to add or subtract time from the estimated time that is generated.
     */
    public OnlinePopUpFrame(String message) {
        super(message);
    }


    @Override
    protected void createExtraButton() {
        adjustButton = new JButton("Adjust Ready Time");
        buttonsBox.add(adjustButton);

    }

    /**
     * Creates a slider that allows the user to add or subtract time form the estimated time that is generated.
     */
    protected void createSlider() {



        timeText = new JLabel();
        timeText.setText("Time adjust: 0");
        text = new JLabel("Adjusting your ready time:");
        slider = new JSlider(JSlider.HORIZONTAL, -30, 30, 0);
        slider.addChangeListener(e -> timeText.setText("Time adjust: " + slider.getValue()));
        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        sliderBox.add(text);
        sliderBox.add(timeText);
        sliderBox.add(slider);

    }

    /**
     * @param alarmStr
     */
    public void showPopUp(String alarmStr) {
        detailMessage.setText(alarmStr);
        slider.setValue(0);
        setVisible(true);
    }

    /**
     * @return returns slider value
     */
    public int getSliderValue() {
        int value = slider.getValue();
        slider.setValue(0);
        return value;
    }

    /**
     * Adds action to button.
     *
     * @param e action when button is clicked
     */
    public void addActionAdjustButton(ActionListener e) {
        adjustButton.addActionListener(e);
    }
    public static void main(String[] args){
        new OnlinePopUpFrame("hi\nhi\nhi");
    }
}

