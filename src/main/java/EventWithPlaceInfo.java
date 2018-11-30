import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class EventWithPlaceInfo creates an event with all of the user input information.
 */
public class EventWithPlaceInfo extends CalendarEvent {
    protected int DEFAULT_PREPARE_MIN = 30;
    private double averageRating;
    protected int preparingTime;
    protected int travelTime;

    /**
     * Constructor for the class.
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
    public EventWithPlaceInfo(String addressFrom, String addressTo, String eventName,
                              String originName, String destName, GregorianCalendar arrivalDateTime,
                              String transport, int duration, double importantScale, double averageRating) {
        super(addressFrom, addressTo, eventName,
                transport, arrivalDateTime, importantScale);
        this.originName = originName;
        this.destName = destName;
        this.averageRating = averageRating;
        this.transport = createTransport(transport, duration);
        this.travelTime = this.transport.getDurationInMin();
        setTotalTime();
        calPrepareTime();
    }

    public EventWithPlaceInfo(String addressFrom, String addressTo, String eventName,
                              String originName, String destName, GregorianCalendar arrivalDateTime,
                              Transportation transport, double importantScale, double averageRating) {
        super(addressFrom, addressTo, eventName, transport,
                arrivalDateTime, importantScale);
        this.originName = originName;
        this.destName = destName;
        this.averageRating = averageRating;
        this.transport = transport;
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

    @Override
    public EventWithPlaceInfo clone() throws CloneNotSupportedException {
        EventWithPlaceInfo event = new EventWithPlaceInfo(addressFrom, addressTo, eventName, originName,
                destName, (GregorianCalendar) arrivalDateTime.clone(),
                (Transportation) transport.clone(), importantScale, averageRating);
        event.recommendedReadyMin = recommendedReadyMin;
        event.alarmTime = alarmTime;
        return event;
    }


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
        if (!(other instanceof EventWithPlaceInfo)) {
            return false;
        }
        EventWithPlaceInfo comparingEvent = (EventWithPlaceInfo) other;
        return (addressFrom.equals(comparingEvent.addressFrom) &&
                addressTo.equals(comparingEvent.addressTo) &&
                arrivalDateTime.equals(comparingEvent.arrivalDateTime) &&
                transport.equals(comparingEvent.transport));
    }


}
