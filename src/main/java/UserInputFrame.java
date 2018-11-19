import javax.swing.*;
import java.awt.*;

    /**
     * ShapeFrame contain box of buttons and panel
     */
    public class UserInputFrame extends JFrame {
        private Box box = Box.createHorizontalBox();
        private EventModel model;
        private UserPanel panel;
        private Controller controller;

        /**
         * Constructor
         * @param size  size of the frame
         */
        public UserInputFrame(EventModel model, int size, OutputPanelTemp outputPan) {
        		setTitle("Event Scheduler");
        		super.setLayout(new BorderLayout());
            super.setBounds(0, 0, size, size);
            setDefaultLookAndFeelDecorated(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel = new UserPanel( size * 3 / 4);
            this.model = model;
            controller = new Controller(panel,model,outputPan);
            add(panel, BorderLayout.CENTER);
//            addButtonsToBox();
            add(box, BorderLayout.NORTH);
            setVisible(true);
        }

    }
