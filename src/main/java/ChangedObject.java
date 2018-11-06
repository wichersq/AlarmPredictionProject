public class ChangedObject {
    private String addressFrom = "";
    private String addressTo = "";
    private String name = "";
    private String dateOfEvent ;
    private String arrivalTime;
    private Transportation transport;
    private double importantScale;

    public ChangedObject(String addressFrom, String addressTo, String name,
                         String dateOfEvent, String arrivalTime, Transportation transport,double importantScale){
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.name = name;
        this.dateOfEvent = dateOfEvent;
        this.arrivalTime = arrivalTime;
        this.transport = transport;
        this.importantScale = importantScale;
    }
}

