import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Class PopUpFrame allows the user to cancel, edit, or save an event once they have inputted all the user information.
 * The slider allows them to add more time if needed.
 */
public abstract class PopUpFrame extends JFrame {
    protected JTextArea detailMessage;
    protected JButton cancelButton;
    protected JButton editButton;
    protected JButton saveButton;
    protected JLabel text;
    protected JSlider slider;
    protected JPanel panel;
    protected Box buttonsBox = Box.createHorizontalBox();
    protected Box sliderBox = Box.createVerticalBox();
    protected JLabel timeText;

    /**
     * Allows user to add or subtract time from the estimated time that is generated.
     */
    public PopUpFrame(String str, boolean withEditButton) {
        super.setTitle("Ready Time");
        panel = new JPanel();
        allocateButtons(withEditButton);
        createSlider();
        createExtraButton();
        detailMessage = new JTextArea(str);
        detailMessage.setEditable(false);
        panel.setLayout(new BorderLayout());
        panel.setBounds(0, 0, 450, 250);
        panel.add(sliderBox, BorderLayout.CENTER);
        panel.add(buttonsBox, BorderLayout.SOUTH);
        panel.add(detailMessage, BorderLayout.NORTH);
        setDefaultLookAndFeelDecorated(true);
        setResizable(false);
        this.setLayout(new FlowLayout());
        this.add(panel);
        super.setBounds(0, 0, 450, 250);
        setVisible(true);
    }

    /**
     * Creates buttons.
     */
    private void allocateButtons(boolean hasEditButton) {
        cancelButton = new JButton("Cancel");
        saveButton = new JButton("Save Time");
        buttonsBox.add(saveButton);
        buttonsBox.add(cancelButton);
        if (hasEditButton)
            createEditButton();
    }

    private void createEditButton(){
        editButton = new JButton("Edit");
        buttonsBox.add(editButton);
    }

    protected abstract void createExtraButton();

    /**
     * Creates a slider that allows the user to add or subtract time form the estimated time that is generated.
     */
    protected abstract void createSlider();

    /**
     * Shows the popUp with new message
     *
     * @param alarmStr alarm in string format
     */
    public void showPopUp(String alarmStr) {
        detailMessage.setText(alarmStr);
        slider.setValue(0);
        setVisible(true);
    }

    /**
     * Gets side value
     * @return slider value
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
    public void addActionEditButton(ActionListener e) {
        if(editButton != null) {
            editButton.addActionListener(e);
        }
    }

    /**
     * Adds action to button.
     *
     * @param e action when button is clicked
     */
    public void addActionSaveButton(ActionListener e) {
        saveButton.addActionListener(e);
    }

    /**
     * Adds action to button.
     *
     * @param e action when button is clicked
     */
    public void addActionCancelButton(ActionListener e) {
        cancelButton.addActionListener(e);
    }

    /**
     * Adds action to button.
     *
     * @param e action when button is clicked
     */
    protected abstract void addActionAdjustButton(ActionListener e);

}

