/**
 * Factory Pattern for creating different types of transportation.
 */
public class TransportationFactory {
    public final static String BIKING_TYPE = "BICYCLING";
    public final static String DRIVING_TYPE = "DRIVING";
    public final static String WALKING_TYPE = "WALKING";
    public final static String TRANSIT_TYPE = "TRANSIT";

    /**
     * @param type mode of transportation
     * @param duration time is takes to travel from starting destination to ending destination using specified mode of transportation.
     * @return returns the time
     */
    public static Transportation createTransport(String type, int duration) {
        if (type.equalsIgnoreCase(BIKING_TYPE)) {
            return new Biking(duration);
        } else if (type.equalsIgnoreCase(DRIVING_TYPE)) {
            return new Driving(duration);
        } else if (type.equalsIgnoreCase(WALKING_TYPE)) {
            return new Walking(duration);
        } else {
            return new Transit(duration);
        }
    }
}
