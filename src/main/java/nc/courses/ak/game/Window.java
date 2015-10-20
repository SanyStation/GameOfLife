package nc.courses.ak.game;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import nc.courses.ak.journal.ArrayJournal;

/**
 *
 * @author Alexander Kovriga
 */
public class Window extends JFrame {

    private JLabel eventLabel;

    public Window() {
        super("Game of life");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Field field = new Field();
        field.addMouseListener(new GameMouseListener());
        field.addMouseMotionListener(new GameMouseMotionListener());
        field.addKeyListener(new GameKeyListener());
        field.setFocusable(true);
        field.setLogJournal(new ArrayJournal());
        getContentPane().add(field);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new Window();
                frame.setPreferredSize(new Dimension(600, 600));
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
