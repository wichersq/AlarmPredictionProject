import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OutputFrameTemp extends JFrame implements Listener{
    JTextArea textArea;
    EventModel model;
    JPanel panel;

    public OutputFrameTemp(EventModel model, int size){
        super.setLayout(new FlowLayout());
        super.setBounds(0, 0, size, size);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        createEvenList ();
        this.model = model;
        this.model.addListener(this);
        setVisible(false);
        textArea.setEditable(false);
    }
    public void createEvenList (){
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.add(panel);
        textArea = new JTextArea();
        panel.add(textArea, BorderLayout.CENTER);
    }

    public void updateTextList(){
        ArrayList<String> calendarList = model.getEvents();
        StringBuilder strBuilder = new StringBuilder();
        int index = 0;
        for(String event: calendarList){
            index ++;
            strBuilder.append(String.format("Event %d:\n",index));
            strBuilder.append(event);
        }
        textArea.setText(strBuilder.toString());
    }

    @Override
    public void update(Object ob) {
        updateTextList();
    }
}
