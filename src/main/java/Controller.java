import java.util.GregorianCalendar;

/**
 * Class controller mitigates information from the UserInput, EventModel, and OutputFrame
 * Class uses Observer Pattern. Listener from UserInputFrame.
 */
public class Controller implements Listener {
    private UserInputFrame userInput;
    private EventModel model;
    private OutputFrame outputFrame;

    /**
     *
     * @param userInput 
     * @param model
     * @param outputPanel
     */
    public Controller(UserInputFrame userInput, EventModel model, OutputFrame outputPanel) {
        this.outputFrame = outputPanel;
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
     * @param dateTime Tiem and Deate that the event is scheduled
     * @return checks if there is an event that is already scheduled at the specifed time and date
     */
    public boolean checkIfTimeOccupied(GregorianCalendar dateTime) {
        return model.isTimeOccupied(dateTime);
    }

    /**
     * Saves the created event to the model
     */
    public void saveEventsToFile() {
        model.saveEventsToFile();
    }

    /**
     * Clears the information that was inputted
     */
    public void resetUserFrame(){
        userInput.setBackToDefault();
    }

    /**
     * 
     */
    private void responseToButtonUserInput() {
        userInput.addActionShowButton(ActionEvent -> {
            outputFrame.updateTextList();
            outputFrame.setVisible(true);
        });
    }

    /**
     * Udates the user information that was changed
     * @param ob
     */
    public void update(Object ob) {
        if (ob.getClass().equals(ChangedObject.class)) {
            requestData((ChangedObject) ob);
        }
    }
}


