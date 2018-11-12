import java.awt.event.ActionEvent;
import java.util.ArrayList;

    /**
     * A model keeps track all Shape value
     */
    public class Controller implements Listener{
        private ArrayList<Listener> listeners;
        private ArrayList<CalendaEvent> events;
        private UserPanel userInput;
        private EventModel model;

        public Controller(UserPanel userInput, EventModel model){
            events = new ArrayList<CalendaEvent>();
            listeners = new ArrayList<Listener>();
            this.userInput = userInput;
            this.model = model;
            userInput.addListener(this);
//            responseToConfirmation();

        }
//TODO: WRITE FACTORY CLASS to find out EventWithoutPlaceInfo or EventWithPlaceInfo
      private void handleEvent(ChangedObject ob){

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
        private void responseToConfirmation(){
            userInput.addActionSaveButton(ActionEvent ->{
                model.addEvent(new EventWithPlaceInfo());
                System.out.println("Save Button Click");


            });

            userInput.addActionCancelButton(ActionEvent ->{
                userInput.setBackToDefault();
                System.out.println("Cancel Button Click");
            });

//            userInput.addActionEditButton(ActionEvent ->{
//
//            });

        }

        public void update(Object ob) {
            if(ob.getClass().equals(ChangedObject.class)) {
                handleEvent((ChangedObject) ob);
            }
            responseToConfirmation();
            userInput.popUpMessage();


        }
    }


