import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PopUpPanel extends JPanel {

	private JLabel text;
	private JSlider slider;
	private JButton cancelButton;
	private JButton editButton;
	private JButton saveButton;

	public PopUpPanel() {
		setLayout(new BorderLayout());
		
		Box sliderBox = Box.createVerticalBox();
		text = new JLabel();
		text.setText("You can slide to modify the given time recommendation:");

		slider = new JSlider(JSlider.HORIZONTAL, -30, 30, 0);
		slider.setMinorTickSpacing(2);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		sliderBox.add(text);
		sliderBox.add(slider);
		add(sliderBox, BorderLayout.NORTH);

		Box buttonsBox = Box.createHorizontalBox();
		saveButton = new JButton("Save Time");
		cancelButton = new JButton("Cancel");
		editButton = new JButton("Edit");
		buttonsBox.add(saveButton);
		buttonsBox.add(cancelButton);
		buttonsBox.add(editButton);
		add(buttonsBox, BorderLayout.SOUTH);
	}

	public void addActionEditButton(ActionListener e) {
		editButton.addActionListener(e);
	}

	public void addActionSaveButton(ActionListener e) {
		saveButton.addActionListener(e);
	}

	public void addActionCancelButton(ActionListener e) {
		System.out.println("Adding cancel listner");
		cancelButton.addActionListener(e);
	}
	
	public int getTimeSliderValue() {
		return slider.getValue();
	}
}
