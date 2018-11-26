import java.util.GregorianCalendar;

/**
 * Class Changed object allows the user to edit the information after it has been inputted
 */
public class ChangedObject {
    private String addressFrom = "";
    private String addressTo = "";
    private String name = "";
    private GregorianCalendar arrivalDateTime;
    private String transport;
    private double importantScale;

    /**
     *
     * @param addressFrom
     * @param addressTo
     * @param name
     * @param arrivalDateTime
     * @param transportType
     * @param importantScale
     */
    public ChangedObject(String addressFrom, String addressTo, String name,
                         GregorianCalendar arrivalDateTime, String transportType, double importantScale){
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.name = name;
        this.arrivalDateTime = arrivalDateTime;
        this.transport = transportType;
        this.importantScale = importantScale;
    }

    /**
     *
     * @return
     */
    public String getAddressFrom(){return addressFrom;}
    public String getAddressTo(){return addressTo;}

    /**
     *
     * @return
     */
    public String getName(){return name;}

    /**
     *
     * @return
     */
    public GregorianCalendar getArrivalDateTime(){
        return (GregorianCalendar) arrivalDateTime.clone();
    }

    /**
     *
     * @return
     */
    public String getTransport(){return transport;}

    /**
     *
     * @return
     */
    public double getImportantScale(){return importantScale;}



}

