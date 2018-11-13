public class TransportationFactory {
    private final static String BIKE_TYPE = "BIKE";
    private final static String DRIVE_TYPE = "DRIVE";
    private final static String WALK_TYPE = "WALK";
    private final static String TRANSIT_TYPE = "TRANSIT";

    public static Transportation getTransport(String type, int duration){
        if(type.equalsIgnoreCase(BIKE_TYPE)){return new Bike(duration);}
        else if(type.equalsIgnoreCase(DRIVE_TYPE)){return new Drive(duration);}
        else if(type.equalsIgnoreCase(WALK_TYPE)){return new Walk(duration);}
        else{return new Transit(duration);}
    }
}
