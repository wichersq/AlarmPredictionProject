import javax.swing.*;

/**
 * Text Area class that can listen the change from the model.
 */
public class TextAreaDetail extends JTextArea implements Listener {
    /**
     * Constructor for the class.
     *
     * @param list selection list
     */
    public TextAreaDetail(CalendarListElement list) {
        list.addListener(this);
    }

    /**
     * Updates whenever the model changes.
     *
     * @param ob updating object
     */
    @Override
    public void update(Object ob) {
        if (ob instanceof CalendarEvent) {
            CalendarEvent event = (CalendarEvent) ob;
            setText(event.toString());
        }
    }
}
