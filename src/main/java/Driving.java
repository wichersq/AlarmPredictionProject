public class Driving extends Transportation {
    private final static int TRAVEL_SEC_PER_BREAK = 2*60*60;


    public Driving(int duration){
        super(duration);
    }
    protected double calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec/ TRAVEL_SEC_PER_BREAK);
        return numOfBreak * BREAK_TIME_MIN;
    }

    public String toString(){
        return "Driving";
    }
    public boolean equals(Object other){
        return(other.getClass().equals(this));
    }
}
