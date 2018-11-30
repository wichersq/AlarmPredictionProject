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
    public CalendarEvent(String addressFrom, String addressTo, String eventName, String transport
            , GregorianCalendar arrivalDateTime, double importantScale) {
        this.transport = createTransport(transport, 0);
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.eventName = eventName;
        this.arrivalDateTime = arrivalDateTime;
        this.importantScale = importantScale;
    }

    public CalendarEvent(String addressFrom, String addressTo, String eventName, Transportation transport,
                         GregorianCalendar arrivalDateTime,
                         double importantScale) {
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.eventName = eventName;
        this.transport = transport;
        this.arrivalDateTime = arrivalDateTime;


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

//    /**
//     * Determines estimated travel time.
//     * @return estimated time it would take to travel to the ending location from the starting location
//     */
//    public int getTravelTime() {
//        return travelTime;
//    }

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

    @Override
    /**
     * Compare 2 CalendarEvent
     *
     */
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
    public abstract PopUpFrame createPopUp();

}

