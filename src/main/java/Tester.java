import sun.plugin.javascript.navig4.Link;

import java.util.LinkedList;

public class Tester {

    public static void main(String[] args) {
        String filePath = "CalendarEvent.se";
        EventModel model = new EventModel(filePath);
        UserInputFrame userInput = new UserInputFrame(500);
        OutputFrame outputPanel = new OutputFrame(model,500);
        Controller controller = new Controller( userInput, model, outputPanel);
        LinkedList<Integer> list = new LinkedList<>();
        Thread thread = new Thread(new ConsumerThread(list));
        Thread thread2 = new Thread(new ProducerThread(list));

    }
}
