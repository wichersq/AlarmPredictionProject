import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.GregorianCalendar;

/**
 * Class CalendarEvent collects all information the user inputs to schedule an event.
 */
public class CalendarEvent implements Serializable, Comparable<CalendarEvent>, Cloneable {
    protected int DEFAULT_PREPARE_MIN = 30;
    protected String addressFrom;
    protected String addressTo;
    protected String eventName;
    protected String originName;
    protected String destName;
    protected GregorianCalendar arrivalDateTime;
    protected GregorianCalendar alarmTime;
    protected Transportation transport;
    protected double importantScale;
    protected int recommendedReadyMin;
    protected int preparingTime;
    protected int travelTime;
    protected SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM dd yyyy - HH:mm");

    /**
     * Constructor.
     *
     * @param addressFrom     Address of the starting destination
     * @param addressTo       Address of the ending destination
     * @param eventName       Name of the event attending
     * @param originName      Name of starting destination
     * @param destName        Name of ending destination
     * @param arrivalDateTime Time of the event
     * @param transport       Mode of transportation
     * @param importantScale  How important the event is to the user
     */
    public CalendarEvent(String addressFrom, String addressTo, String eventName,
                         String originName, String destName, GregorianCalendar arrivalDateTime,
                         Transportation transport, double importantScale) {
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.eventName = eventName;
        this.originName = originName;
        this.destName = destName;
        this.arrivalDateTime = arrivalDateTime;
        this.transport = transport;
        this.importantScale = importantScale;
        this.travelTime = transport.getDurationInMin();
        calPrepareTime();
        setTotalTime();

    }

    /**
     * @return returns all the user inputs
     */
    public String getSummaryInfo() {
        return String.format("\t%s\n*** %s ***\n%s  --->  %s\n" +
                        "Alarm set at: %s\n", eventName, getArrivalTimeString(),
                originName, destName, getAlarmString());

    }

    public GregorianCalendar getArrivalDateTime() {
        return (GregorianCalendar) arrivalDateTime.clone();
    }

    /**
     * Gets alarm time in string format
     * @return the alarm time
     */
    public String getAlarmString() {
        return dateTimeFormat.format(alarmTime.getTime());
    }

    /**
     * Gets arrival time in string format
     * @return the arrival time
     */

    public String getArrivalTimeString() {
        return dateTimeFormat.format(arrivalDateTime.getTime());
    }

    /**
     * Calculates the adjusted preparation time for the event based on the importance.
     */
    protected void calPrepareTime() {
        preparingTime = DEFAULT_PREPARE_MIN + (int) importantScale * 5;
    }

    /**
     * Calculates the recommended time to prepare for an event based on all the user input.
     */
    protected void setTotalTime() {
        recommendedReadyMin = preparingTime + transport.getTotalTravelMin();
        alarmTime = (GregorianCalendar) arrivalDateTime.clone();
        alarmTime.add(Calendar.MINUTE, -recommendedReadyMin);
    }

    /**
     * Adjust preparing time depends on the user's wish
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
    public String getEventInfo() {
        return String.format("Travel Duration:\t%s" +
                        "\n\nAlarm Time:\t%s\n\n%s %s the event",
                durationStringFormat(travelTime), getAlarmString(),
                durationStringFormat(recommendedReadyMin), (recommendedReadyMin < 0) ? "after" : "before");
    }

    private String durationStringFormat(int durationInMin){
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
    public boolean equals(Object other) {
        if (!(other instanceof CalendarEvent)) {
            return false;
        }
        CalendarEvent comparingEvent = (CalendarEvent) other;
        return (addressFrom.equals(comparingEvent.addressFrom) &&
                addressTo.equals(comparingEvent.addressTo) &&
                arrivalDateTime.equals(comparingEvent.arrivalDateTime) &&
                transport.equals(comparingEvent.transport));
    }

    /**
     * Deep clone an object.
     * @return copy object
     */

    @Override
    public CalendarEvent clone() throws CloneNotSupportedException {
        return new CalendarEvent(addressFrom, addressTo, eventName, originName,
                destName, (GregorianCalendar) arrivalDateTime.clone(),
                (Transportation)transport.clone(), importantScale);
    }

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
     * @return a string
     */
    public String toString() {
        return String.format("Date & Time:\t%s\n\nOrigin:\t%s\n\n" +
                        "Destination:\t%s\n\nTravel by:\t%s\n\n%s",
                getArrivalTimeString(), addressFrom, addressTo,
                transport.toString(), getEventInfo());
    }

}

