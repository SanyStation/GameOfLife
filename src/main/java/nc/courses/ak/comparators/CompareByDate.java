package nc.courses.ak.comparators;

import java.util.Comparator;
import nc.courses.ak.journal.Record;

/**
 *
 * @author Alexander Kovriga
 */
public class CompareByDate implements Comparator<Record> {

    @Override
    public int compare(Record o1, Record o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
    
}
