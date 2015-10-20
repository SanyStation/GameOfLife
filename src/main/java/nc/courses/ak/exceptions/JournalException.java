package nc.courses.ak.exceptions;

/**
 * Thrown when occurs the exception in the heirs of the class {@code Journal}
 * 
 * @author Alexander Kovriga
 */
public class JournalException extends Exception {
    
    private final static String PREFIX = "Journal: ";
    
    public JournalException() {
        super(PREFIX);
    }
    
    public JournalException(String message) {
        super(PREFIX + message);
    }
    
}
