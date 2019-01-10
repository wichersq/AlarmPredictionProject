import javax.swing.*;
import java.awt.*;

/**
 * Class OutputFrame displays the event information and estimated time the user would need
 * to prepare for the event along with all the event that were scheduled prior.
 */
public class OutputFrame extends JFrame implements Listener {
    private TextAreaDetail textArea;
    private JPanel listPanel;
    private JPanel detailPanel;
    private JScrollPane scrollPane;
    private DataListModel listModel;
    private JList list;
    private JScrollPane scrollPaneDetail;
    private JButton deleteButton;
    private JButton editButton;
    private Box buttonBox;
    private CalendarListElement calendarListElement;
    private RealAlarmTimePopUp popUp;

    /**
     * Constructor
     *
     * @param model model that using to get values
     * @param size  desired size of output frame
     */
    public OutputFrame(EventModel model, int size) {
        super.setTitle("Scheduled Events");
        super.setLayout(new FlowLayout());
        super.setBounds(0, 0, 900, 500);
        setDefaultLookAndFeelDecorated(true);
        setVisible(false);
        setResizable(false);
        model.addListener(this);
        popUp = new RealAlarmTimePopUp();

        listModel = new DataListModel(model);
        addEventListPanel();

        createEventDetailPanel();
        add(detailPanel, BorderLayout.EAST);

        createButtonBox();
        add(buttonBox, BorderLayout.SOUTH);
    }

    /**
     * Creates the detailPanel for the event to display.
     */
    private void addEventListPanel() {
        listPanel = new JPanel();

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
        buttonBox = Box.createHorizontalBox();
        deleteButton = new JButton("Delete");
        editButton = new JButton("Edit Time");
        buttonBox.add(deleteButton);
        buttonBox.add(editButton);
        deleteButton.addActionListener(ActionListener -> {
            try {
                deleteEvents(getSelectionFromList());
            }catch(NullPointerException e){
            }
            if (listModel.getSize() <= 0) {
                clearTextDetail();
            }
        });

        editButton.addActionListener(ActionListener -> {
            try {
                editEvents(getSelectionFromList());
            }catch(NullPointerException e) {
            }
        });
    }


    private void editEvents(CalendarEvent ob) {
        popUp.popUpMessage(ob);
    }

    /**
     * Deletes events in the event model.
     *
     * @param ob deleting object
     */
    public void deleteEvents(CalendarEvent ob) {
        listModel.remove(ob);
    }

    /**
     * Set visible frame
     *
     * @param visible true if visible
     */
    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            listModel.updateList();
            if (listModel.getSize() <= 0) {
                JDialog.setDefaultLookAndFeelDecorated(true);
                JOptionPane.showConfirmDialog(null,
                        "No Events", "Warning Message",
                        JOptionPane.DEFAULT_OPTION);
            } else {
                super.setVisible(true);
            }
        } else {
            super.setVisible(false);
        }
    }

    /**
     * Clears text in text area detail
     */
    private void clearTextDetail() {
        textArea.setText("");
    }

    /**
     * Gets the object being chosen
     */
    private CalendarEvent getSelectionFromList() throws NullPointerException {
        CalendarEvent selectedEvent = calendarListElement.getCurrentSelection();
        if(selectedEvent == null){
            throw new NullPointerException("No event pick");
        }
        return selectedEvent;
    }


    /**
     * Updates the event list with the new event.
     *
     * @param ob updating info object
     */
    @Override
    public void update(Object ob) {
        listModel.updateList();
    }
}
