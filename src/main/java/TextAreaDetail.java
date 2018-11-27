import javax.swing.*;

/**
 * Text Area class that can listen the change from the model
 */
public class TextAreaDetail extends JTextArea implements Listener {
    /**
     * Constructor
     *
     * @param list selection list
     */
    public TextAreaDetail(MyList list) {
        list.addListener(this);
    }

    /**
     * Updates whenever the model change
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
