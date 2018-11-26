/**
 *
 */
public class Biking extends Transportation {
    private final static int TRAVEL_SEC_PER_BREAK = 60*60;
    private final static int BREAK_TIME_MIN = 15;

    /**
     *
     * @param durationInSec
     */
    public Biking(int durationInSec){
        super(durationInSec);
    }

    /**
     *
     * @return
     */
    protected double calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec/ TRAVEL_SEC_PER_BREAK);
        return numOfBreak* BREAK_TIME_MIN;
    }

    /**
     *
     * @return
     */
    public String toString(){return TransportationFactory.BIKING_TYPE;}

    /**
     *
     * @param other
     * @return
     */
    public boolean equals(Object other){
        return(other.getClass().equals(this));
    }
}
