public class Tester {

    public static void main(String[] args) {
        String filePath = "CalendarEvent.se";
        EventModel model = new EventModel(filePath);
        UserPanel userInput = new UserPanel(500);
        OutputFrameTemp outputPanel = new OutputFrameTemp(model,500);
        Controller controller = new Controller( userInput, model, outputPanel);

    }
}
