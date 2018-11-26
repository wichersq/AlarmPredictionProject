/**
 *
 */
public class Driving extends Transportation {
    private final static int TRAVEL_SEC_PER_BREAK = 2*60*60;

    /**
     *
     * @param duration
     */
    public Driving(int duration){
        super(duration);
    }

    /**
     *
     * @return
     */
    protected int calculateBreakTime() {
        System.out.println("durationInSec " + durationInSec);
        double numOfBreak = Math.floor(durationInSec/ TRAVEL_SEC_PER_BREAK);
        System.out.println("numOfBreak " + numOfBreak);
        System.out.println("numOfBreak * BREAK_TIME_MIN " + numOfBreak * BREAK_TIME_MIN);
        breakTimeOfTravel = (int) (numOfBreak * BREAK_TIME_MIN);
        return breakTimeOfTravel;
    }

    /**
     *
     * @return
     */
    public String toString(){
        return TransportationFactory.DRIVING_TYPE;
    }

    /**
     *
     * @param other
     * @return
     */
    public boolean equals(Object other){
        return(other.getClass().equals(this));
    }
}
