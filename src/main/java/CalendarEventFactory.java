import java.util.GregorianCalendar;

public class CalendarEventFactory {
    /**
     * The class creates an event for the calendar.
     * @param addressFrom Address of ending destination
     * @param addressTo Address of starting destination
     * @param eventName Name of event attending
     * @param originName Name of starting destination
     * @param destName Name of ending destination
     * @param arrivalDateTime Arrival date and time
     * @param transport Mode of transportation
     * @param importantScale The importance level of an event to the user
     * @param rating Determines how busy the destination may be to estimate time to find parking
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
