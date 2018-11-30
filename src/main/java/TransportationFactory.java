/**
 * Factory Pattern for creating different types of transportation.
 */
public class TransportationFactory {
    public final static String BIKING_TYPE = "BICYCLING";
    public final static String DRIVING_TYPE = "DRIVING";
    public final static String WALKING_TYPE = "WALKING";
    public final static String TRANSIT_TYPE = "TRANSIT";
    /**
//     * Displays all modes of transportation.
//     * @param type mode of transportation
//     * @param duration time is takes to travel from starting destination to ending destination using specified mode of transportation.
//     * @return returns the time
//     */
    public Transportation createTransport(String type, int duration) {
        Transportation transport = null;
       switch (type){
           case BIKING_TYPE:
               transport =  new Biking(duration);
               break;
           case DRIVING_TYPE:
               transport = new Driving(duration);
               break;
           case WALKING_TYPE:
               transport = new Walking(duration);
               break;
           case TRANSIT_TYPE:
               transport = new Transit(duration);
       }
       return transport;
    }
}
