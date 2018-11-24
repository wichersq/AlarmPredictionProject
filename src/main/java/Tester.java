import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class Tester {
    private static String readApiKey() {
        File input = new File("API_Key.txt");
        Scanner scanner = null;
        String apiKey = "";
        try {
            scanner = new Scanner(input);
            while (scanner.hasNext()) {
                apiKey = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.close();
        return apiKey;
    }

    public static void main(String[] args) {
        String filePath = "CalendarEvent.se";
        EventModel model = new EventModel(filePath);
        UserInputFrame userInput = new UserInputFrame(500);
        OutputFrame outputPanel = new OutputFrame(model, 500);
        LinkedBlockingQueue<ChangedObject> list = new LinkedBlockingQueue<>();
        Controller controller = new Controller(userInput, model, outputPanel, list);

        Thread thread = new Thread(new ReadyTimeCalc(model, outputPanel, list, controller,readApiKey()));
        thread.start();
    }
}
