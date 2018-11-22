import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class PopUpFrame extends JFrame {

    private JLabel text;
    private JSlider slider;
    private JButton cancelButton = new JButton("Cancel");
    private JButton editButton = new JButton("Edit");
    private JButton saveButton = new JButton("Save Time");
    private JButton adjustTime = new JButton("Adjust Ready Time");
    private JPanel panel = new JPanel();
    private JTextArea tf;
    private Box buttonsBox = Box.createHorizontalBox();
    private Box sliderBox = Box.createVerticalBox();
    private Controller controller;
    private JLabel timeText;


    public PopUpFrame(Controller controller) {
    		setTitle("Ready Time");
        this.controller = controller;
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

    private void allocateButtons() {
        cancelButton = new JButton("Cancel");

        editButton = new JButton("Edit");

        saveButton = new JButton("Save Time");
        adjustTime = new JButton("Adjust Ready Time");

        adjustTime.addActionListener(ActionEvent -> {
            int adjustingTime = slider.getValue();
            if (adjustingTime != 0) {
                controller.adjustReadyTime(adjustingTime);
            }
            slider.setValue(0);
        });
        buttonsBox.add(saveButton);
        buttonsBox.add(editButton);
        buttonsBox.add(cancelButton);
        buttonsBox.add(adjustTime);
    }

    private void createSlider() {
        tf = new JTextArea("Recommended Alarm Time: ");
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


    public void showPopUp(String alarmStr) {
        tf.setText(alarmStr);
        slider.setValue(0);
        setVisible(true);
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

//    public void popUpMessage() {
//        //        messageBox.add(addMoreTime);
//        JDialog.setDefaultLookAndFeelDecorated(true);
//        JOptionPane.showOptionDialog(null, "The event is saved", "Confirmation",
//                JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,null,null);
//    }

}

