import java.io.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A model keeps track all events' values.
 */
public class EventModel{
    private ArrayList<Listener> modelListeners;
    private HashMap<GregorianCalendar, CalendarEvent> events;
    private File file;
    private LinkedBlockingQueue<RawUserInput> eventsToProcess;


    /**
     * Constructor for the class.
     *
     * @param filePath file path that save old information
     */
    public EventModel(String filePath) {
        events = new HashMap<GregorianCalendar, CalendarEvent>();
        modelListeners = new ArrayList<Listener>();
        eventsToProcess = new LinkedBlockingQueue<>();
        maybeCreateFile(filePath);
    }

    /**
     * Adds a listener.
     *
     * @param l adding listener
     */
    public void addListener(Listener l) {
        modelListeners.add(l);
    }

    /**
     * Adds event to the list.
     *
     * @param s the adding event
     */
    public void addEvent(CalendarEvent s) {
        events.put(s.getArrivalDateTime(), s);
        notifyListener(s);
    }

    /**
     * Adds user input to list for processing
     *
     * @param ob object with the user input
     */

    public void addEventToProcess(RawUserInput ob) {
        try {
            eventsToProcess.put(ob);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Get raw info from the list
     *
     * @return returns the event
     */

    public RawUserInput getEventToProcess() {
        RawUserInput event = null;
        try {
            event = eventsToProcess.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return event;
    }

    /**
     * Checks if the time is already in the the list.
     *
     * @param dateTime the checking time
     * @return true or false
     */
    public boolean isTimeOccupied(GregorianCalendar dateTime) {
        return events.containsKey(dateTime);
    }

    /**
     * Gets list of events in sort order.
     *
     * @return a copy of list of events in string format
     */

    public ArrayList<CalendarEvent> getEventsList() {
            CalendarEvent event;
            ArrayList<CalendarEvent> eventListForModel = new ArrayList<>();
            Iterator<Map.Entry<GregorianCalendar, CalendarEvent>> iterator = events.entrySet().iterator();
            while (iterator.hasNext()) {
                event = iterator.next().getValue();
                try {
                    eventListForModel.add(event.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
            Collections.sort(eventListForModel);
            return eventListForModel;
        }

    /**
     * Notifies all listeners.
     *
     * @param object changed object
     */
    private void notifyListener(Object object) {
        for (Listener l : modelListeners) {
            l.update(object);
        }
    }

    /**
     * Removes events from the tracking event list
     *
     * @param ob removing object
     */
    public void removeEvents(CalendarEvent ob) {
        GregorianCalendar calendar = ob.getArrivalDateTime();
        if (events.containsKey(calendar)) {
            events.remove(calendar);
            notifyListener(ob);
        }
    }

    /**
     * Saves all event to file.
     */
    public void saveEventsToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream(file.getName());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(events);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Restore information from file.
     */

    public void restoreEventsFromFile() {
        try {
            FileInputStream fileInput = new FileInputStream(file.getName());
            ObjectInputStream inputStream = new ObjectInputStream(fileInput);
            while (true) {
                try {
                    events = (HashMap<GregorianCalendar, CalendarEvent>) inputStream.readObject();
                    getEventsList();
                } catch (EOFException eof) {
                    break;
                }
            }
            inputStream.close();
            fileInput.close();
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Checks if the file exists, if not creates a file.
     *
     * @param fileName the file name
     */
    private void maybeCreateFile(String fileName) {
        this.file = new File(fileName);
        if (file.exists()) {
            restoreEventsFromFile();
        }
    }

}

