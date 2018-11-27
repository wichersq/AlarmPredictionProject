import java.io.Serializable;

/**
 *
 */
public abstract class Transportation  implements Serializable, Cloneable {
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

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
