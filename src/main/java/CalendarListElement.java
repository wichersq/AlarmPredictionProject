import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class to creates JTextArea for each Jlist cell.
 */
public class CalendarListElement extends JTextArea implements ListCellRenderer {
    private ArrayList<Listener> listeners;
    private CalendarEvent currentOb;

    /**
     * Constructor
     *
     * @param rows number of rows in text area
     * @param cols number of cols in text area
     */
    protected CalendarListElement(int rows, int cols) {
        super(rows, cols);
        listeners = new ArrayList<>();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * Returns a text area for each cell
     *
     * @param list         The JList for painting.
     * @param value        The value returned by list.getModel().getElementAt(index).
     * @param index        The cells index.
     * @param isSelected   True if the specified cell was selected.
     * @param cellHasFocus True if the specified cell has the focus.
     * @return A component whose paint() method will render the specified value.
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        setBackground(null);
        CalendarEvent event = (CalendarEvent) value;
        setText(event.getSummaryInfo());
        if (cellHasFocus) {
            currentOb = event;
            setBackground(Color.LIGHT_GRAY);
            notifyListener(value);
        }
        return this;
    }

    /**
     * Get current selection from Jlist
     *
     * @return an current chosen object
     */
    public CalendarEvent getCurrentSelection() {
        return currentOb;
    }



    /**
     * Adds a listener.
     *
     * @param l adding listener
     */
    public void addListener(Listener l) {
        listeners.add(l);
    }

    /**
     * Notifies all listeners.
     *
     * @param object changed object
     */
    private void notifyListener(Object object) {
        for (Listener l : listeners) {
            l.update(object);
//            if(l.getClass() == OutputFrame.class){
//                OutputFrame temp = (OutputFrame) l;
//                ((OutputFrame) l).maybeGrayOutEditButton((CalendarEvent) ob);
//            }
        }
    }
}
