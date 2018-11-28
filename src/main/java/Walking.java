/**
 * Class Walking estimates the time it would take for the user to reach ending destination by walking.
 */
public class Walking extends Transportation{
    private final static int TRAVEL_SEC_PER_BREAK = 30*60;
    private final static int BREAK_TIME_SEC = 15*60;

    /**
     * Gets the time it takes to walk from starting destination to ending destination.
     * @param duration time it takes to walk from starting destination to ending destination
     */
    public Walking(int duration){
        super(duration);
    }

    /**
     * Calculates the break time when walking.
     * @return returns break time when walking
     */
    protected int calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec/ TRAVEL_SEC_PER_BREAK);
        breakTimeOfTravel = (int) numOfBreak * BREAK_TIME_SEC;
        return breakTimeOfTravel;
    }

    /**
     * ToString method
     * @return returns the mode of transportation
     */
    public String toString(){
        return TransportationFactory.WALKING_TYPE;
    }

    /**
     * Compares the new event object to another event.
     * @param other another event the is scheduled
     * @return new event
     */
    public boolean equals(Object other){
        return(other.getClass().equals(this));
    }

}
