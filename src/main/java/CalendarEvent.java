
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.GregorianCalendar;

/**
 *
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
     *
     * @param addressFrom
     * @param addressTo
     * @param eventName
     * @param originName
     * @param destName
     * @param arrivalDateTime
     * @param transport
     * @param importantScale
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
        this.travelTime = transport.getTotalMinTravel();
        calPrepareTime();
        setTotalTime();

    }

    /**
     *
     * @return
     */
    public String toString() {
//        return String.format("Date and Time:\t%s\nOrigin:\t%s\nDestination:\t%s\n" +
//                        "Travel by:\t%s\n%s\n\n",
//                        getArrivalFormatTime(), addressFrom, addressTo, transport.toString(), getEventInfo());

        return String.format("\t%s\n*** %s ***\n%s  --->  %s\n" +
                        "Alarm set at: %s\n",eventName, getArrivalFormatTime(),
                originName, destName, getAlarmFormatTime());

    }

    /**
     *
     * @return
     */
    public String getAlarmFormatTime() {
        return dateTimeFormat.format(alarmTime.getTime());
    }

    /**
     *
     * @return
     */

    public String getArrivalFormatTime() {
        return dateTimeFormat.format(arrivalDateTime.getTime());
    }

    /**
     *
     */
    protected void calPrepareTime() {
        preparingTime = DEFAULT_PREPARE_MIN + (int) importantScale * 5;
    }

    /**
     *
     */
    protected void setTotalTime() {
        recommendedReadyMin = preparingTime + travelTime;
        alarmTime = (GregorianCalendar) arrivalDateTime.clone();
        alarmTime.add(Calendar.MINUTE, -recommendedReadyMin);
    }

    /**
     *
     * @param adjustMin
     */
    public void editReadyTime(double adjustMin) {
        recommendedReadyMin += adjustMin;
        alarmTime.add(Calendar.MINUTE, -(int) adjustMin);
        System.out.println(getAlarmFormatTime());
    }

//    /**
//     *
//     * @return
//     */
//    public int getTravelTime() {
//        return travelTime;
//    }

    /**
     *
     * @return
     */
    public String getEventInfo() {
        return String.format("Travel Duration: %d %s" +
                        "\nAlarm Time: %s \n%d minutes %s the event",
                travelTime, travelTime > 1 ? "minutes" : "minute",
                getAlarmFormatTime(), Math.abs(recommendedReadyMin), (recommendedReadyMin < 0) ? "after" : "before");
    }

    /**
     *
     * @param other
     * @return
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
