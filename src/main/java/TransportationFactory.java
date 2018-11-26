/**
 *
 */
public class TransportationFactory {
    public final static String BIKE_TYPE = "BIKING";
    public final static String DRIVE_TYPE = "DRIVING";
    public final static String WALK_TYPE = "WALKING";
    public final static String TRANSIT_TYPE = "TRANSIT";

    /**
     *
     * @param type
     * @param duration
     * @return
     */
    public static Transportation createTransport(String type, int duration){
        if(type.equalsIgnoreCase(BIKE_TYPE)){return new Biking(duration);}
        else if(type.equalsIgnoreCase(DRIVE_TYPE)){return new Driving(duration);}
        else if(type.equalsIgnoreCase(WALK_TYPE)){return new Walking(duration);}
        else{return new Transit(duration);}
    }
}
