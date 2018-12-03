import java.util.GregorianCalendar;

/**
 * A class that holds all input from the user.
 */
public class RawUserInput {
    private String addressFrom;
    private String addressTo;
    private String name;
    private GregorianCalendar arrivalDateTime;
    private String transport;
    private double importantScale;

    /**
     * Constructor for the class.
     *
     * @param addressFrom     Address of starting destination
     * @param addressTo       Address of ending destination
     * @param name            Name of event
     * @param arrivalDateTime Date and time of the event
     * @param transportType   Mode of transportation
     * @param importantScale  The importance level of an event to the user
     */
    public RawUserInput(String addressFrom, String addressTo, String name,
                        GregorianCalendar arrivalDateTime, String transportType, double importantScale) {
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.name = name;
        this.arrivalDateTime = arrivalDateTime;
        this.transport = transportType;
        this.importantScale = importantScale;
    }

    /**
     * Accessor for addressFrom.
     *
     * @return origin address
     */
    public String getAddressFrom() {
        return addressFrom;
    }

    /**
     * Accessor for addressTo.
     *
     * @return destination address
     */
    public String getAddressTo() {
        return addressTo;
    }

    /**
     * Accessor for name.
     *
     * @return name of event
     */
    public String getName() {
        return name;
    }

    /**
     * Creates clone for date and time.
     *
     * @return a copy of the time and date of an event
     */
    public GregorianCalendar getArrivalDateTime() {
        return (GregorianCalendar) arrivalDateTime.clone();
    }

    /**
     * Accessor for transport.
     *
     * @return mode of transportation
     */
    public String getTransport() {
        return transport;
    }

    /**
     * Accessor for importantScale.
     *
     * @return the level of importance of the event
     */
    public double getImportantScale() {
        return importantScale;
    }


}

