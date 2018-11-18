public class Walking extends Transportation {
    final static int TRAVEL_TIME_PER_BREAK = 90*60;

    public Walking(int duration){
        super(duration);
    }
    public double calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec/TRAVEL_TIME_PER_BREAK);
        return numOfBreak * BREAK_TIME_MIN;
    }



    public String toString(){
        return "Walking";
    }
    public boolean equals(Object other){
        return(other.getClass().equals(this));
    }
}
