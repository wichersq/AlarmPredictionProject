import java.io.*;
import java.util.*;

/**
 * A model keeps track all Shape value
 */
public class EventModel {
    private ArrayList<Listener> listeners;
    private HashMap<String, CalendarEvent> events;
    private File file;

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

    public ArrayList<String> getEvents() {
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

    private void writeEventsToFile() {
        Iterator<Map.Entry<String, CalendarEvent>> iter = events.entrySet().iterator();
        try {
            FileOutputStream fileOut = new FileOutputStream("Biking.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            while (iter.hasNext()) {
                out.writeObject(iter.next().getValue());
            }
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    public void readEventFromFile(String filePath) {
        CalendarEvent event;
        try {
            FileInputStream fileInput = new FileInputStream(filePath);
            ObjectInputStream inputStream = new ObjectInputStream(fileInput);
            while (true) {
                try {
                    event = (CalendarEvent) inputStream.readObject();
                    if (event != null)
                        events.put(event.getArrivalFormatTime(), event);

                } catch (EOFException eof) {
                    break;
                }
            }
            inputStream.close();
            fileInput.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }
}

