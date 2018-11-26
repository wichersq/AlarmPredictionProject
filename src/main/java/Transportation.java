import java.io.Serializable;

/**
 *
 */
public abstract class Transportation  implements Serializable {
    final static int BREAK_TIME_MIN = 30;
    protected int durationInSec;
    protected int travelMin;
    protected int breakTimeOfTravel;
    Transportation(int duration){
        durationInSec = duration;
        setTotalMinTravel();
        System.out.println(travelMin);
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
        travelMin = calculateBreakTime() + durationInSec/60;
    }

    private int getDurationInMin(){
        return durationInSec/60;
    }

    public int getBreakTimeOfTravel(){
        return breakTimeOfTravel;
    }

    /**
     *
     * @return
     */
    public int getTravelMin(){
        return travelMin;
    }

    /**
     *
     * @return
     */
    public abstract String toString();
}
