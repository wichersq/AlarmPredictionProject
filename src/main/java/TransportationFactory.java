public class TransportationFactory {
    private final static String BIKE_TYPE = "BIKING";
    private final static String DRIVE_TYPE = "DRIVING";
    private final static String WALK_TYPE = "WALKING";
    private final static String TRANSIT_TYPE = "TRANSIT";

    public static Transportation createTransport(String type, int duration){
        if(type.equalsIgnoreCase(BIKE_TYPE)){return new Biking(duration);}
        else if(type.equalsIgnoreCase(DRIVE_TYPE)){return new Driving(duration);}
        else if(type.equalsIgnoreCase(WALK_TYPE)){return new Walking(duration);}
        else{return new Transit(duration);}
    }
}
