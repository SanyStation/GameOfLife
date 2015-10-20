package nc.courses.ak.exceptions;

/**
 * Thrown if input string of event is wrong.
 * 
 * @author Alexander Kovriga
 */
public class IllegalEventFormatJournalException extends JournalException {
    
    private final static String SUFIX = " Valid format: yyyy-MM-dd hh:mm:ss "
            + "<importance> (\".\", \"!\", \"!!!\", \"!!!!!\") <source> "
            + "<message>";
    
    public IllegalEventFormatJournalException() {
        super(SUFIX);
    }
    
    public IllegalEventFormatJournalException(String message) {
        super(message + SUFIX);
    }
    
}
