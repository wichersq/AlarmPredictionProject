public class Main {
    public static void main(String[] args) {
        String filePath = "CalendarEvent.se";
        EventModel model = new EventModel(filePath);
        UserInputFrame userInput = new UserInputFrame(500);
        OutputFrame outputPanel = new OutputFrame(model, 500);

        Controller controller = new Controller(userInput, model, outputPanel);

        Thread thread = new Thread(new ReadyTimeCalc(model, outputPanel, controller));
        thread.start();
    }
}
