/**
 * Class Walking estimates the time it would take for the user to reach ending destination by walking
 */
public class Walking extends Transportation {
    private final static int TRAVEL_SEC_PER_BREAK = 30*60;
    private final static int BREAK_TIME_SEC = 15*60;

    /**
     *
     * @param duration 
     */
    public Walking(int duration){
        super(duration);
    }

    /**
     * Calculates the break time when walking
     * @return returns break time when walking
     */
    protected int calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec/ TRAVEL_SEC_PER_BREAK);
        breakTimeOfTravel = (int) numOfBreak * BREAK_TIME_SEC;
        return breakTimeOfTravel;
    }

    /**
     *
     * @return returns the mode of transportation
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
