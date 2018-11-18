import java.util.GregorianCalendar;

public class CalendarEventFactory {

    public static CalendarEvent createEvenType(String origin, String destination, String name, GregorianCalendar dateTime,
                                               Transportation transport, double scale, int rating){
        if(rating < 0){return new CalendarEvent(origin,destination,name,dateTime,transport,scale);}
        else{return new EventWithPlaceInfo(origin,destination,name,dateTime,transport,scale,rating);}
    }
}
