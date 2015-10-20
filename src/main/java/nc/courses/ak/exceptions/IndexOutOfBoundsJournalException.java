package nc.courses.ak.exceptions;

/**
 * Thrown to indicate that an index of instance of the {@code Journal}'s heirs
 * is out of range.
 * 
 * @author Alexander Kovriga
 */
public class IndexOutOfBoundsJournalException extends JournalException {

    public IndexOutOfBoundsJournalException() {
    }
    
    public IndexOutOfBoundsJournalException(String str) {
        super(str);
    }
    
}
