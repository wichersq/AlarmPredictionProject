import java.io.Serializable;

/**
 * Class Transportation determines the estimated time it would take to travel
 * from starting destination to ending destination including the break times.
 */
public abstract class Transportation implements Serializable, Cloneable {
    final static int BREAK_TIME_SEC = 30 * 60;
    protected int durationInSec;
    protected int travelInSec;
    protected int breakTimeOfTravel;
    protected int distance;

    Transportation(int duration, int distance) {
        durationInSec = duration;
        this.distance = distance;
        setTotalMinTravel();
    }

    /**
     * Calculates break time.
     *
     * @return break time during traveling
     */
    abstract int calculateBreakTime();

    /**
     * Sets the minimum time it would take to travel from starting destination to ending destination.
     */
    private void setTotalMinTravel() {
        travelInSec = calculateBreakTime() + durationInSec;
    }

    /**
     * Gets travel time in minute
     * @return  duration in min
     */
    public int getDurationInMin() {
        return durationInSec / 60;
    }

    /**
     * Get travel distance in mile.
     * @return miles of distance.
     */
    public int getTravelDistanceInMile(){
        return distance;
    }
    /**
     * Accessor for TotalTravelMin.
     *
     * @return minimum travel time
     */
    public int getTotalTravelMin() {
        return travelInSec / 60;
    }

    /**
     * Gets string
     *
     * @return a string of object info
     */
    public abstract String toString();

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
