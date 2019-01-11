import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * a class that help EventModel adapt to JList
 */
public class DataListModel extends AbstractListModel {
    private EventModel model;
    private ArrayList<CalendarEvent> eventListForModel;

    /**
     * Constructor
     *
     * @param model adapting model
     */
    public DataListModel(EventModel model) {
        this.model = model;
        updateList();
    }

    /**
     * Updates the list
     */
    public void updateList() {
        eventListForModel = model.getEventsList();
        fireIntervalAdded(eventListForModel, eventListForModel.size(), eventListForModel.size());
    }

    /**
     * Removes an event
     *
     * @param ob specific events
     */
    public void remove(CalendarEvent ob) {
        model.removeEvents(ob);
        model.getEventsList();
        fireIntervalRemoved(eventListForModel, 0, eventListForModel.size());

    }

    public void saveEventToFile(CalendarEvent ob, String fileName) {
        FileWriter file = null;
        PrintWriter writer = null;
        try {
            file = new FileWriter("testing.csv", true);
            writer = new PrintWriter(file);
            writer.append("");
            writer.append("\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("catch it");
        }
    }

    /**
     * Gets size of the list
     *
     * @return size of the list
     */
    @Override
    public int getSize() {
        return eventListForModel.size();
    }

    /**
     * Gets element at specific index
     *
     * @param index index of element
     * @return element
     */
    @Override
    public Object getElementAt(int index) {
        return eventListForModel.get(index);
    }


}
