
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.GregorianCalendar;

public class CalendarEvent {
    protected int DEFAULT_PREPARE_MIN = 30;
    protected String addressFrom;
    protected String addressTo;
    protected String name;
    protected GregorianCalendar arrivalDateTime;
    protected Transportation transport;
    protected double importantScale;
    protected int recommendedReadyMins;
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
        this.travelTime = (int) transport.getTotalTimeTravel();
        calPrepareTime();
        setTotalTime();

    }

    public String toString() {
        return String.format("Date and Time:\t%s\nOrigin:\t%s\nDestination:\t%s\n" +
                        "Travel by:\t%s\n%s",
                        getArrivalFormatTime(), addressFrom, addressTo, transport.toString(), getAlarmString());
    }
    //TODO: calculate and format the alarm time by adding it to the event time
    public String getAlarmFormatTime() {
        GregorianCalendar alarmTime = (GregorianCalendar)arrivalDateTime.clone();
        alarmTime.add(Calendar.MINUTE, -recommendedReadyMins);
        return dateTimeFormat.format(alarmTime.getTime());
    }

    public String getArrivalFormatTime(){
        return dateTimeFormat.format(arrivalDateTime.getTime());
    }

    protected void calPrepareTime() {
        preparingTime = DEFAULT_PREPARE_MIN + (int)importantScale*5;
    }

    protected void setTotalTime() {
        recommendedReadyMins = preparingTime + travelTime;
    }

    public void editReadyTime(double adjustMin) {
        recommendedReadyMins += adjustMin;
    }

    public int getTravelTime(){
        return travelTime;
    }

    public String getAlarmString(){
        return String.format("Travel Estimation Duration: %d %s" +
                "\nRecommended Time: %s \n%d minutes before the event",
                travelTime, travelTime > 1? "minutes" : "minute",
                getAlarmFormatTime(), recommendedReadyMins);
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

}
