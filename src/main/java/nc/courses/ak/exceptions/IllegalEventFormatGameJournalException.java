package nc.courses.ak.exceptions;

/**
 * Thrown if input string of game event is wrong.
 * 
 * @author Alexander Kovriga
 */
public class IllegalEventFormatGameJournalException extends JournalException {
    
    private final static String SUFIX = " Valid format: yyyy-MM-dd hh:mm:ss "
            + "<importance> (\".\", \"!\", \"!!!\", \"!!!!!\") <source> <x> "
            + "<y> <massage>";
    
    public IllegalEventFormatGameJournalException() {
        super(SUFIX);
    }
    
    public IllegalEventFormatGameJournalException(String message) {
        super(message + SUFIX);
    }
    
}
