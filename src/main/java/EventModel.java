import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
     * A model keeps track all Shape value
     */
    public class EventModel{
        private ArrayList<Listener> listeners;
        private HashMap<String,CalendaEvent> events;

        public EventModel(){
            events = new HashMap<String,CalendaEvent>();
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
         * @param s the adding shape
         */
        public void addEvent  (CalendaEvent s){
            events.put(s.getDateandTime(),s);
            notifyListener(s);
        }
        public boolean isTimeOccupied(String dateTime){
            return events.containsKey(dateTime);
        }
        /**
         * Gets list of shapes
         * @return  a list of shapes
         */
        public ArrayList<CalendaEvent> getEvents(){
            return (ArrayList<CalendaEvent>)events.clone();
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

