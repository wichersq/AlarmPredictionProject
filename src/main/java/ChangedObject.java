import java.util.GregorianCalendar;

public class ChangedObject {
    private String addressFrom = "";
    private String addressTo = "";
    private String name = "";
    private GregorianCalendar arrivalDateTime;
    private String transport;
    private double importantScale;

    public ChangedObject(String addressFrom, String addressTo, String name,
                         GregorianCalendar arrivalDateTime, String transportType, double importantScale){
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.name = name;
        this.arrivalDateTime = arrivalDateTime;
        this.transport = transportType;
        this.importantScale = importantScale;
    }

    public String getAddressFrom(){return addressFrom;}
    public String getAddressTo(){return addressTo;}
    public String getName(){return name;}

    public GregorianCalendar getArrivalDateTime(){
        return (GregorianCalendar) arrivalDateTime.clone();
    }

    public String getTransport(){return transport;}

    public double getImportantScale(){return importantScale;}



}

