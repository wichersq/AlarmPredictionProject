import java.util.ArrayList;

    /**
     * A model keeps track all Shape value
     */
    public class EventModel{
        private ArrayList<Listener> listeners;
        private ArrayList<CalendaEvent> events;

        public EventModel(){
            events = new ArrayList<CalendaEvent>();
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
            events.add(s);
            notifyListener(s);
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
        public void notifyListener(Object object) {
            for (Listener l : listeners) {
                l.update(object);
            }
        }
    }

