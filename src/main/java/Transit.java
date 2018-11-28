/**
 * Class Transit calculates time it would take for the user to get to ending destination using public transportation/
 */
public class Transit extends Transportation {
    private final static int TRAVEL_SEC_PER_BREAK = 4 * 60 * 60;

    public Transit(int duration) {
        super(duration);
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
     * toString method
     * @return the string mode of transportation
     */
    public String toString() {
        return TransportationFactory.TRANSIT_TYPE;
    }

    /**
     * Provides another object to compare the time and date with the new event
     * @param other another event
     * @return new event
     */
    public boolean equals(Object other) {
        return (other.getClass().equals(this));
    }
}
