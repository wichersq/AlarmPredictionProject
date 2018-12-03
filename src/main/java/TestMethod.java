
import static org.junit.Assert.assertEquals;

/**
 * A class shows example of unit test
 */
public class TestMethod {
    @org.junit.Test
    public void calculateTotalDrivingDuration() {
        int duration = 3 * 60 * 60; //Driving 3 hours
        int distance = 200;
        Driving driving = new Driving(duration, distance);
        driving.calculateBreakTime();
        assertEquals(210, driving.getTotalTravelMin());
    }
}
