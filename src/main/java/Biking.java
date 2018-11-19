public class Biking extends Transportation {
    private final static int TRAVEL_SEC_PER_BREAK = 60*60;
    private final static int BREAK_TIME_MIN = 15;

    public Biking(int durationInSec){
        super(durationInSec);
    }

    protected double calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec/ TRAVEL_SEC_PER_BREAK);
        return numOfBreak* BREAK_TIME_MIN;
    }

    public String toString(){return "Biking";}

    public boolean equals(Object other){
        return(other.getClass().equals(this));
    }
}
