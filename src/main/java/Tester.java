import java.util.concurrent.LinkedBlockingQueue;

public class Tester {

    public static void main(String[] args) {
        String filePath = "CalendarEvent.se";
        EventModel model = new EventModel(filePath);
        UserInputFrame userInput = new UserInputFrame(500);
        OutputFrame outputPanel = new OutputFrame(model,500);
        LinkedBlockingQueue<ChangedObject> list = new LinkedBlockingQueue<>();
        Controller controller = new Controller( userInput, model, outputPanel, list);
        Thread thread = new Thread(new ReadyTimeCalc(model, outputPanel,list, controller));
        thread.start();
    }
}
