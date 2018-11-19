import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Tester {

    public static void main(String[] args) {
        EventModel model = new EventModel();
        new UserInputFrame(model, 500,new OutputPanelTemp(model, 500));

    }
}
