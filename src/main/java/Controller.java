import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 */
public class Controller implements Listener {
    private UserInputFrame userInput;
    private EventModel model;
    private OutputFrame outputPanel;

    /**
     *
     * @param userInput
     * @param model
     * @param outputPanel
     */
    public Controller(UserInputFrame userInput, EventModel model, OutputFrame outputPanel) {
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
    private void requestData(ChangedObject ob) {
        model.addEventToProcess(ob);
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
            requestData((ChangedObject) ob);
        }
    }
}


