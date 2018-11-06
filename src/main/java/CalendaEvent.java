import java.sql.Time;
import java.util.Date;

public abstract class CalendaEvent {
    private String addressFrom;
    private String addressTo;
    private String name;
    private Date dateOfEvent;
    private Time arrivalTime;
    private Transportation transport;
    private double importantScale;
}
