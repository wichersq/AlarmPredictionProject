public class Driving extends Transportation {
    final static int TRAVEL_TIME_PER_BREAK = 120;

    public Driving(int duration){
        super(duration);
    }
    public double calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec/TRAVEL_TIME_PER_BREAK);
        return numOfBreak * BREAK_TIME_MIN;
    }

    public double getTotalTimeTravel(){
        return calculateBreakTime() + durationInSec;
    }

    public String toString(){
        return "Driving";
    }
    public boolean equals(Object other){
        return(other.getClass().equals(this));
    }
}
