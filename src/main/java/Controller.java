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
     * Constructor of the class.
     *
     * @param userInput   user interface
     * @param model       model that stores all events
     * @param outputPanel frame that displays the result
     */
    public Controller(UserInputFrame userInput, EventModel model, OutputFrame outputPanel) {
        this.outputFrame = outputPanel;
        this.userInput = userInput;
        this.model = model;
        userInput.addListener(this);
        createButtonUserInput();
    }

    /**
     * Method where the information from UserInput to queue so the consumer thread can get it.
     *
     * @param ob holds info from raw user input.
     */
    private void requestData(RawUserInput ob) {
        model.addEventToProcess(ob);
    }

    /**
     * Checks if the time and date is already occupied by another event.
     *
     * @param dateTime Time and Date that the event is scheduled
     * @return true if there is an event that is already scheduled at the specified time and date
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
    public void resetUserFrame() {
        userInput.setBackToDefault();
    }

    /**
     * Creates a button.
     */
    private void createButtonUserInput() {
        userInput.addActionShowButton(ActionEvent -> {
            outputFrame.updateTextList();
            outputFrame.setVisible(true);
        });

        outputFrame.addActionDeleteButton(ActionEvent -> {
            deleteEvents(outputFrame.getSelectionFromList());
        });
    }

    /**
     * Deletes events in the event model.
     *
     * @param ob deleting object
     */
    public void deleteEvents(CalendarEvent ob) {

        model.removeEvents(ob);
    }

    /**
     * Updates the user information that was changed.
     *
     * @param ob another object to compare to
     */
    public void update(Object ob) {
        if (ob.getClass().equals(RawUserInput.class)) {
            requestData((RawUserInput) ob);
        }
    }
}


