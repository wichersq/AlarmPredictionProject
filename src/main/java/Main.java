public class Main {
    public static void main(String[] args) {
        //TODO: fix ready time for longer driving time.
        boolean isDryRun = true;
        if(args.length >= 1){
            isDryRun = args[0].equalsIgnoreCase("1");
        }

        String filePath = "CalendarEvent.se";
        EventModel model = new EventModel(filePath);
        UserInputFrame userInput = new UserInputFrame(500);
        OutputFrame outputFrame = new OutputFrame(model, 500);

        Controller controller = new Controller(userInput, model, outputFrame);

        Thread thread = new Thread(new ReadyTimeCalc(model, outputFrame, controller, isDryRun));
        thread.start();
    }
}
