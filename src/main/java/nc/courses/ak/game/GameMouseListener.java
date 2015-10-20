package nc.courses.ak.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Alexander Kovriga
 */
public class GameMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Field field = (Field) e.getSource();
        field.changeAlive(e.getX(), e.getY());
        field.updateUI();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}
