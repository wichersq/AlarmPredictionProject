/**
 * Class Driving determines the estimanted time needed for the user to prepare for an event using driving as the mode of transportation
 */
public class Driving extends Transportation {
    private final static int TRAVEL_SEC_PER_BREAK = 2 * 60 * 60;

    /**
     * @param duration estimated time it would take for user to drive from starting destination to ending destination
     */
    public Driving(int duration) {
        super(duration);
    }

    /**
     *
     * @return returns
     */
    protected int calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec / TRAVEL_SEC_PER_BREAK);
        breakTimeOfTravel = (int) (numOfBreak * BREAK_TIME_SEC);
        return breakTimeOfTravel;
    }

    /**
     * @return returns the mode of transportation
     */
    public String toString() {
        return TransportationFactory.DRIVING_TYPE;
    }


    /**
     * @param other
     * @return
     */
    public boolean equals(Object other) {
        return (other.getClass().equals(this));
    }

}
