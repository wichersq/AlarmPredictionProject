import java.util.GregorianCalendar;

/**
 * 
 */
public class EventWithPlaceInfo extends CalendarEvent {
    private double averageRating;

    /**
     *
     * @param addressFrom Starting address
     * @param addressTo Ending address
     * @param eventName Name of the event 
     * @param originName Name of the starting destination 
     * @param destName Name of the ending destination
     * @param arrivalDateTime Time the user wants to arrive by 
     * @param transport Mode of transportation
     * @param importantScale Priority level of the event
     * @param averageRating Rating of the ending destination 
     */
    public EventWithPlaceInfo(String addressFrom, String addressTo, String eventName,
                              String originName, String destName, GregorianCalendar arrivalDateTime,
                              Transportation transport, double importantScale, double averageRating){
        super(addressFrom, addressTo, eventName, originName,  destName,
                arrivalDateTime, transport, importantScale);
        this.averageRating = averageRating;

    }

    /**
     * Calculates the estimated time the user needs to prepare for an event using the importance scale. 
       The more important the event is the more time will be adding to prepare.
     */
    protected void calPrepareTime(){
        preparingTime = DEFAULT_PREPARE_MIN + (int)
                (importantScale * 0.5 + (importantScale - averageRating*6)*0.5);
    }
}
