public abstract class Transportation {
    final static int BREAK_TIME_MIN = 30;
    protected int durationInSec;
    Transportation(int duration){
        durationInSec = duration;
    }
    abstract double calculateBreakTime();
    public double getTotalTimeTravel(){
        return calculateBreakTime() + durationInSec/60;
        }
    public abstract String toString();
}
