public class Transit implements Transportation {
    final static int TRAVEL_TIME_PER_BREAK = 4*60;

    private double durationInSec;
    public Transit(int duration){
        durationInSec = duration;
    }

    public double calculateBreakTime() {
        double numOfBreak = Math.floor(durationInSec/TRAVEL_TIME_PER_BREAK);
        return numOfBreak * BREAK_TIME_MIN;
    }

    public double getTotalTimeTravel(){
        return calculateBreakTime() + READY_MIN + + durationInSec;
    }

    public String toString(){
        return "Transit";
    }

    public boolean equals(Object other){
        return(other.getClass().equals(this));
    }
}
