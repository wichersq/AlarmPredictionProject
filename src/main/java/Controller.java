
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * A model keeps track all Shape value
 */
public class Controller implements Listener {
    private ArrayList<Listener> listeners;
    private ArrayList<CalendarEvent> events;
    private UserPanel userInput;
    private EventModel model;
    private CalendarEvent currentEvent;
    private PopUpPanel popUp;

    public Controller(UserPanel userInput, EventModel model) {
        popUp = new PopUpPanel(this);
        events = new ArrayList<CalendarEvent>();
        listeners = new ArrayList<Listener>();
        this.userInput = userInput;
        this.model = model;
        userInput.addListener(this);
//            responseToConfirmation();

    }

    //TODO: WRITE FACTORY CLASS to find out EventWithoutPlaceInfo or EventWithPlaceInfo
    private void pullDataRequest(ChangedObject ob) {
        String addressFrom = ob.getAddressFrom();
        String addressTo = ob.getAddressTo();
        String name = ob.getName();
        GregorianCalendar arrivalDateTime = ob.getArrivalDateTime();
        double importantScale = ob.getImportantScale();
        Transportation transport = TransportationFactory.getTransport(ob.getTransport(), 35);
//        double rating = -1 if no rating
        currentEvent = CalendarEventFactory.createEvenType(addressFrom, addressTo, name,
                arrivalDateTime, transport, importantScale , -2);
        popUpMessage(currentEvent.getAlarmString());
    }


    /**
     * notifies all listener
     *
     */
    private void popUpMessage(String alarmStr) {
        popUp.showPopUp(alarmStr);
    }

    public void adjustReadyTime(int changingTime){
        currentEvent.editReadyTime(changingTime);
        String alarmStr = currentEvent.getAlarmString();
        popUp.showPopUp(alarmStr);
    }

    public boolean checkIfTimeOccupied(String dateTime){
        return model.isTimeOccupied(dateTime);
    }

    private void responseToConfirmation() {
        popUp.addActionSaveButton(ActionEvent -> {
            CalendarEvent event = currentEvent;
            model.addEvent(event);
            userInput.setBackToDefault();
            popUp.setVisible(false);
        });

        popUp.addActionCancelButton(ActionEvent -> {
            popUp.setVisible(false);
            userInput.setBackToDefault();
            System.out.println("Cancel Button Click");
        });

        popUp.addActionEditButton(ActionEvent -> {
            popUp.setVisible(false);
        });


//            userInput.addActionEditButton(ActionEvent ->{
//
//            });

    }

    public void update(Object ob) {
        if (ob.getClass().equals(ChangedObject.class)) {
            pullDataRequest((ChangedObject) ob);
        }
        responseToConfirmation();
//        userInput.popUpMessage();
    }
}


