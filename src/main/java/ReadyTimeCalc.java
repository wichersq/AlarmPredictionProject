import java.io.File;
import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 */
public class ReadyTimeCalc implements Runnable {
    private EventModel model;
    private PopUpFrame popUp;
    private OutputFrame outputFrame;
    private CalendarEvent currentEvent;
    private Controller controller;
    private DataRequest dataRequest;
    private boolean isDryRun;

    /**
     * @param model
     * @param outputFrame
     * @param controller
     */
    public ReadyTimeCalc(EventModel model, OutputFrame outputFrame, Controller controller, boolean isDryRun) {
        this.isDryRun = isDryRun;
        if (!isDryRun) {
            this.dataRequest = new DataRequest(readApiKey());
        }
        this.controller = controller;
        this.popUp = new PopUpFrame();
        this.model = model;
        this.outputFrame = outputFrame;
        responseToButtonOfPopUp();
    }

    /**
     * @return
     */
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

    /**
     *
     */
    @Override
    public void run() {
        while (true) {
            requestData(model.getEventToProcess());
        }
    }

    /**
     * @param ob
     */
    private void requestData(ChangedObject ob) {
        System.out.println("pull data request now");
        GregorianCalendar arrivalDateTime = ob.getArrivalDateTime();

        //TODO: this is for example without using API Key
        double importantScale = ob.getImportantScale();
        String destName = "570 N Shoreline Blvd";
        String originName = "189 Central Ave";
        long durationSec = 976*60;
        double rating = 1.0;
        String startAddress = "189 Central Ave, Mountain View, CA 94043, USA";
        String endAddress = "570 N Shoreline Blvd, Mountain View, CA 94043, USA";

        //TODO: This will need Api Key to run
        if (!isDryRun) {
            dataRequest.requestMapData(ob.getAddressFrom(), ob.getAddressTo(),
                    ob.getTransport(), arrivalDateTime);
            destName = dataRequest.getDestName();
            originName = dataRequest.getOriginName();
            durationSec = dataRequest.getDurationSec();
            rating = (double) dataRequest.getRating();
            startAddress = dataRequest.getStartAddress();
            endAddress = dataRequest.getEndAddress();
        }

        Transportation transport = TransportationFactory.createTransport(ob.getTransport(), (int) durationSec);
        currentEvent = CalendarEventFactory.createEvenType(startAddress,
                endAddress, ob.getName(), originName, destName, arrivalDateTime,
                transport, importantScale, rating);
        popUp.showPopUp(currentEvent.getEventInfo());
    }

    /**
     * @param changingTime
     */
    public void adjustReadyTime(int changingTime) {
        currentEvent.editReadyTime(changingTime);
        String alarmStr = currentEvent.getEventInfo();
        popUp.showPopUp(alarmStr);
    }

    /**
     *
     */
    private void responseToButtonOfPopUp() {
        popUp.addActionSaveButton(ActionEvent -> {
            model.addEvent(currentEvent);
            controller.resetUserFrame();
            popUp.setVisible(false);
            outputFrame.setVisible(true);
        });

        popUp.addActionCancelButton(ActionEvent -> {
            popUp.setVisible(false);
            controller.resetUserFrame();
        });

        popUp.addActionEditButton(ActionEvent -> {
            popUp.setVisible(false);
        });

        popUp.addActionAdjustButton(ActionEvent -> {
            int adjustingTime = popUp.getSliderValue();
            if (adjustingTime != 0) {
                adjustReadyTime(adjustingTime);
            }
        });
    }
}
