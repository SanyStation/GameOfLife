package nc.courses.ak.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Alexander Kovriga
 */
public class GameKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Field field = (Field) e.getSource();
        switch (e.getKeyChar()) {
            case 's':
                field.start();
                break;
            case 'p':
                field.pause();
                break;
            case 'c':
                field.pause();
                field.clear();
                break;
            case '=':
                field.speedUp();
                break;
            case '-':
                field.speedDown();
                break;
            case 'i':
                field.setCanInsert(!field.canInsert());
                break;
        }
    }

}
