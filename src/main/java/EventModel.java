import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A model keeps track all Shape value
 */
public class EventModel {
    private ArrayList<Listener> listeners;
    private HashMap<String, CalendarEvent> events;

    public EventModel() {
        events = new HashMap<String, CalendarEvent>();
        listeners = new ArrayList<Listener>();
    }

    /**
     * adds a listener
     *
     * @param l adding listener
     */
    public void addListener(Listener l) {
        listeners.add(l);
    }

    /**
     * Adds shape to the list
     *
     * @param s the adding shape
     */
    public void addEvent(CalendarEvent s) {
        events.put(s.getArrivalFormatTime(), s);
        notifyListener(s);
    }

    public boolean isTimeOccupied(String dateTime) {
        return events.containsKey(dateTime);

    }

    /**
     * Gets list of shapes
     *
     * @return a list of shapes
     */
    public ArrayList<CalendarEvent> getEventList() {
        return (ArrayList<CalendarEvent>) events.clone();
    }

    public ArrayList<String> getEvents(){
        CalendarEvent event;
        ArrayList<String> eventList = new ArrayList<>();
        Iterator<Map.Entry<String, CalendarEvent>> iterator = events.entrySet().iterator();
        while (iterator.hasNext()) {
            event = iterator.next().getValue();
            eventList.add(event.toString());
        }
        return eventList;
    }

    /**
     * notifies all listener
     *
     * @param object changed object
     */
    private void notifyListener(Object object) {
        System.out.println("hello");
        for (Listener l : listeners) {
            l.update(object);
        }
    }
}

