import java.util.GregorianCalendar;

public class EventWithPlaceInfo extends CalendaEvent {
    private double averageRating;


    public EventWithPlaceInfo(String addressFrom, String addressTo, String name,
                              GregorianCalendar arrivalDateTime, Transportation transport, double importantScale, int travelTime, double averageRating){
        super(addressFrom,addressTo,name,arrivalDateTime,transport,importantScale, 34);
        this.averageRating = averageRating;

    }

    protected double calculateImportantEvent(){
        return super.calculateImportantEvent() *0.5 + (15 - averageRating*3)*0.5;
    }
}
