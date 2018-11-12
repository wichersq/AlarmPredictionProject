import java.sql.Time;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendaEvent {
    private String addressFrom;
    private String addressTo;
    private String name;
    private GregorianCalendar arrivalDateTime;
    private Transportation transport;
    private double importantScale;
    private double getReadySecond;
    private double travelTime;
    public CalendaEvent(){}
    public CalendaEvent(
            String addressFrom, String addressTo, String name,
            GregorianCalendar arrivalDateTime, Transportation transport, double importantScale) {
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.name = name;
        this.arrivalDateTime = arrivalDateTime;
        this.transport = transport;
        this.importantScale = importantScale;
    }

    public String toString() {
        System.out.println("addressFrom " + addressFrom);
        System.out.println("addressTo" + addressTo);
        System.out.println("transport" + transport.toString());
        System.out.println("getFormatTime" +  getFormatTime());
        System.out.println("getTotalTime" + getTotalTime());

        return String.format("Time , Date: \nOrigin: %s\nDestination: %s\nTravel by: %s\nRecommended Time:%s (%d before the event)",
                addressFrom, addressTo, transport.toString(), getFormatTime(), getTotalTime());
    }

    public String getFormatTime() {
        //TODO: calculate and format the alarm time by adding it to the event time
        return "hi";
    }

    protected double calculateImportantEvent() {
        return importantScale * 60 * 60;
    }

    public int getTotalTime() {
        getReadySecond = calculateImportantEvent() + transport.getTotalTimeTravel();
        return 458;
    }

    public void editReadyTime(double adjustMin) {
        getReadySecond += adjustMin;
    }

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
