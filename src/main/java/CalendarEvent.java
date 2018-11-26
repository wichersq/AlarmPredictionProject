
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.GregorianCalendar;

/**
 * Class CalendarEvent allows the user to input all of the information to schedule an event in the calendar
 */
public class CalendarEvent implements Serializable {
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
     * @param addressFrom Address of the starting destination 
     * @param addressTo Address of the ending destination 
     * @param eventName Name of the event attending
     * @param originName Name of starting destination
     * @param destName Name of ending destination
     * @param arrivalDateTime Time of the event
     * @param transport Mode of transportation
     * @param importantScale How important the event is to the user
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
    public String toString() {
//        return String.format("Date and Time:\t%s\nOrigin:\t%s\nDestination:\t%s\n" +
//                        "Travel by:\t%s\n%s\n\n",
//                        getArrivalTimeString(), addressFrom, addressTo, transport.toString(), getEventInfo());

        return String.format("\t%s\n*** %s ***\n%s  --->  %s\n" +
                        "Alarm set at: %s\n", eventName, getArrivalTimeString(),
                originName, destName, getAlarmString());

    }

    public GregorianCalendar getArrivalDateTime() {
        return (GregorianCalendar) arrivalDateTime.clone();
    }

    /**
     * @return returns the information in the given format
     */
    public String getAlarmString() {
        return dateTimeFormat.format(alarmTime.getTime());
    }

    /**
     * @return returns the arrival time
     */

    public String getArrivalTimeString() {
        return dateTimeFormat.format(arrivalDateTime.getTime());
    }

    /**
     * Retruns the adjusted preparation time for the event based on the importance
     */
    protected void calPrepareTime() {
        preparingTime = DEFAULT_PREPARE_MIN + (int) importantScale * 5;
    }

    /**
     * Calculates the etimated time to prepare for an event based on all the user input
     */
    protected void setTotalTime() {
        recommendedReadyMin = preparingTime + transport.getTotalTravelMin();
        alarmTime = (GregorianCalendar) arrivalDateTime.clone();
        alarmTime.add(Calendar.MINUTE, -recommendedReadyMin);
    }

    /**
     * @param adjustMin user input of how much time they want to add
     */
    public void editReadyTime(double adjustMin) {
        recommendedReadyMin += adjustMin;
        alarmTime.add(Calendar.MINUTE, -(int) adjustMin);
        System.out.println(getAlarmString());
    }

//    /**
//     * determines estimated travel time
//     * @return estimated time it would take to travel to the ending location from the starting location
//     */
//    public int getTravelTime() {
//        return travelTime;
//    }

    /**
     * @return returns the recommended prepartion time for an event 
     */
    public String getEventInfo() {
        int min = recommendedReadyMin % 60;
        int hour = recommendedReadyMin/60;
        String readyTime = String.format("%d %s %d %s", hour, hour > 1 ? "hours" : "hour",
                min, min > 1 ? "minutes" : "minute");
        return String.format("Travel Duration: %s" +
                        "\nAlarm Time: %s \n%s %s the event",
                transport.getDurationString(), getAlarmString(),
                readyTime, (recommendedReadyMin < 0) ? "after" : "before");
    }

    /**
     * @param other 
     * @return false
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

}
