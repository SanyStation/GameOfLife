package nc.courses.ak.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Alexander Kovriga
 */
public class GameMouseMotionListener implements MouseMotionListener {

    @Override
    public void mouseDragged(MouseEvent e) {
//        System.out.println("Mouse dragged. X: "
//                + e.getX() + " Y: "
//                + e.getY());
        Field field = (Field) e.getSource();
        field.changeAlive(e.getX(), e.getY());
        field.updateUI();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
}
