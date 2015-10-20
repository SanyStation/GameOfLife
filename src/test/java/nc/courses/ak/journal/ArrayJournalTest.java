package nc.courses.ak.journal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import nc.courses.ak.exceptions.IllegalEventFormatJournalException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

/**
 *
 * @author Alexander Kovriga
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArrayJournalTest {

    @BeforeClass
    public static void setUpClass() {
        System.out.println("\n[ArrayJournalTest] started");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("[ArrayJournalTest] ended\n");
    }

    @Test
    public void testAdd_Record() {
        System.out.println("Add_Record");
        try {
            Record r = new Record("2013-11-23 20:30:40 !!!!! Console Message");
            ArrayJournal instance = new ArrayJournal();
            instance.add(r);
            assertTrue(instance.get(0).equals(r));
        } catch (IllegalEventFormatJournalException ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void testAdd_Journal() {
        System.out.println("Add_Journal");
        try {
            ArrayJournal instance = new ArrayJournal();
            ArrayJournal tmp = new ArrayJournal();
            tmp.add(new Record("2013-11-23 20:30:40 !!!!! Console Message"));
            tmp.add(new Record("2013-11-27 10:10:10 . Console Message"));
            instance.add(tmp);
            for (int i = 0; i < instance.size(); ++i) {
                assertTrue(instance.get(i).equals(tmp.get(i)));
            }
        } catch (IllegalEventFormatJournalException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testRemove_Record() {
        System.out.println("Remove_Record");
        try {
            ArrayJournal instance = new ArrayJournal();
            instance.add(new Record("2013-11-23 20:30:40 !!!!! Console "
                    + "Message"));
            assertTrue(instance.size() == 1);
            instance.remove(new Record("2013-11-23 20:30:40 !!! Console "
                    + "Message"));
            assertTrue(instance.size() == 1);
            instance.remove(new Record("2013-11-23 20:30:40 !!!!! Console "
                    + "Message"));
            assertTrue(instance.size() == 0);
        } catch (IllegalEventFormatJournalException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testGet() {
        System.out.println("Get");
        try {
            int index = 0;
            ArrayJournal instance = new ArrayJournal();
            Record expResult = new Record("2013-11-23 20:30:40 !!!!! Console "
                    + "Message");
            instance.add(expResult);
            Record result = instance.get(index);
            assertEquals(expResult, result);
        } catch (IllegalEventFormatJournalException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testSet() {
        System.out.println("Set");
        try {
            int index = 0;
            ArrayJournal instance = new ArrayJournal();
            Record r = new Record("2013-11-23 20:30:40 !!!!! Console Message");
            instance.add(r);
            Record expResult = instance.get(index);
            assertEquals(expResult, r);
        } catch (IllegalEventFormatJournalException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testInsert() {
        System.out.println("Insert");
        try {
            Record r = new Record("2013-11-23 20:30:40 !!!!! Console Message");
            ArrayJournal instance = new ArrayJournal();
            instance.insert(0, r);
            assertTrue(instance.get(0).equals(r));
        } catch (IllegalEventFormatJournalException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testRemove_index() {
        System.out.println("Remove_index");
        try {
            ArrayJournal instance = new ArrayJournal();
            instance.add(new Record("2013-11-23 20:30:40 !!!!! Console"
                    + " Message"));
            assertTrue(instance.size() == 1);
            instance.remove(0);
            assertTrue(instance.size() == 0);
        } catch (IllegalEventFormatJournalException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testRemove_fromIndex_toIndex() {
        System.out.println("Remove_fromIndex_toIndex");
        try {
            ArrayJournal instance = new ArrayJournal();
            instance.add(new Record("2013-11-23 20:30:40 !!!!! Console Message"));
            instance.add(new Record("2011-10-23 20:30:40 . Printer Message"));
            instance.add(new Record("2012-09-20 11:12:13 ! Terminal Message"));
            assertTrue(instance.size() == 3);
            instance.remove(0, 1);
            assertEquals(instance.get(0),
                    new Record("2012-09-20 11:12:13 ! Terminal Message"));
            instance.remove(0, 0);
            assertTrue(instance.size() == 0);
        } catch (IllegalEventFormatJournalException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testRemoveAll() {
        System.out.println("RemoveAll");
        try {
            ArrayJournal instance = new ArrayJournal();
            instance.add(new Record("2013-11-23 20:30:40 !!!!! Console Message"));
            instance.add(new Record("2011-10-23 20:30:40 . Printer Message"));
            instance.add(new Record("2012-09-20 11:12:13 ! Terminal Message"));
            assertTrue(instance.size() == 3);
            instance.removeAll();
            assertTrue(instance.size() == 0);
        } catch (IllegalEventFormatJournalException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testSize() {
        System.out.println("Size");
        try {
            ArrayJournal instance = new ArrayJournal();
            assertTrue(instance.size() == 0);
            instance.add(new Record("2013-11-23 20:30:40 !!!!! Console Message"));
            instance.add(new Record("2011-10-23 20:30:40 . Printer Message"));
            instance.add(new Record("2012-09-20 11:12:13 ! Terminal Message"));
            assertTrue(instance.size() == 3);
        } catch (IllegalEventFormatJournalException ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void testFilter_String() throws IllegalEventFormatJournalException {
        System.out.println("Filter_String");
        ArrayJournal instance = new ArrayJournal();
        assertTrue(instance.size() == 0);
        instance.add(new Record("2013-11-23 20:30:40 !!!!! Console Critical"));
        instance.add(new Record("2011-10-23 20:30:40 . Printer Normal work"));
        instance.add(new Record("2012-09-20 11:12:13 ! Terminal Warning"));
        Journal filtered = instance.filter("23");
        assertTrue(filtered.size() == 2);
        filtered = instance.filter("2013");
        assertTrue(filtered.size() == 1);
        filtered = instance.filter("ads23");
        assertTrue(filtered.size() == 0);
    }
    
    @Test
    public void testFilter_Date_Date()
            throws IllegalEventFormatJournalException, ParseException {
        System.out.println("Filter_Date_Date");
        ArrayJournal instance = new ArrayJournal();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        assertTrue(instance.size() == 0);
        instance.add(new Record("2013-11-23 20:30:40 !!!!! Console Critical"));
        instance.add(new Record("2011-10-23 20:30:40 . Printer Normal work"));
        instance.add(new Record("2012-09-20 11:12:13 ! Terminal Warning"));
        Journal filtered = instance.filter(sdf.parse("2011-10-10"),
                sdf.parse("2013-10-10"));
        assertTrue(filtered.size() == 2);
        filtered = instance.filter(sdf.parse("2012-8-10"),
                sdf.parse("2013-10-10"));
        assertTrue(filtered.size() == 1);
        filtered = instance.filter(sdf.parse("2013-10-10"),
                sdf.parse("2013-10-10"));
        assertTrue(filtered.size() == 0);
    }

}
