import java.awt.event.ActionEvent;
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
                         LinkedBlockingQueue<ChangedObject> sharedQueue, Controller controller){
        this.controller = controller;
        this.popUp = new PopUpFrame();
        this.sharedQueue = sharedQueue;
        this.model = model;
        this.outputPanel = outputPanel;
        responseToButtonOfPopUp();
    }

    private void pullDataRequest(ChangedObject ob) {
        String addressFrom = ob.getAddressFrom();
        String addressTo = ob.getAddressTo();
        String name = ob.getName();
        GregorianCalendar arrivalDateTime = ob.getArrivalDateTime();
        double importantScale = ob.getImportantScale();
        Transportation transport = TransportationFactory.getTransport(ob.getTransport(), 35 * 60);
        System.out.println(transport);
//        double rating = -1 if no rating
        currentEvent = CalendarEventFactory.createEvenType(addressFrom, addressTo, name,
                arrivalDateTime, transport, importantScale, -2);
        popUp.showPopUp(currentEvent.getAlarmString());
    }

    @Override
    public void run() {
        System.out.println("Consumer start");
        while (true) {
            try {
                ChangedObject event = sharedQueue.take();
                pullDataRequest(event);
            } catch (InterruptedException e) {
                System.out.println("Consumer Interrupted");
            }
        }
    }

    public void adjustReadyTime(int changingTime) {
        currentEvent.editReadyTime(changingTime);
        String alarmStr = currentEvent.getAlarmString();
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

        popUp.addActionAdjustButton(ActionEvent ->{
                int adjustingTime = popUp.getSliderValue();
                if (adjustingTime != 0) {
                    adjustReadyTime(adjustingTime);
                }
            });
    }

}
