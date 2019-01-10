
import java.lang.reflect.Method;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for ReadyTimeCalc class
 */
public class ReadyTimeCalcTest {
    @org.junit.Test
    public void testReadyTimeCalc() {
        EventModel model = new EventModel("TestingFile.se");
        UserInputFrame userInput = new UserInputFrame(500);
        OutputFrame outputFrame = new OutputFrame(model, 500);
        Controller controller = new Controller(userInput, model, outputFrame);
        ReadyTimeCalc calc = new ReadyTimeCalc(model,outputFrame,controller,true);
        CalendarEvent event = null;
        try {
            Method privateMethod = ReadyTimeCalc.class.getDeclaredMethod("createEventType",
                    boolean.class,String.class, String.class,String.class,
                    String.class,String.class,GregorianCalendar.class,String.class,
                    int.class,int.class,double.class,double.class);
            privateMethod.setAccessible(true);
            event = (CalendarEvent) privateMethod.invoke(calc, false, "testing","testing",
                    "testing event", "testing","testing", new GregorianCalendar(2019,1,15),
                    "BIKING",15, 1,4,0.0);
        } catch (ReflectiveOperationException x) {
            x.printStackTrace();
        }
        assertEquals(true,event.getClass().equals(EventWithoutInfo.class));
    }
}
