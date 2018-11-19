import java.util.GregorianCalendar;

public class EventWithPlaceInfo extends CalendarEvent {
    private double averageRating;


    public EventWithPlaceInfo(String addressFrom, String addressTo, String name,
                              GregorianCalendar arrivalDateTime, Transportation transport, double importantScale, double averageRating){
        super(addressFrom,addressTo,name,arrivalDateTime,transport,importantScale);
        this.averageRating = averageRating;

    }

    protected void calPrepareTime(){
        preparingTime = DEFAULT_PREPARE_MIN + (int)
                (importantScale * 0.5 + (importantScale - averageRating*6)*0.5);
    }
}
