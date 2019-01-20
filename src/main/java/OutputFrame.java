import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    private int size;
    private FileWriter trainDataWriter;
    private File file;
    /**
     * Constructor
     *
     * @param model model that using to get values
     * @param size  desired size of output frame
     */
    public OutputFrame(EventModel model, int size) {
        this.size = size;
        super.setTitle("Scheduled Events");
        super.setLayout(new FlowLayout());
        super.setBounds(0, 0, size + (4*size/5), size);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addCloseWindowOption(model);
        setVisible(false);
        setResizable(false);
        model.addListener(this);


        createPopUp();

        listModel = new DataListModel(model);
        addEventListPanel();

        createEventDetailPanel();
        add(detailPanel, BorderLayout.EAST);

        createButtonBox();
        add(buttonBox, BorderLayout.SOUTH);

        maybeCreateFile("trainingData.csv");
    }

    /**
     * Creates the detailPanel for the event to display.
     */
    private void addEventListPanel() {
        listPanel = new JPanel();
        list = new JList(listModel);
        calendarListElement = new CalendarListElement(3, 30);
        calendarListElement.addListener(this);
        list.setCellRenderer(calendarListElement);
        scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(4*size/5, 4*size/5));
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
        scrollPaneDetail.setPreferredSize(new Dimension(4*size/5, 4*size/5));

        detailPanel = new JPanel();
        detailPanel.add(scrollPaneDetail, BorderLayout.CENTER);
    }

    private void createPopUp(){
        popUp = new RealAlarmTimePopUp();
        popUp.addActionSaveButton(ActionEvent ->{
            CalendarEvent editingEvent = popUp.hidePopUp();
            int requestedChangeMin = popUp.getRequestedTotalMinAdj();
            if(requestedChangeMin != 0) {
                listModel.editEvents(editingEvent, requestedChangeMin);
                textArea.setText("");
            }
        });

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
                popUp.showPopUp(getSelectionFromList());
            }catch(NullPointerException e) {
            }
        });
    }

    public void maybeGrayOutEditButton(Object ob){
        if (ob.getClass() != EventWithInfo.class){
            editButton.setEnabled(false);
        }else{
            editButton.setEnabled(true);
        }
    }
    /**
     * Deletes events in the event model.
     *
     * @param ob deleting object
     */
    public void deleteEvents(CalendarEvent ob) {
        listModel.remove(ob);
        if(ob.getClass().equals(EventWithInfo.class))
            writeToFile((EventWithInfo)ob);
    }

    //TODO: edit method to output event info
    /**
     * This method to collect training data only
     * @param event
     */
    public void writeToFile(EventWithInfo event){
            try {
                trainDataWriter.append("");
                trainDataWriter.append(",");
                trainDataWriter.flush();
            }catch (IOException ex){
                ex.printStackTrace();
            }
    }

    /**
     * Checks if the file exists, if not creates a file.
     *
     * @param fileName the file name
     */
    private void maybeCreateFile(String fileName) {
        file = new File(fileName);
        boolean doFileExist = file.exists();
        try {
            trainDataWriter = new FileWriter(file, true);
            if (!doFileExist) {
                String header = "Address From,Origin ID,Address To,Place ID,Rating,Open Period,Event Important Level," +
                        "Driving,Transit,Biking,Walking,Duration,Distance";
                trainDataWriter.append(header);
                trainDataWriter.flush();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }

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
     * This window makes sure that the user wants to exit the event they created.
     */
    private void addCloseWindowOption(EventModel model) {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                model.saveEventsToFile();
                setVisible(false);
            }
        });
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
