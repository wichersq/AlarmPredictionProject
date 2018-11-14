public class Tester {

    public static void main(String[] args) {
        EventModel model = new EventModel();
        new UserInputFrame(model,500);
        new OutputPanelTemp(model,500);

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
