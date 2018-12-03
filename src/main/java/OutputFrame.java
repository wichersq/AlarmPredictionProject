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
    private JButton deleteButton;
    private CalendarListElement calendarListElement;


    /**
     * Constructor
     * @param model model that using to get values
     * @param size desired size of output frame
     */
    public OutputFrame(EventModel model, int size) {
        super.setTitle("Scheduled Events");
        super.setLayout(new FlowLayout());
        super.setBounds(0, 0, 900, 500);
        setDefaultLookAndFeelDecorated(true);
        setVisible(false);
        setResizable(false);

        addEventListPanel();


        createEventDetailPanel();
        add(detailPanel, BorderLayout.EAST);

        createButtonBox();
        add(deleteButton, BorderLayout.SOUTH);

        this.model = model;
        model.addListener(this);





    }

    /**
     * Creates the detailPanel for the event to display.
     */
    private void addEventListPanel() {
        listPanel = new JPanel();
        listModel = new DefaultListModel();
        list = new JList(listModel);
        calendarListElement = new CalendarListElement(3, 30);
        list.setCellRenderer(calendarListElement);
        scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        listPanel.add(scrollPane, BorderLayout.CENTER);
        add(listPanel, BorderLayout.CENTER);
    }

    /**
     * Creates a panel that show event detail
     */
    private void createEventDetailPanel() {

        textArea = new TextAreaDetail(calendarListElement);
        textArea.setEditable(false);

        scrollPaneDetail = new JScrollPane(textArea);
        scrollPaneDetail.setPreferredSize(new Dimension(400, 400));

        detailPanel = new JPanel();
        detailPanel.add(scrollPaneDetail, BorderLayout.CENTER);
    }

    /**
     * Creates button
     */
    private void createButtonBox() {
        deleteButton = new JButton("Delete");


    }

    /**
     *  Updates list
     */
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
        return calendarListElement.getCurrentSelection();
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
