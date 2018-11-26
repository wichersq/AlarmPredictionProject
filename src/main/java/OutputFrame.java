import javax.swing.*;


import java.awt.*;
import java.util.ArrayList;

/**
 * Class OutputFrame displays the event information and estimated time the user would need 
   to prepare for the event along with all the event that were scheduled prior. 
 */
public class OutputFrame extends JFrame implements Listener {
    private JTextArea textArea;
    private EventModel model;
    private JPanel panel;
    private JScrollPane scrollPane;

    /**
     *
     * @param model 
     * @param size desired size of output frame
     */
    public OutputFrame(EventModel model, int size) {
        super.setTitle("Scheduled Events");
        super.setLayout(new FlowLayout());
        super.setBounds(0, 0, size, size);
        setDefaultLookAndFeelDecorated(true);
//      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createEventListPanel();
        this.model = model;
        model.addListener(this);
        setVisible(false);
        setResizable(false);
        this.add(panel, BorderLayout.CENTER);
    }

    /**
     * Creates the panel to shich the evnt will be displayed
     */
    public void createEventListPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane =new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        panel.add(scrollPane, BorderLayout.CENTER);


    }

    /**
     * Adds the new event to the list of events that were scheduled before
     */
    public void updateTextList() {
        ArrayList<String> calendarList = model.getEvents();
        StringBuilder strBuilder = new StringBuilder();
        int index = 0;
        for (String event : calendarList) {
            index++;
            strBuilder.append(String.format("Event %d:", index));
            strBuilder.append(event);
        }
        textArea.setText(strBuilder.toString());
    }

    /**
     * Updates the event list with the new event
     * @param ob
     */
    @Override
    public void update(Object ob) {
        updateTextList();
    }
}
