/**
 * Class Biking determines the amount of time it would take for a person to bike to the determined destination.
 */
public class Biking extends Transportation {
    private final static int TRAVEL_SEC_PER_BREAK = 60 * 60;
    private final static int BREAK_TIME_MIN = 15;

    /**
     * Determines time it would take to travel from starting destination to ending destination by bike.
     *
     * @param duration how long it would take to bike to the given destination
     */
    public Biking(int duration, int distance) {
        super(duration,distance);
    }

    /**
     * Calculates estimated break time the user will take using bicycling as the mode of transportation.
     *
     * @return returns the estimated time of travel
     */
    protected int calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec / TRAVEL_SEC_PER_BREAK);
        breakTimeOfTravel = (int) numOfBreak * BREAK_TIME_MIN;
        return breakTimeOfTravel;
    }

    /**
     * Get string mode of the transportation
     *
     * @return String of the mode of transportation
     */
    public String toString() {
        return CalendarEvent.BIKING_TYPE;
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
