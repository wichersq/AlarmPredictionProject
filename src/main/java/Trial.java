import java.io.*;
import java.util.GregorianCalendar;

public class Trial {

    public static void main(String[] args) {
        EventModel model = new EventModel();
        new UserInputFrame(model, 500, new OutputPanelTemp(model, 500));

        CalendarEvent e = new CalendarEvent("511 Walker Dr, Apt3, Mountain View, CA 94043",
                "1401 N Shoreline Blvd, Mountain View, CA 94043", "Computer History Museum",
                new GregorianCalendar(2018, 12, 30, 15, 45),
                new Biking(1200), 4.5);
        EventWithPlaceInfo e2 = new EventWithPlaceInfo("511 Walker Dr, Apt3, Mountain View, CA 94043",
                "1401 N Shoreline Blvd, Mountain View, CA 94043", "Computer History Museum",
                new GregorianCalendar(2018, 10, 29, 4, 45),
                new Walking(1200), 4.5, 3.5);
        System.out.println(e);
        try {
            FileOutputStream fileOut = new FileOutputStream("Biking.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.writeObject(e2);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /Biking.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }

        try {

            FileInputStream fileIn = new FileInputStream("Biking.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            System.out.println(in.available());
            while (true) {
                try {
                    CalendarEvent event = (CalendarEvent) in.readObject();
                    System.out.println(event);
                } catch (EOFException exc) {
                    break;
                }
            }
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
//        System.out.println(e);
//            CarShape car = new CarShape(10,10,50);
//            JFrame frame = new JFrame();
////            ShapeIcon icon = new ShapeIcon(car);
////            JLabel label = new JLabel(icon);
//            frame.setLayout(new FlowLayout());
//            frame.add(label);
//            frame.setDefaultLookAndFeelDecorated(true);
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.pack();
//            frame.setBounds(0,0,500,500);
//            frame.setVisible(true);
//            final int DELAY = 100;
//            Timer t = new Timer(DELAY, (ActionEvent event) -> {
//                icon.move();
//                label.repaint();
//            });
//            t.start();
    }

}
