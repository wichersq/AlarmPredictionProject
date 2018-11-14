import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OutputPanelTemp extends JFrame implements Listener{
    JTextArea textArea;
    EventModel model;
    JPanel panel;

    public OutputPanelTemp(EventModel model, int size){
        super.setLayout(new FlowLayout());
        super.setBounds(0, 0, size, size);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createEvenList ();

        this.model = model;
        this.model.addListener(this);

        setVisible(true);
    }
    public void createEvenList (){
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.add(panel);
        textArea = new JTextArea();
        panel.add(textArea, BorderLayout.CENTER);
    }

    @Override
    public void update(Object ob) {
        ArrayList<CalendaEvent> calendarList = model.getEvents();
        for(CalendaEvent event: calendarList){
            textArea.setText(event.toString());
        }
    }
}