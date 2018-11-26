/**
 *
 */
public class Walking extends Transportation {
    private final static int TRAVEL_SEC_PER_BREAK = 30*60;
    private final static int BREAK_TIME_MIN = 15;

    /**
     *
     * @param duration
     */
    public Walking(int duration){
        super(duration);
    }

    /**
     *
     * @return
     */
    protected int calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec/ TRAVEL_SEC_PER_BREAK);
        breakTimeOfTravel = (int) numOfBreak * BREAK_TIME_MIN;
        return breakTimeOfTravel;
    }

    /**
     *
     * @return
     */
    public String toString(){
        return TransportationFactory.WALKING_TYPE;
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
