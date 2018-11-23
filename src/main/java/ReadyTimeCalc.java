import java.util.GregorianCalendar;
import java.util.concurrent.LinkedBlockingQueue;

public class ReadyTimeCalc implements Runnable {
    private LinkedBlockingQueue<ChangedObject> sharedQueue;
    private EventModel model;
    private PopUpFrame popUp;
    private OutputFrame outputPanel;
    private CalendarEvent currentEvent;
    private Controller controller;

    public ReadyTimeCalc(EventModel model, OutputFrame outputPanel,
                         LinkedBlockingQueue<ChangedObject> sharedQueue, Controller controller) {
        this.controller = controller;
        this.popUp = new PopUpFrame();
        this.sharedQueue = sharedQueue;
        this.model = model;
        this.outputPanel = outputPanel;
        responseToButtonOfPopUp();
    }

    @Override
    public void run() {
        while (true) {
            try {
                ChangedObject event = sharedQueue.take();
                pullDataRequest(event);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void pullDataRequest(ChangedObject ob) {
        GregorianCalendar arrivalDateTime = ob.getArrivalDateTime();
        double importantScale = ob.getImportantScale();

        //TODO: This will need Api Key to run
//        DataRequest.pullMapRequest(ob.getAddressFrom(), ob.getAddressTo(),
//                        ob.getTransport(), arrivalDateTime);
//        String destName = DataRequest.getDestName();
//        String originName = DataRequest.getOriginName();
//        long durationSec = DataRequest.getDurationSec();
//        double rating = (double) DataRequest.getRating();
//        String startAddress = DataRequest.getStartAddress();
//        String endAddress = DataRequest.getEndAddress();

        //TODO: this is for example
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

    public void adjustReadyTime(int changingTime) {
        currentEvent.editReadyTime(changingTime);
        String alarmStr = currentEvent.getEventInfo();
        popUp.showPopUp(alarmStr);
    }

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
