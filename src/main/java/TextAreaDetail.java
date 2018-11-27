import javax.swing.*;

public class TextAreaDetail extends JTextArea implements Listener {

    public TextAreaDetail(MyList list){
        list.addListener(this);
    }
    @Override
    public void update(Object ob) {
        if(ob instanceof CalendarEvent){
            CalendarEvent event = (CalendarEvent)ob;
            setText(event.getStringDetail());
        }
    }
}
