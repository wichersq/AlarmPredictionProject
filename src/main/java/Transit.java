/**
 * 
 */
public class Transit extends Transportation {
    private final static int TRAVEL_SEC_PER_BREAK = 4*60*60;
       public Transit(int duration){
        super(duration);
    }

    /**
     *
     * @return
     */
    protected double calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec/TRAVEL_SEC_PER_BREAK);
        return numOfBreak * BREAK_TIME_MIN;
    }

    /**
     *
     * @return
     */
    public String toString(){
        return "TRANSIT";
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
