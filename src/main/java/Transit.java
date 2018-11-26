/**
 *
 */
public class Transit extends Transportation {
    private final static int TRAVEL_SEC_PER_BREAK = 4*60*60;
       public Transit(int duration){
        super(duration);
    }

    /**
     *
     * @return
     */
    protected int calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec/ TRAVEL_SEC_PER_BREAK);
        breakTimeOfTravel = (int) numOfBreak * BREAK_TIME_SEC;
        return breakTimeOfTravel;
    }

    /**
     *
     * @return
     */
    public String toString(){
        return TransportationFactory.TRANSIT_TYPE;
    }

    /**
     *
     * @param other
     * @return
     */
    public boolean equals(Object other){
        return(other.getClass().equals(this));
    }
}
