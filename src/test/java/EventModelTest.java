import java.lang.reflect.Field;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class EventModelTest {
        @org.junit.Test
        public void testEventModel() {
            EventModel model = new EventModel("testEventModel.se");
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

            assertEquals(true,model.isTimeOccupied(new GregorianCalendar(2019,1,15)));
        }
    }
