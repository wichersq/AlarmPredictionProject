
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.HashMap;

public class CalendaEvent {
    private String addressFrom;
    private String addressTo;
    private String name;
    private GregorianCalendar arrivalDateTime;
    private Transportation transport;
    private double importantScale;
    private double getReadySecond;
    private double travelTime;

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
//        System.out.println("addressFrom " + addressFrom);
//        System.out.println("addressTo" + addressTo);
//        System.out.println("transport" + transport.toString());
//        System.out.println("getFormatTime" +  getFormatTime());
//        System.out.println("getTotalTime" + getReadySecond);

        return String.format("Time , Date: \nOrigin: %s\nDestination: %s\nTravel by: %s\nRecommended Time:%s (%d minutes before the event)",
                addressFrom, addressTo, transport.toString(), getFormatTime(), (int) getReadySecond/60);
    }
    //TODO: calculate and format the alarm time by adding it to the event time
    public String getFormatTime() {
        GregorianCalendar alarmTime = (GregorianCalendar)arrivalDateTime.clone();
        alarmTime.add(Calendar.SECOND, (int) - getReadySecond );
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM dd yyyy HH:mm");
        dateTimeFormat.format(alarmTime.getTime());
        return dateTimeFormat.format(alarmTime.getTime());
    }

    protected double calculateImportantEvent() {
        return importantScale;
    }

    private void setTotalTime() {
        getReadySecond = calculateImportantEvent() + transport.getTotalTimeTravel();
    }

    public void editReadyTime(double adjustMin) {
        getReadySecond += adjustMin;
    }

    public String getDateandTime(){
        return arrivalDateTime.toString();
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
