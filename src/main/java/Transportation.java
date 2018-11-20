import java.io.Serializable;

public abstract class Transportation  implements Serializable {
    final static int BREAK_TIME_MIN = 30;
    protected int durationInSec;
    protected int totalMinTravel;
    Transportation(int duration){
        durationInSec = duration;
        setTotalMinTravel();
        System.out.println(totalMinTravel);
    }
    abstract double calculateBreakTime();

    private void setTotalMinTravel(){
        totalMinTravel = (int) calculateBreakTime() + durationInSec/60;
    }
    public int getTotalMinTravel(){
        return totalMinTravel;
    }
    public abstract String toString();
}
