
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.GregorianCalendar;

public class CalendarEvent  implements Serializable {
    protected int DEFAULT_PREPARE_MIN = 30;
    protected String addressFrom;
    protected String addressTo;
    protected String name;
    protected GregorianCalendar arrivalDateTime;
    protected GregorianCalendar alarmTime;
    protected Transportation transport;
    protected double importantScale;
    protected int recommendedReadyMin;
    protected int preparingTime;
    protected int travelTime;
    protected SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM dd yyyy - HH:mm");


    public CalendarEvent(String addressFrom, String addressTo, String name,
                         GregorianCalendar arrivalDateTime, Transportation transport, double importantScale) {
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.name = name;
        this.arrivalDateTime = arrivalDateTime;
        this.transport = transport;
        this.importantScale = importantScale;
        this.travelTime = (int) transport.getTotalMinTravel();
        calPrepareTime();
        setTotalTime();

    }

    public String toString() {
        return String.format("Date and Time:\t%s\nOrigin:\t%s\nDestination:\t%s\n" +
                        "Travel by:\t%s\n%s\n",
                        getArrivalFormatTime(), addressFrom, addressTo, transport.toString(), getAlarmString());
    }
    //TODO: calculate and format the alarm time by adding it to the event time
    public String getAlarmFormatTime() {
        return dateTimeFormat.format(alarmTime.getTime());
    }

    public String getArrivalFormatTime(){
        return dateTimeFormat.format(arrivalDateTime.getTime());
    }

    protected void calPrepareTime() {
        preparingTime = DEFAULT_PREPARE_MIN + (int)importantScale*5;
    }

    protected void setTotalTime() {
        recommendedReadyMin = preparingTime + travelTime;
        alarmTime = (GregorianCalendar)arrivalDateTime.clone();
        alarmTime.add(Calendar.MINUTE, - recommendedReadyMin);
    }

    public void editReadyTime(double adjustMin) {
        recommendedReadyMin += adjustMin;
        alarmTime.add(Calendar.MINUTE, -recommendedReadyMin);
    }

    public int getTravelTime(){
        return travelTime;
    }

    public String getAlarmString(){
        return String.format("Travel Estimation Duration: %d %s" +
                "\nRecommended Time: %s \n%d minutes before the event",
                travelTime, travelTime > 1? "minutes" : "minute",
                getAlarmFormatTime(), recommendedReadyMin);
    }
//    public void setTravelTime(double duration ){
//    }
//    public String getOrigin(){return addressFrom;}
//    public String getDestination(){return addressTo;}
//    public String getOrigin(){return addressFrom;}
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


//    /**
//     A helper method to write a rectangular shape.
//     @param out the stream onto which to write the shape
//     @param s the shape to write
//     */
//    private static void writeTransportation(ObjectOutputStream out,
//                                              RectangularShape s)
//            throws IOException
//    {
//        out.writeDouble(s.getX());
//        out.writeDouble(s.getY());
//        out.writeDouble(s.getWidth());
//        out.writeDouble(s.getHeight());
//    }
//
//    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
//        //always perform the default de-serialization first
//        inputStream.defaultReadObject();
//
//        //make defensive copy of the mutable Date field
//        dateOpened = new Date(dateOpened.getTime());
//
//        //ensure that object state has not been corrupted or tampered with maliciously
//        validateState();
//    }
//
//    /**
//     * This is the default implementation of writeObject.
//     * Customise if necessary.
//     */
//    private void writeObject(ObjectOutputStream outputStream) throws IOException {
//        //perform the default serialization for all non-transient, non-static fields
//        outputStream.defaultWriteObject();
//    }



}
