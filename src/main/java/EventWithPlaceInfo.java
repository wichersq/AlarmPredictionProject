import java.util.GregorianCalendar;

public class EventWithPlaceInfo extends CalendarEvent {
    private double averageRating;


    public EventWithPlaceInfo(String addressFrom, String addressTo, String eventName,
                              String originName, String destName, GregorianCalendar arrivalDateTime,
                              Transportation transport, double importantScale, double averageRating){
        super(addressFrom, addressTo, eventName, originName,  destName,
                arrivalDateTime, transport, importantScale);
        this.averageRating = averageRating;

    }

    protected void calPrepareTime(){
        preparingTime = DEFAULT_PREPARE_MIN + (int)
                (importantScale * 0.5 + (importantScale - averageRating*6)*0.5);
    }
}
