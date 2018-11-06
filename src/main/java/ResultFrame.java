import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ResultFrame extends JFrame implements Listener{
    JTextArea textArea;
    EventModel model;
    JPanel panel;
    public ResultFrame(EventModel model, int size){
        super.setLayout(new FlowLayout());
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        super.setBounds(0, 0, size, size);
        this.add(panel);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.model = model;
        model.addListener(this);
        createEvenList ();
        panel.add(textArea, BorderLayout.CENTER);
        setVisible(true);
    }
    public void createEvenList (){
        textArea = new JTextArea();
    }


    @Override
    public void update(Object ob) {
        ArrayList<CalendaEvent> calendarList = model.getEvents();
        for(CalendaEvent event: calendarList){
            System.out.println("hello");
            textArea.setText(event.toString());
        }
    }
}
