import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.GregorianCalendar;

/**
 * Class CalendarEvent collects all information the user inputs to schedule an event.
 */
public abstract class CalendarEvent implements Serializable, Comparable<CalendarEvent>, Cloneable {
    protected int DEFAULT_PREPARE_MIN = 30;
    protected SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM dd yyyy - HH:mm");
    protected String addressFrom;
    protected String addressTo;
    protected String eventName = "No Event Name";
    protected String originName;
    protected String destName;
    protected GregorianCalendar arrivalDateTime;
    protected GregorianCalendar alarmTime;
    protected double importantScale;
    protected int recommendedReadyMin = 0;
    protected Transportation transport;
    public final static String BIKING_TYPE = "BICYCLING";
    public final static String DRIVING_TYPE = "DRIVING";
    public final static String WALKING_TYPE = "WALKING";
    public final static String TRANSIT_TYPE = "TRANSIT";

    /**
     * Constructor.
     *
     * @param addressFrom     Address of the starting destination
     * @param addressTo       Address of the ending destination
     * @param eventName       Name of the event attending
     * @param arrivalDateTime Time of the event
     * @param importantScale  How important the event is to the user
     */

    private CalendarEvent(String addressFrom, String addressTo, String eventName,
                          GregorianCalendar arrivalDateTime, double importantScale) {
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.eventName = eventName;
        this.arrivalDateTime = arrivalDateTime;
        this.importantScale = importantScale;
    }
    /**
     * Constructor for the class takes in string of transportation
     *
     * @param addressFrom     Starting address
     * @param addressTo       Ending address
     * @param eventName       Name of the event
     * @param arrivalDateTime Time the user wants to arrive by
     * @param transport       String of transportation
     * @param duration        Traveling time
     * @param importantScale  Priority level of the event
     */
    public CalendarEvent(String addressFrom, String addressTo, String eventName, String transport, int duration,
                         GregorianCalendar arrivalDateTime, double importantScale) {
        this(addressFrom, addressTo, eventName, arrivalDateTime, importantScale);
        this.transport = createTransport(transport, duration);

    }
    /**
     * Constructor for the class takes in string of transportation
     *
     * @param addressFrom     Starting address
     * @param addressTo       Ending address
     * @param eventName       Name of the event
     * @param arrivalDateTime Time the user wants to arrive by
     * @param transport       Transportation
     * @param importantScale  Priority level of the event
     */
    public CalendarEvent(String addressFrom, String addressTo, String eventName, Transportation transport,
                         GregorianCalendar arrivalDateTime,
                         double importantScale) {
        this(addressFrom, addressTo, eventName, arrivalDateTime, importantScale);
        this.transport = transport;
    }

    public abstract String getSummaryInfo();

    public GregorianCalendar getArrivalDateTime() {
        return (GregorianCalendar) arrivalDateTime.clone();
    }

    /**
     * Gets alarm time in string format
     *
     * @return the alarm time
     */
    public String getAlarmString() {
        return dateTimeFormat.format(alarmTime.getTime());
    }

    /**
     * Gets arrival time in string format
     *
     * @return the arrival time
     */

    public String getArrivalTimeString() {
        return dateTimeFormat.format(arrivalDateTime.getTime());
    }


    /**
     * Adjust preparing time depends on the user's wish
     *
     * @param adjustMin user input of how much time they want to add
     */
    public void editReadyTime(double adjustMin) {
        recommendedReadyMin += adjustMin;
        alarmTime.add(Calendar.MINUTE, -(int) adjustMin);
    }

    /**
     * Get a prompt about the ready time information.
     *
     * @return a string about the ready time
     */
    public abstract String getEventInfo();

    protected String durationStringFormat(int durationInMin) {
        int min = Math.abs(durationInMin % 60);
        int hour = durationInMin / 60;
        return String.format("%d %s %d %s", hour, hour > 1 ? "hours" : "hour",
                min, min > 1 ? "minutes" : "minute");
    }

    /**
     * Compares if the object is the same.
     *
     * @param other comparing object
     * @return false if it is not equal otherwise true
     */
    public abstract boolean equals(Object other);

    /**
     * Deep clone an object.
     *
     * @return copy object
     */

    @Override
    public abstract CalendarEvent clone() throws CloneNotSupportedException;


    /**
     * Compare 2 CalendarEvent
     *
     */
    @Override
    public int compareTo(CalendarEvent o) {
        return (this.arrivalDateTime.compareTo(o.arrivalDateTime));
    }

    /**
     * Gets information in string format
     *
     * @return a string
     */
    public abstract String toString();


    /**
     * Displays all modes of transportation.
     *
     * @param type     mode of transportation
     * @param duration time is takes to travel from starting destination to ending destination using specified mode of transportation.
     * @return returns the time
     */
    public Transportation createTransport(String type, int duration) {
        Transportation transport = null;
        switch (type) {
            case BIKING_TYPE:
                transport = new Biking(duration);
                break;
            case DRIVING_TYPE:
                transport = new Driving(duration);
                break;
            case WALKING_TYPE:
                transport = new Walking(duration);
                break;
            case TRANSIT_TYPE:
                transport = new Transit(duration);
        }
        return transport;
    }

    /**
     * Create popUp frame
     * @return  an new popUpFrame
     */
    public abstract PopUpFrame createPopUp();

}

