/**
 * Class Biking determines the amount of time it would take for a person to bike to the determined destination
 */
public class Biking extends Transportation {
    private final static int TRAVEL_SEC_PER_BREAK = 60*60;
    private final static int BREAK_TIME_MIN = 15;

    /**
     * 
     * @param durationInSec how long it would take to bike to the given destionation
     */
    public Biking(int durationInSec){
        super(durationInSec);
    }

    /**
     *
     * @return returns the estimated time of travel
     */
    protected int calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec/ TRAVEL_SEC_PER_BREAK);
        breakTimeOfTravel = (int) numOfBreak * BREAK_TIME_MIN;
        return breakTimeOfTravel;
    }

    /**
     *
     * @return returns 
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
