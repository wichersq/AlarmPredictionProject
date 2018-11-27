import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyList extends JTextArea implements ListCellRenderer{
    private ArrayList<Listener> listeners;
    private CalendarEvent currentOb;

    protected MyList(int rows, int cols) {
        super(rows, cols);
        listeners = new ArrayList<>();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());
        setBackground(null);
        if (cellHasFocus) {
            System.out.println(index);
            if(value instanceof CalendarEvent) {
                currentOb = (CalendarEvent) value;
            }
            setBackground(Color.LIGHT_GRAY);
            notifyListener(value);
        }
        return this;
    }

    public CalendarEvent getCurrentSelection(){
        return currentOb;
    }
    /**
     * adds a listener
     *
     * @param l adding listener
     */
    public void addListener(Listener l) {
        listeners.add(l);
    }
    /**
     * notifies all listener
     *
     * @param object changed object
     */
    private void notifyListener(Object object) {
        for (Listener l : listeners) {
            l.update(object);
        }
    }
}
