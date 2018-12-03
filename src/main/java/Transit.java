/**
 * Class Transit calculates time it would take for the user to get to ending destination using public transportation/
 */
public class Transit extends Transportation {
    private final static int TRAVEL_SEC_PER_BREAK = 4 * 60 * 60;

    public Transit(int duration, int distance) {
        super(duration, distance);
    }

    /**
     * Calculates break time when using public transportation.
     *
     * @return break time when using public transportation
     */
    protected int calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec / TRAVEL_SEC_PER_BREAK);
        breakTimeOfTravel = (int) numOfBreak * BREAK_TIME_SEC;
        return breakTimeOfTravel;
    }

    /**
     * Get string mode of the transportation
     *
     * @return String of the mode of transportation
     */
    public String toString() {
        return CalendarEvent.TRANSIT_TYPE;
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
