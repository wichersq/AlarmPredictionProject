
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.GregorianCalendar;

public class CalendaEvent {
    private String addressFrom;
    private String addressTo;
    private String name;
    private GregorianCalendar arrivalDateTime;
    private Transportation transport;
    private double importantScale;
    private int getReadyMins;
    private double travelTime;
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM dd yyyy HH:mm");


    public CalendaEvent(
            String addressFrom, String addressTo, String name,
            GregorianCalendar arrivalDateTime, Transportation transport, double importantScale, int travelTime) {
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.name = name;
        this.arrivalDateTime = arrivalDateTime;
        this.transport = transport;
        this.importantScale = importantScale;
        this.travelTime = travelTime;
        setTotalTime();
    }

    public String toString() {
        return String.format("Date and Time:\t%s\nOrigin:\t%s\nDestination:\t%s\nTravel by:\t%s\n%s",
                getArrivalFormatTime(), addressFrom, addressTo, transport.toString(), getAlarmString());
    }
    //TODO: calculate and format the alarm time by adding it to the event time
    public String getAlarmFormatTime() {
        GregorianCalendar alarmTime = (GregorianCalendar)arrivalDateTime.clone();
        alarmTime.add(Calendar.MINUTE, (int) -getReadyMins);
        return dateTimeFormat.format(alarmTime.getTime());
    }

    public String getArrivalFormatTime(){
        return dateTimeFormat.format(arrivalDateTime.getTime());
    }

    protected double calculateImportantEvent() {
        return importantScale;
    }

    protected void setTotalTime() {
        getReadyMins = (int) (importantScale + transport.getTotalTimeTravel());
    }

    public void editReadyTime(double adjustMin) {
        getReadyMins += adjustMin;
    }

    public int getReadyTime(){
        return getReadyMins;
    }

    public String getAlarmString(){
        return String.format("Recommended Time: %s (%d minutes before the event)", getAlarmFormatTime(), getReadyMins);
    }
//    public void setTravelTime(double duration ){
//
//    }
    //    public String getOrigin(){return addressFrom;}
//    public String getDestination(){return addressTo;}
//    public String getOrigin(){return addressFrom;}
    public boolean equals(Object other) {
        if (!(other instanceof CalendaEvent)) {
            return false;
        }
        CalendaEvent comparingEvent = (CalendaEvent) other;
        return (addressFrom.equals(comparingEvent.addressFrom) &&
                addressTo.equals(comparingEvent.addressTo) &&
                arrivalDateTime.equals(comparingEvent.arrivalDateTime) &&
                transport.equals(comparingEvent.transport));
    }

}
