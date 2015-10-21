package nc.courses.ak.journal;

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
public class RecordTest {

    @BeforeClass
    public static void setUpClass() {
        System.out.println("\n[RecordTest] started");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("[RecordTest] ended\n");
    }

    @Test(expected = IllegalEventFormatJournalException.class)
    public void testTimeParsing() throws IllegalEventFormatJournalException {
        System.out.println("Time_Parsing");
        Record r = new Record("2013-12-01 18:3040 !!!!! Message !");
    }

    @Test(expected = IllegalEventFormatJournalException.class)
    public void testDateParsing() throws IllegalEventFormatJournalException {
        System.out.println("Date_Parsing");
        Record r = new Record("01a3-2-01 18:30:40 !!!!! Message !");
    }

    @Test(expected = IllegalEventFormatJournalException.class)
    public void testImportanceParsing()
            throws IllegalEventFormatJournalException {
        System.out.println("Importance_Parsing");
        Record r = new Record("2013-12-01 18:30:40 !!!+ Message !");
    }

    @Test
    public void testEventParsing()
            throws IllegalEventFormatJournalException {
        System.out.println("Event_Parsing");
        Record r = new Record("2013-12-01 18:30:40 !!!!! Message!");
    }

}
