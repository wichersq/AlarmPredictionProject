import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Class contain event which doesn't have valid info from API
 */
public class EventWithInfo extends CalendarEvent {
    protected int DEFAULT_PREPARE_MIN = 30;
    private double averageRating;
    protected int preparingTime;
    protected int travelTime;

    /**
     * Constructor for the class takes in string of transportation
     *
     * @param addressFrom     Starting address
     * @param addressTo       Ending address
     * @param eventName       Name of the event
     * @param originName      Name of the starting destination
     * @param destName        Name of the ending destination
     * @param arrivalDateTime Time the user wants to arrive by
     * @param transport       String of transportation
     * @param duration        Duration of the travel
     * @param importantScale  Priority level of the event
     * @param averageRating   Rating of the ending destination
     */

    public EventWithInfo(String addressFrom, String addressTo, String eventName,
                         String originName, String destName, GregorianCalendar arrivalDateTime,
                         String transport, int duration, double importantScale, double averageRating) {
        super(addressFrom, addressTo, eventName,
                transport, duration, arrivalDateTime, importantScale);
        setInfo(originName, destName, importantScale, averageRating);
    }

    /**
     * Constructor for the class takes in Transportation type.
     *
     * @param addressFrom     Starting address
     * @param addressTo       Ending address
     * @param eventName       Name of the event
     * @param originName      Name of the starting destination
     * @param destName        Name of the ending destination
     * @param arrivalDateTime Time the user wants to arrive by
     * @param transport       Mode of transportation
     * @param importantScale  Priority level of the event
     * @param averageRating   Rating of the ending destination
     */
    public EventWithInfo(String addressFrom, String addressTo, String eventName,
                         String originName, String destName, GregorianCalendar arrivalDateTime,
                         Transportation transport, double importantScale, double averageRating) {
        super(addressFrom, addressTo, eventName, transport,
                arrivalDateTime, importantScale);
        setInfo(originName, destName, importantScale, averageRating);
    }

    private void setInfo(String originName, String destName, double importantScale, double averageRating) {
        this.originName = originName;
        this.destName = destName;
        this.averageRating = averageRating;
        this.importantScale = importantScale;
        this.travelTime = this.transport.getDurationInMin();
        setTotalTime();
        calPrepareTime();
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
     * Calculates the estimated time the user needs to prepare for an event using the importance scale.
     * The more important the event is the more time will be adding to prepare.
     */
    protected void calPrepareTime() {
        preparingTime = DEFAULT_PREPARE_MIN + (int)
                ((importantScale * 5) * 0.5 + ((5 - averageRating) * 6) * 0.5);
    }

    /**
     * Clone an object
     *
     * @return a copy of the object
     * @throws CloneNotSupportedException
     */
    @Override
    public EventWithInfo clone() throws CloneNotSupportedException {
        EventWithInfo event = new EventWithInfo(addressFrom, addressTo, eventName, originName,
                destName, (GregorianCalendar) arrivalDateTime.clone(),
                (Transportation) transport.clone(), importantScale, averageRating);
        event.recommendedReadyMin = recommendedReadyMin;
        event.alarmTime = alarmTime;
        return event;
    }

    /**
     * Creates popUp frame
     *
     * @return A right pop up frame
     */
    public PopUpFrame createPopUp() {
        return new OnlinePopUpFrame(this.getEventInfo());
    }

    /**
     * Gets information in string format
     *
     * @return a string
     */
    public String toString() {
        return String.format("Date & Time:\t%s\n\nOrigin:\t%s\n\n" +
                        "Destination:\t%s\n\nTravel by:\t%s\n\n%s",
                getArrivalTimeString(), addressFrom, addressTo,
                transport.toString(), getEventInfo());
    }

    /**
     * @return returns all the user inputs
     */
    public String getSummaryInfo() {
        return String.format("\t%s\n*** %s ***\n%s  --->  %s\n" +
                        "Alarm set at: %s\n", eventName, getArrivalTimeString(),
                originName, destName, getAlarmString());

    }

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

    /**
     * Compares if the object is the same.
     *
     * @param other comparing object
     * @return false if it is not equal otherwise true
     */
    public boolean equals(Object other) {
        if (!(other instanceof EventWithInfo)) {
            return false;
        }
        EventWithInfo comparingEvent = (EventWithInfo) other;
        return (addressFrom.equals(comparingEvent.addressFrom) &&
                addressTo.equals(comparingEvent.addressTo) &&
                arrivalDateTime.equals(comparingEvent.arrivalDateTime) &&
                transport.equals(comparingEvent.transport));
    }


}
