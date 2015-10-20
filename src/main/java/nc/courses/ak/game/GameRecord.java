package nc.courses.ak.game;

import java.text.SimpleDateFormat;
import java.util.Date;
import nc.courses.ak.exceptions.IllegalEventFormatGameJournalException;
import nc.courses.ak.exceptions.IllegalEventFormatJournalException;
import nc.courses.ak.journal.Record;

/**
 *
 * @author Alexander Kovriga
 */
public class GameRecord extends Record {
    
    private final int x;
    private final int y;
    private static int tmpX;
    private static int tmpY;
    
    public GameRecord(Date date, int importance, String source, int x,
            int y, String message) {
        super(date, importance, source, message); 
        this.x = x;
        this.y = y;
    }
    
    public GameRecord(String event) 
            throws IllegalEventFormatGameJournalException,
            IllegalEventFormatJournalException {
        super(cutCoordinates(event));
        x = tmpX;
        y = tmpY;
    }
    
    private static String cutCoordinates(String str) 
            throws IllegalEventFormatGameJournalException {
        String[] eventBlocks = str.split("\\s");
        if (eventBlocks.length < 7) {
            throw new IllegalEventFormatGameJournalException("Wrong format of "
                    + "event.");
        }
        try {
            tmpX = Integer.parseInt(eventBlocks[4]);
        } catch(NumberFormatException ex) {
            throw new IllegalEventFormatGameJournalException("Wrong fromat of "
                    + "abscissa. " + eventBlocks[4]);
        }
        try {
            tmpY = Integer.parseInt(eventBlocks[5]);
        } catch(NumberFormatException ex) {
            throw new IllegalEventFormatGameJournalException("Wrong fromat of "
                    + "ordinate. " + eventBlocks[5]);
        }
        String retString = eventBlocks[0];
        for (int i = 1; i < 4; ++i) {
            retString += " " + eventBlocks[i];
        }
        for (int i = 6; i < eventBlocks.length; ++i) {
            retString += " " + eventBlocks[i];
        }
        return retString;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && this.getX() == ((GameRecord) obj).getX() &&
                this.getY() == ((GameRecord) obj).getY();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + super.hashCode();
        hash = 43 * hash + this.x;
        hash = 43 * hash + this.y;
        return hash;
    }
    
    @Override
    public String toString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(super.getDate()) + " " + String.format("%-5s",
                        Importance.values()[super.getImportance() - 1]) + " " +
                super.getSource() + " X:" + String.format("%-4s", x) + " Y:" +
                String.format("%-4s", y) + " " + super.getMessage();
    }
}
