

import java.lang.reflect.Field;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
public class ControllerTest {
    @org.junit.Test
    public void testController() {
        EventModel model = new EventModel("TestingFile.se");
        UserInputFrame userInput = new UserInputFrame(500);
        OutputFrame outputFrame = new OutputFrame(model, 500);
        Controller controller = new Controller(userInput, model, outputFrame);
        CalendarEvent event = new EventWithoutInfo("testing","testing",
                "testing event", new GregorianCalendar(2019,1,15),
                "BIKING",4);
        try {
            Field privateHashMapField = EventModel.class.
                    getDeclaredField("events");
            privateHashMapField.setAccessible(true);
            HashMap<GregorianCalendar, CalendarEvent> map = (HashMap<GregorianCalendar, CalendarEvent>) privateHashMapField.get(model);
            map.put(event.getArrivalDateTime(), event);
        }catch (Exception e){e.printStackTrace();}

        assertEquals(true,controller.checkIfTimeOccupied(new GregorianCalendar(2019,1,15)));
    }
}
