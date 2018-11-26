import java.util.GregorianCalendar;

/**
 * Class Changed object allows the user to edit the information after it has been inputted.
 */
public class ChangedObject {
    private String addressFrom = "";
    private String addressTo = "";
    private String name = "";
    private GregorianCalendar arrivalDateTime;
    private String transport;
    private double importantScale;

    /**
     * Updates the changes the user makes on the event.
     * @param addressFrom Address of starting destination
     * @param addressTo Address of ending destination
     * @param name Name of event
     * @param arrivalDateTime Date and time of the event
     * @param transportType Mode of transportation 
     * @param importantScale The importance level of an event to the user
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
     * Accessor for addressFrom
     * @return Returns address
     */
    public String getAddressFrom(){return addressFrom;}
    /**
     * Accessor for addressTo
     * @return Returns address
     */
    public String getAddressTo(){return addressTo;}

    /**
     * Accessor for name
     * @return returns name of event
     */
    public String getName(){return name;}

    /**
     * Creates clone for date and time.
     * @return returns a clone of the time and date of an event
     */
    public GregorianCalendar getArrivalDateTime(){
        return (GregorianCalendar) arrivalDateTime.clone();
    }

    /**
     * Accessor for transport
     * @return returns mode of transportation
     */
    public String getTransport(){return transport;}

    /**
     * Accessor for importantScale
     * @return returns the level of importance of the event
     */
    public double getImportantScale(){return importantScale;}



}

