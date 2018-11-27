import javax.swing.*;


import java.awt.*;
import java.util.ArrayList;

/**
 * Class OutputFrame displays the event information and estimated time the user would need
 * to prepare for the event along with all the event that were scheduled prior.
 */
public class OutputFrame extends JFrame implements Listener {
    private JTextArea textArea;
    private EventModel model;
    private JPanel listPanel;
    private JPanel detailPanel;
    private JScrollPane scrollPane;
    private DefaultListModel listModel;
    private JList list;
    private JScrollPane scrollPaneDetail;
    private JButton showButton;
    private JButton deleteButton;
    private Box buttonBox;

    /**
     * @param model
     * @param size  desired size of output frame
     */
    public OutputFrame(EventModel model, int size) {
        super.setTitle("Scheduled Events");
        super.setLayout(new FlowLayout());
        super.setBounds(0, 0, 900, 500);
        setDefaultLookAndFeelDecorated(true);
//      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createEventListPanel();
        createEventDetailPanel();
        createButtonBox();
        this.model = model;
        model.addListener(this);
        setVisible(false);
        setResizable(false);
        add(listPanel, BorderLayout.CENTER);
        add(detailPanel, BorderLayout.EAST);
        add(buttonBox, BorderLayout.NORTH);

    }

    /**
     * Creates the detailPanel for the event to display
     */
    private void createEventListPanel() {
        listPanel = new JPanel();

        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setCellRenderer(new MyList(3, 30));

        scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        listPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void createEventDetailPanel(){
        detailPanel = new JPanel();

        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPaneDetail = new JScrollPane(textArea);
        scrollPaneDetail.setPreferredSize(new Dimension(400, 400));

        detailPanel.add(scrollPaneDetail, BorderLayout.CENTER);
    }
    private void createButtonBox(){
        buttonBox = Box.createHorizontalBox();
        showButton = new JButton("Show Detail");
        deleteButton = new JButton("Delete");
        buttonBox.add(showButton);
        buttonBox.add(deleteButton);

    }

    //TODO: adds action to button
    public void updateTextList() {
        ArrayList<CalendarEvent> calendarList = model.getEventsList();
        for (CalendarEvent event : calendarList) {
            listModel.addElement(event);
        }
    }
//    /**
//     * Adds the new event to the list of events that were scheduled before
//     */
//    public void updateTextList() {
//        ArrayList<String> calendarList = model.getEvents();
//        StringBuilder strBuilder = new StringBuilder();
//        int index = 0;
//        for (String event : calendarList) {
//            index++;
//            strBuilder.append(String.format("Event %d:", index));
//            strBuilder.append(event);
//        }
//        textArea.setText(strBuilder.toString());
//    }

    /**
     * Updates the event list with the new event
     *
     * @param ob
     */
    @Override
    public void update(Object ob) {
        updateTextList();
    }
}
