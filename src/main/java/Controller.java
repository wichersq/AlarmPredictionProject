import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 */
public class Controller implements Listener {
    private UserInputFrame userInput;
    private EventModel model;
    private OutputFrame outputPanel;
    private LinkedBlockingQueue<ChangedObject> sharedQueue;

    /**
     *
     * @param userInput
     * @param model
     * @param outputPanel
     * @param sharedQueue
     */
    public Controller(UserInputFrame userInput, EventModel model, OutputFrame outputPanel,
                      LinkedBlockingQueue<ChangedObject> sharedQueue) {
        this.sharedQueue = sharedQueue;
        this.outputPanel = outputPanel;
        this.userInput = userInput;
        this.model = model;
        userInput.addListener(this);
        responseToButtonUserInput();
    }

    /**
     *
     * @param ob
     */
    private void pullDataRequest(ChangedObject ob) {
        try {
            sharedQueue.put(ob);
        } catch (InterruptedException ex) {
            System.out.println("Producer Interrupted");
        }
    }

    /**
     *
     * @param dateTime
     * @return
     */
    public boolean checkIfTimeOccupied(String dateTime) {
        return model.isTimeOccupied(dateTime);
    }

    /**
     *
     */
    public void saveEventsToFile() {
        model.saveEventsToFile();
    }

    /**
     *
     */
    public void resetUserFrame(){
        userInput.setBackToDefault();
    }

    /**
     *
     */
    private void responseToButtonUserInput() {
        userInput.addActionShowButton(ActionEvent -> {
            outputPanel.updateTextList();
            outputPanel.setVisible(true);
        });
    }

    /**
     *
     * @param ob
     */
    public void update(Object ob) {
        if (ob.getClass().equals(ChangedObject.class)) {
            pullDataRequest((ChangedObject) ob);
        }
    }
}


