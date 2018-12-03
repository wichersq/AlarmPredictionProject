
import static org.junit.Assert.assertEquals;

/**
 * A class shows example of unit test
 */
public class TestMethod {
    @org.junit.Test
    public void calculateTotalDrivingDuration() {
        int duration = 3 * 60 * 60; //Driving 3 hours
        Driving driving = new Driving(duration);
        driving.calculateBreakTime();
        assertEquals(210, driving.getTotalTravelMin());
    }
}
