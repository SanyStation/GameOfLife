package nc.courses.ak.exceptions;

/**
 * Thrown to indicate that a left bound more than right in instance of the
 * {@code Journal}'s heirs (ex. in sort methods).
 * 
 * @author Alexander Kovriga
 */
public class IllegalBoundsJournalException extends JournalException {
    
    public IllegalBoundsJournalException() {
    }
    
    public IllegalBoundsJournalException(String str) {
        super(str);
    }
    
}
