public class Driving implements Transportation {
    final static int TRAVEL_TIME_PER_BREAK = 120 *60;
    private double durationInSec;
    public Driving(int duration){
        durationInSec = duration;
    }
    public double calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec/TRAVEL_TIME_PER_BREAK);
        return numOfBreak * BREAK_TIME;
    }

    public double getTotalTimeTravel(){
        return calculateBreakTime() + READY_SEC + durationInSec;
    }

    public String toString(){
        return "Driving";
    }
    public boolean equals(Object other){
        return(other.getClass().equals(this));
    }
}
