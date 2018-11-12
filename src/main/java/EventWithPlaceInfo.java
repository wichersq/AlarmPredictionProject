import java.util.GregorianCalendar;

public class EventWithPlaceInfo extends CalendaEvent {
    private double averageRating;

    public EventWithPlaceInfo(){}
    public EventWithPlaceInfo(String addressFrom, String addressTo, String name,
                              GregorianCalendar arrivalDateTime, Transportation transport, double importantScale, double averageRating){
        super(addressFrom,addressTo,name,arrivalDateTime,transport,importantScale);
        this.averageRating = averageRating;

    }

    protected double calculateImportantEvent(){
        return super.calculateImportantEvent() *0.5 + (15 - averageRating*3)*0.5;
    }
}
