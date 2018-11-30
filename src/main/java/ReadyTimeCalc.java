import java.io.File;
import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Class ReadyTimeCalc calculates the time it would take for the user to prepare for an event set at a specified place,
 * using specified mode of transportation, and taking into account the level of priority of the event.
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
     * Constructor for the class.
     *
     * @param model       model that using to get values
     * @param outputFrame output of the program
     * @param controller  controller of the program
     */
    public ReadyTimeCalc(EventModel model, OutputFrame outputFrame, Controller controller, boolean isDryRun) {
        this.isDryRun = isDryRun;
        if (!isDryRun) {
            this.dataRequest = new DataRequest(readApiKey());
        }
        this.controller = controller;
        this.model = model;
        this.outputFrame = outputFrame;
    }

    /**
     * Gets the api key from a file.
     *
     * @return api key
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
     * Runs the program.
     */
    @Override
    public void run() {
        while (true) {
            try {
                requestData(model.getEventToProcess());
            }catch (Exception e){
                break;
            }
        }
    }

    /**
     * Process the raw input from user and call api on that information.
     *
     * @param ob information object
     */
    private void requestData(RawUserInput ob) {
        GregorianCalendar arrivalDateTime = ob.getArrivalDateTime();
        boolean gotInfoSuccessfully = false;
        String destName;
        String originName;
        long durationSec;
        double rating;
        String startAddress;
        String endAddress;
        double importantScale = ob.getImportantScale();
        // this is for example without using API Key
        if (isDryRun) {
            destName = "570 N Shoreline Blvd";
            originName = "189 Central Ave";
            durationSec = 976 * 60;
            rating = 0.0;
            startAddress = "189 Central Ave, Mountain View, CA 94043, USA";
            endAddress = "570 N Shoreline Blvd, Mountain View, CA 94043, USA";
        }
//         This will need Api Key to run
        else {
            gotInfoSuccessfully = dataRequest.requestMapData(ob.getAddressFrom(), ob.getAddressTo(),
                    ob.getTransport(), arrivalDateTime);
            destName = dataRequest.getDestName();
            originName = dataRequest.getOriginName();
            durationSec = dataRequest.getDurationSec();
            rating = (double) dataRequest.getRating();
            startAddress = dataRequest.getStartAddress();
            endAddress = dataRequest.getEndAddress();
        }
        currentEvent = createEventType(gotInfoSuccessfully, startAddress,
                endAddress, ob.getName(), originName, destName, arrivalDateTime,
                ob.getTransport(), (int) durationSec, importantScale, rating);
        popUp = currentEvent.createPopUp();
        createsButtonOfPopUp();
    }

    /**
     *  Creates type of scheduled event depends on the info.
     *
     * @param addressFrom     Address of destination
     * @param addressTo       Address of starting
     * @param eventName       name of event
     * @param originName      name of origin place
     * @param destName        name of destination
     * @param arrivalDateTime arrival date and time
     * @param transport       Mode of transportation
     * @param duration        duration of travel
     * @param importantScale  The importance level of an event to the user
     * @param rating          rating of the business
     * @return all the given user information
     */
    private CalendarEvent createEventType(boolean gotInfoSuccessfully, String addressFrom, String addressTo, String eventName,
                                          String originName, String destName, GregorianCalendar arrivalDateTime,
                                          String transport, int duration, double importantScale, double rating) {
        if (gotInfoSuccessfully) {
            return new EventWithPlaceInfo(addressFrom, addressTo, eventName,
                    originName, destName, arrivalDateTime, transport, duration, importantScale, rating);
        } else {

            return new EventWithoutInfo(addressFrom, addressTo, eventName, arrivalDateTime, transport, importantScale);
        }
    }

    /**
     * Adjust the ready time from the user request.
     *
     * @param changingTime adjusts the preparation time according to the user input
     */
    public void adjustReadyTime(int changingTime) {
        currentEvent.editReadyTime(changingTime);
        String alarmStr = currentEvent.getEventInfo();
        popUp.showPopUp(alarmStr);

    }

    /**
     * Creates buttons for the PopUpFrame.
     */
    private void createsButtonOfPopUp() {
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

        popUp.addActionEditButton(ActionEvent -> popUp.setVisible(false));

        popUp.addActionAdjustButton(ActionEvent -> {
            int adjustingTime = popUp.getSliderValue();
            if (adjustingTime != 0) {
                adjustReadyTime(adjustingTime);
            }
        });
    }
}
