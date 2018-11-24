import java.util.GregorianCalendar;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 */
public class ReadyTimeCalc implements Runnable {
    private LinkedBlockingQueue<ChangedObject> sharedQueue;
    private EventModel model;
    private PopUpFrame popUp;
    private OutputFrame outputPanel;
    private CalendarEvent currentEvent;
    private Controller controller;
    private DataRequest dataRequest;

    /**
     *
     * @param model
     * @param outputPanel
     * @param sharedQueue
     * @param controller
     * @param apiKey
     */
    public ReadyTimeCalc(EventModel model, OutputFrame outputPanel, LinkedBlockingQueue<ChangedObject> sharedQueue,
                         Controller controller, String apiKey) {
        this.dataRequest = new DataRequest(apiKey);
        this.controller = controller;
        this.popUp = new PopUpFrame();
        this.sharedQueue = sharedQueue;
        this.model = model;
        this.outputPanel = outputPanel;
        responseToButtonOfPopUp();
    }

    /**
     *
     */
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("start run");
                ChangedObject event = sharedQueue.take();
                System.out.println("Start to pull data request");
                pullDataRequest(event);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param ob
     */
    private void pullDataRequest(ChangedObject ob) {
        System.out.println("pull data request now");
        GregorianCalendar arrivalDateTime = ob.getArrivalDateTime();
        double importantScale = ob.getImportantScale();

        //TODO: This will need Api Key to run

//        dataRequest.requestMapData(ob.getAddressFrom(), ob.getAddressTo(),
//                        ob.getTransport(), arrivalDateTime);
//        String destName = dataRequest.getDestName();
//        String originName = dataRequest.getOriginName();
//        long durationSec = dataRequest.getDurationSec();
//        double rating = (double) dataRequest.getRating();
//        String startAddress = dataRequest.getStartAddress();
//        String endAddress = dataRequest.getEndAddress();

        //TODO: this is for example without using API Key
        String destName = "570 N Shoreline Blvd";
        String originName = "189 Central Ave";
        long durationSec = 286;
        double rating = 0.0;
        String startAddress = "189 Central Ave, Mountain View, CA 94043, USA";
        String endAddress = "570 N Shoreline Blvd, Mountain View, CA 94043, USA";

        Transportation transport = TransportationFactory.createTransport(ob.getTransport(), (int) durationSec);
        currentEvent = CalendarEventFactory.createEvenType(startAddress,
                endAddress, ob.getName(), originName, destName, arrivalDateTime,
                transport, importantScale, rating);
        popUp.showPopUp(currentEvent.getEventInfo());
    }

    /**
     *
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
            outputPanel.setVisible(true);
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
