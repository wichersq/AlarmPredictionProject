
/**
 * Class Driving determines the estimated time needed for the user to prepare for an event using driving as the mode of transportation
 */
public class Driving extends Transportation {
    private final static int TRAVEL_SEC_PER_BREAK = 2 * 60 * 60;

    /**
     * Constructor
     *
     * @param duration estimated time it would take for user to drive from starting destination to ending destination
     */
    public Driving(int duration, int distance) {
        super(duration, distance);
    }

    /**
     * Calculates the estimated break time the user will take when driving.
     *
     * @return returns calculated break time
     */

    protected int calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec / TRAVEL_SEC_PER_BREAK);
        breakTimeOfTravel = (int) (numOfBreak * BREAK_TIME_SEC);
        return breakTimeOfTravel;
    }

    /**
     * Get string mode of the transportation
     *
     * @return String of the mode of transportation
     */
    public String toString() {
        return CalendarEvent.DRIVING_TYPE;
    }


    /**
     * Checks if 2 object is the same
     *
     * @param other another object used to compare the new object
     * @return true or false
     */
    public boolean equals(Object other) {
        return (other.getClass().equals(this));
    }

}
