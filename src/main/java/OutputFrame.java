import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Class OutputFrame displays the event information and estimated time the user would need
   to prepare for the event along with all the event that were scheduled prior.
 */
public class OutputFrame extends JFrame implements Listener {
    private TextAreaDetail textArea;
    private JPanel listPanel;
    private JPanel detailPanel;
    private JScrollPane scrollPane;
    private DefaultListModel listModel;
    private JList list;
    private JScrollPane scrollPaneDetail;
    private EventModel model;
    //    private JButton showButton;
    private JButton deleteButton;
    private Box buttonBox;
    private MyList myList;

    /**
     * @param model 
     * @param size desired size of output frame
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
        add(deleteButton, BorderLayout.SOUTH);

    }

    /**
     * Creates the detailPanel for the event to display.
     */
    private void createEventListPanel() {
        listPanel = new JPanel();
        listModel = new DefaultListModel();
        list = new JList(listModel);
        myList = new MyList(3, 30);
        list.setCellRenderer(myList);
        scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        listPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void createEventDetailPanel() {
        detailPanel = new JPanel();

        textArea = new TextAreaDetail(myList);
        textArea.setEditable(false);
        scrollPaneDetail = new JScrollPane(textArea);
        scrollPaneDetail.setPreferredSize(new Dimension(400, 400));

        detailPanel.add(scrollPaneDetail, BorderLayout.CENTER);
    }

    private void createButtonBox() {
        buttonBox = Box.createHorizontalBox();
//        showButton = new JButton("Show Detail");
        deleteButton = new JButton("Delete");
//        buttonBox.add(showButton);
//        buttonBox.add(deleteButton);

    }

    public void updateTextList() {
        ArrayList<CalendarEvent> calendarList = model.getEventsList();
        listModel.setSize(0);
        textArea.setText("");
        for (CalendarEvent event : calendarList) {
            listModel.addElement(event);
        }
    }

    /**
     * Adds action to button.
     *
     * @param e action when button is clicked
     */
    public void addActionDeleteButton(ActionListener e) {
        deleteButton.addActionListener(e);
    }

    public CalendarEvent getSelectionFromList() {
        return myList.getCurrentSelection();
    }


    /**
     * Updates the event list with the new event.
     *
     * @param ob
     */
    @Override
    public void update(Object ob) {
        updateTextList();
    }
}
