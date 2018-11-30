//import java.util.GregorianCalendar;
//
///**
// * Class CalendarEventFactory creates an event for the calendar.
// */
//public class CalendarEventFactory{
//    private TransportationFactory transportationFactory;
//
//    /**
//     * Constructor
//     */
//    public CalendarEventFactory(){
//       transportationFactory = new TransportationFactory();
//    }
//
//    /**
//     * Stores information about each scheduled event.
//     * @param addressFrom Address of destination
//     * @param addressTo Address of starting
//     * @param eventName name of event
//     * @param originName name of origin place
//     * @param destName name of destination
//     * @param arrivalDateTime arrival date and time
//     * @param transport Mode of transportation
//     * @param duration duration of travel
//     * @param importantScale The importance level of an event to the user
//     * @param rating rating of the business
//     * @return all the given user information
//     */
//    public CalendarEvent createEventType(String addressFrom, String addressTo, String eventName,
//                                                String originName, String destName, GregorianCalendar arrivalDateTime,
//                                                String transport,int duration, double importantScale, double rating) {
//        if (rating <= 0.0) {
//            return new CalendarEvent(addressFrom, addressTo, eventName,
//                    originName, destName, arrivalDateTime, createTransport(transport,duration), importantScale);
//        } else {
//            return new EventWithPlaceInfo(addressFrom, addressTo, eventName,
//                    originName, destName, arrivalDateTime, createTransport(transport,duration), importantScale, rating);
//        }
//    }
//    /**
//     * Creates transport base on the type
//     */
//    private Transportation createTransport(String transport, int durationSec){
//        return transportationFactory.createTransport(transport, durationSec);
//    }
//}
