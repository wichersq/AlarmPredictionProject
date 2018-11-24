import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 */
public class PopUpFrame extends JFrame {

    private JLabel text;
    private JSlider slider;
    private JButton cancelButton = new JButton("Cancel");
    private JButton editButton = new JButton("Edit");
    private JButton saveButton = new JButton("Save Time");
    private JButton adjustButton = new JButton("Adjust Ready Time");
    private JPanel panel = new JPanel();
    private JTextArea tf;
    private Box buttonsBox = Box.createHorizontalBox();
    private Box sliderBox = Box.createVerticalBox();
    private JLabel timeText;

    /**
     *
     */
    public PopUpFrame() {
        super.setTitle("Ready Time");
        allocateButtons();
        createSlider();
        panel.setLayout(new BorderLayout());
        panel.setBounds(0, 0, 300, 100);
        panel.add(sliderBox, BorderLayout.CENTER);
        panel.add(buttonsBox, BorderLayout.SOUTH);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.add(panel, BorderLayout.CENTER);
        super.setBounds(0, 0, 500, 200);
    }

    /**
     *
     */
    private void allocateButtons() {
        cancelButton = new JButton("Cancel");

        editButton = new JButton("Edit");

        saveButton = new JButton("Save Time");
        adjustButton = new JButton("Adjust Ready Time");
        buttonsBox.add(saveButton);
        buttonsBox.add(editButton);
        buttonsBox.add(cancelButton);
        buttonsBox.add(adjustButton);
    }

    /**
     *
     */
    private void createSlider() {
        tf = new JTextArea("Recommended Alarm Time: ");
        tf.setEditable(false);
        timeText = new JLabel();
        timeText.setText("Time adjust: 0");
        text = new JLabel("Adjusting your ready time:");
        slider = new JSlider(JSlider.HORIZONTAL, -30, 30, 0);
        slider.addChangeListener(e -> timeText.setText("Time adjust: " + slider.getValue()));
        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        sliderBox.add(tf);
        sliderBox.add(text);
        sliderBox.add(timeText);
        sliderBox.add(slider);

    }

    /**
     * @param alarmStr
     */
    public void showPopUp(String alarmStr) {
        tf.setText(alarmStr);
        slider.setValue(0);
        setVisible(true);
    }

    /**
     * @return
     */
    public int getSliderValue() {
        int value = slider.getValue();
        slider.setValue(0);
        return value;
    }

    /**
     * @param e
     */
    public void addActionEditButton(ActionListener e) {
        editButton.addActionListener(e);
    }

    /**
     * @param e
     */
    public void addActionSaveButton(ActionListener e) {
        saveButton.addActionListener(e);
    }

    /**
     * @param e
     */
    public void addActionCancelButton(ActionListener e) {
        cancelButton.addActionListener(e);
    }

    /**
     * @param e
     */
    public void addActionAdjustButton(ActionListener e) {
        adjustButton.addActionListener(e);
    }
}

