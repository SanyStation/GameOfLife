package nc.courses.ak.game;

import java.awt.*;
import java.util.Date;
import javax.swing.*;
import nc.courses.ak.journal.Journal;

/**
 * 
 * 
 * @author Alexander Kovriga
 */
public class Field extends JPanel {

    private static final int UNITS_PER_LINE = 50;
    private final Unit[][] matrix = new Unit[UNITS_PER_LINE][UNITS_PER_LINE];
    private double unitSide;
    private int minSide;
    private GameThread gameThread;
    private long sleepIntrval = 250;
    private boolean canInsert = true;
    private Journal log = null;

    private class GameThread extends Thread {

        private volatile boolean execute;

        public void stopExecution() {
            execute = false;
        }

        @Override
        public void run() {
            this.execute = true;
            while (execute) {
                for (int i = 0; i < UNITS_PER_LINE; ++i) {
                    for (int j = 0; j < UNITS_PER_LINE; ++j) {
                        int aliveNeighbors = 0;
                        for (int k = i - 1; k < i + 2; ++k) {
                            for (int l = j - 1; l < j + 2; ++l) {
                                int m = (k == -1) ? UNITS_PER_LINE - 1
                                        : (k >= UNITS_PER_LINE) ? 0 : k;
                                int n = (l == -1) ? UNITS_PER_LINE - 1
                                        : (l >= UNITS_PER_LINE) ? 0 : l;
                                if (matrix[m][n].isAlive()) {
                                    ++aliveNeighbors;
                                }
                            }
                        }
                        if (!matrix[i][j].isAlive()) {
                            if (aliveNeighbors == 3) {
                                matrix[i][j].setWillAlive(true);
                            }
                        } else {
                            if (aliveNeighbors - 1 == 3
                                    || aliveNeighbors - 1 == 2) {
                                matrix[i][j].setWillAlive(true);
                            } else {
                                matrix[i][j].setWillAlive(false);
                            }
                        }
                    }
                }
                for (int i = 0; i < UNITS_PER_LINE; ++i) {
                    for (int j = 0; j < UNITS_PER_LINE; ++j) {
                        if (log != null) {
                            if (matrix[i][j].isAlive() != 
                                    matrix[i][j].willAlive()) {
                                log.add(new GameRecord(new Date(),
                                        (matrix[i][j].willAlive() ? 1 : 4),
                                        "Game", i, j, (matrix[i][j].willAlive()
                                                ? "Borned !" : "Died !")));
//                                System.out.println(log.get(log.size() - 1));
                            }
                        }
                        matrix[i][j].setAlive(matrix[i][j].willAlive());
                    }
                }
                Field.this.updateUI();
                try {
                    Thread.sleep(sleepIntrval);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    public Field() {
        for (int i = 0; i < UNITS_PER_LINE; ++i) {
            for (int j = 0; j < UNITS_PER_LINE; ++j) {
                matrix[i][j] = new Unit();
            }
        }
    }

    public void changeAlive(int x, int y) {
        if (x < 0 || x >= minSide || y < 0 || y >= minSide) {
            return;
        }
        int xPos = (int) (x / unitSide);
        int yPos = (int) (y / unitSide);
        matrix[xPos][yPos].setAlive(canInsert);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int h = getHeight();
        int w = getWidth();

        minSide = w >= h ? h : w;
        unitSide = minSide / (double) UNITS_PER_LINE;
        g2d.draw(new Rectangle.Double(0, 0, minSide, minSide));
        Color tmp = g2d.getColor();
        g2d.setColor(Color.blue);
        g2d.drawString("Press \'s\' to start game.", 10, minSide - 120);
        g2d.drawString("Press \'p\' to pause game.", 10, minSide - 100);
        g2d.drawString("Press \'c\' to stop and clear game.", 10, minSide - 80);
        g2d.drawString("Press \'i\' to insert/delete unit.", 10, minSide - 60);
        g2d.drawString("Press \'=\' to speed up.", 10, minSide - 40);
        g2d.drawString("Press \'-\' to speed down .", 10, minSide - 20);
        g2d.setColor(tmp);

        for (int i = 0; i < UNITS_PER_LINE; ++i) {
            for (int j = 0; j < UNITS_PER_LINE; ++j) {
                if (matrix[i][j].isAlive()) {
                    g2d.fill(new Rectangle.Double(i * unitSide, j * unitSide,
                            unitSide, unitSide));
                }
            }
        }

    }

    public void start() {
        if (gameThread == null) {
            gameThread = new GameThread();
            gameThread.start();
        } else {
            gameThread.stopExecution();
            gameThread = new GameThread();
            gameThread.start();
        }
    }

    public void pause() {
        if (gameThread != null) {
            gameThread.stopExecution();
        }
    }

    public void clear() {
        for (int i = 0; i < UNITS_PER_LINE; ++i) {
            for (int j = 0; j < UNITS_PER_LINE; ++j) {
                matrix[i][j].setWillAlive(false);
                matrix[i][j].setAlive(false);
            }
        }
        Field.this.updateUI();
    }

    public void speedUp() {
        sleepIntrval = (sleepIntrval - 50 < 100) ? 100 : sleepIntrval - 50;
    }

    public void speedDown() {
        sleepIntrval = (sleepIntrval + 50 > 1500) ? 1500 : sleepIntrval + 50;
    }

    public boolean canInsert() {
        return canInsert;
    }

    public void setCanInsert(boolean canInsert) {
        this.canInsert = canInsert;
    }

    public void setLogJournal(Journal log) {
        this.log = log;
    }

}
