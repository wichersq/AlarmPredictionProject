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
     * Constructor 
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
     * Method where the information from UserInput to queue so the consumer thread can get it
     * @param ob 
     */
    private void requestData(ChangedObject ob) {
        model.addEventToProcess(ob);
    }

    /**
     * Checking if the time and date is already occupied by another event.
     * @param dateTime Time and Date that the event is scheduled
     * @return checks if there is an event that is already scheduled at the specifed time and date
     */
    public boolean checkIfTimeOccupied(GregorianCalendar dateTime) {
        return model.isTimeOccupied(dateTime);
    }

    /**
     * Saves the created event to File.
     */
    public void saveEventsToFile() {
        model.saveEventsToFile();
    }

    /**
     * Clears the information that was inputted.
     */
    public void resetUserFrame(){
        userInput.setBackToDefault();
    }

    /**
     * Creates a button
     */
    private void responseToButtonUserInput() {
        userInput.addActionShowButton(ActionEvent -> {
            outputFrame.updateTextList();
            outputFrame.setVisible(true);
        });
    }

    /**
     * Udates the user information that was changed.
     * @param ob another object to compare to
     */
    public void update(Object ob) {
        if (ob.getClass().equals(ChangedObject.class)) {
            requestData((ChangedObject) ob);
        }
    }
}


