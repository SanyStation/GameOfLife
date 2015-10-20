package nc.courses.ak.game;

import nc.courses.ak.exceptions.IllegalEventFormatGameJournalException;
import nc.courses.ak.exceptions.IllegalEventFormatJournalException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Alexander Kovriga
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameRecordTest {
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("\n[GameRecordTest] started");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("[GameRecordTest] ended\n");
    }
    
    @Test(expected = IllegalEventFormatGameJournalException.class)
    public void testAbscissaParsing() throws IllegalEventFormatGameJournalException,
            IllegalEventFormatJournalException {
        System.out.println("Abscissa_Parsing");
        GameRecord gr = new GameRecord("2013-12-01 18:30:40 . Game a 0 "
                + "Borned !");
    }
    
    @Test(expected = IllegalEventFormatGameJournalException.class)
    public void testOrdinateParsing()
            throws IllegalEventFormatGameJournalException,
            IllegalEventFormatJournalException {
        System.out.println("Ordinate_Parsing");
        GameRecord gr = new GameRecord("2013-12-01 18:30:40 !!!!! Game 0 Ad "
                + "Died !");
    }

    @Test
    public void testEventParsing() throws IllegalEventFormatJournalException,
            IllegalEventFormatGameJournalException {
        System.out.println("Event_Parsing");
        GameRecord gr = new GameRecord("2013-12-01 18:30:40 !!!!! Game 10 46 "
                + "Died !");
    }
    
}
