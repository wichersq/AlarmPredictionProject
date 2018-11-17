import java.util.GregorianCalendar;

public class CalendaEventFactory {

    public static CalendaEvent createEvenType(String origin, String destination, String name, GregorianCalendar dateTime,
                                              Transportation transport, double scale, int duration, int rating){
        if(rating < 0){return new CalendaEvent(origin,destination,name,dateTime,transport,scale,duration);}
        else{return new EventWithPlaceInfo(origin,destination,name,dateTime,transport,scale,duration,rating);}
    }
}
