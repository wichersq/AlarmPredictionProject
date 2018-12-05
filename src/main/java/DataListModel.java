import javax.swing.*;
import java.util.ArrayList;

/**
 * a class that help EventModel adapt to JList
 */
public class DataListModel extends AbstractListModel {
    private EventModel model;
    private ArrayList<CalendarEvent> eventListForModel;

    /**
     * Constructor
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
     * @param ob  specific events
     */
    public void remove(CalendarEvent ob) {
        model.removeEvents(ob);
        model.getEventsList();
        fireIntervalRemoved(eventListForModel, 0, eventListForModel.size());

    }

    /**
     * Gets size of the list
     * @return size of the list
     */
    @Override
    public int getSize() {
        return eventListForModel.size();
    }

    /**
     * Gets element at specific index
     * @param index index of element
     * @return element
     */
    @Override
    public Object getElementAt(int index) {
        return eventListForModel.get(index);
    }


}
