import java.io.Serializable;

/**
 *
 */
public abstract class Transportation  implements Serializable {
    final static int BREAK_TIME_SEC = 30*60;
    protected int durationInSec;
    protected int travelInSec;
    protected int breakTimeOfTravel;
    Transportation(int duration){
        durationInSec = duration;
        setTotalMinTravel();
    }

    /**
     *
     * @return
     */
    abstract int calculateBreakTime();

    /**
     *
     */
    private void setTotalMinTravel(){
        travelInSec = calculateBreakTime() + durationInSec;
    }

    public int getDurationInMin(){
        return durationInSec/60;
    }
    public String getDurationString(){
        int min = (durationInSec % (60*60))/60;
        int hour = durationInSec /(60*60);
        return String.format("%d %s %d %s", hour, hour > 1 ? "hours" : "hour",
                min, min > 1 ? "minutes" : "minute");
    }

    /**
     *
     * @return
     */
    public int getTotalTravelMin(){
        return travelInSec/60;
    }

    /**
     *
     * @return
     */
    public abstract String toString();
    public abstract Transportation copy();

}
