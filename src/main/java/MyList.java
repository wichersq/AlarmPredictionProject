import javax.swing.*;
import java.awt.*;

public class MyList extends JTextArea implements ListCellRenderer {
    protected MyList(int rows, int cols) {
        super(rows, cols);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());
        setBackground(null);
        if (cellHasFocus) {
            setBackground(Color.LIGHT_GRAY);
        }
        return this;
    }
}
