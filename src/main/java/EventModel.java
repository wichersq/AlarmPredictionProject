import java.io.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A model keeps track all events' values
 */
public class EventModel {
    private ArrayList<Listener> listeners;
    private TreeMap<GregorianCalendar, CalendarEvent> events;
    private File file;
    private LinkedBlockingQueue<ChangedObject> eventsToProcess;

    /**
     *
     * @param filePath
     */
    public EventModel(String filePath) {
        events = new TreeMap<GregorianCalendar, CalendarEvent>();
        listeners = new ArrayList<Listener>();
        eventsToProcess = new LinkedBlockingQueue<>();
        maybeCreateFile(filePath);
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
     * Adds event to the list
     *
     * @param s the adding event
     */
    public void addEvent(CalendarEvent s) {
        events.put(s.getArrivalDateTime(),s);
        notifyListener(s);
    }

    public void addEventToProcess(ChangedObject ob){
        try {
            eventsToProcess.put(ob);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public ChangedObject getEventToProcess(){
        ChangedObject event = null;
        try {
            System.out.println("start run");
            event = eventsToProcess.take();
            System.out.println("Start to pull data request");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return event;
    }
    public boolean isTimeOccupied(String dateTime) {
        return events.containsKey(dateTime);
    }
    /**
     * Gets list of events in string format
     *
     * @return a copy of list of events in string format
     */
    public ArrayList<String> getEvents() {
        CalendarEvent event;
        ArrayList<String> eventList = new ArrayList<>();
        Iterator<Map.Entry<GregorianCalendar, CalendarEvent>> iterator = events.entrySet().iterator();
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
        for (Listener l : listeners) {
            l.update(object);
        }
    }

    /**
     *
     */
    public void saveEventsToFile() {
        Iterator<Map.Entry<GregorianCalendar, CalendarEvent>> iter = events.entrySet().iterator();
        try {
            FileOutputStream fileOut = new FileOutputStream(file.getName());
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

    /**
     *
     */

    public void restoreEventsFromFile() {
        CalendarEvent event;
        try {
            FileInputStream fileInput = new FileInputStream(file.getName());
            ObjectInputStream inputStream = new ObjectInputStream(fileInput);
            while (true) {
                try {
                    event = (CalendarEvent) inputStream.readObject();
                    if (event != null)
                        events.put(event.getArrivalDateTime(), event);

                } catch (EOFException eof) {
                    break;
                }
            }
            inputStream.close();
            fileInput.close();
        }catch (EOFException e) {
            return;
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
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

