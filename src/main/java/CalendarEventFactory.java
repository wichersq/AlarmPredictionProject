import java.util.GregorianCalendar;

/**
 *
 */
public class CalendarEventFactory {
    /**
     * The class creates an event for the calendar.
     * @param addressFrom Address of destination
     * @param addressTo Address of starting
     * @param eventName name of event
     * @param originName name of origin place
     * @param destName name of destination
     * @param arrivalDateTime arrival date and time
     * @param transport Mode of transportation
     * @param importantScale The importance level of an event to the user
     * @param rating rating of the business
     * @return all the given user information
     */
    public static CalendarEvent createEventType(String addressFrom, String addressTo, String eventName,
                                                String originName, String destName, GregorianCalendar arrivalDateTime,
                                                Transportation transport, double importantScale, double rating) {
        if (rating <= 0.0) {
            return new CalendarEvent(addressFrom, addressTo, eventName,
                    originName, destName, arrivalDateTime, transport, importantScale);
        } else {
            return new EventWithPlaceInfo(addressFrom, addressTo, eventName,
                    originName, destName, arrivalDateTime, transport, importantScale, rating);
        }
    }
}
