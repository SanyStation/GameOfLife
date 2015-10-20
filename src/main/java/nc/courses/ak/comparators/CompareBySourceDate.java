package nc.courses.ak.comparators;

import java.util.Comparator;
import nc.courses.ak.journal.Record;

/**
 *
 * @author Alexander Kovriga
 */
public class CompareBySourceDate implements Comparator<Record> {

    @Override
    public int compare(Record o1, Record o2) {
        if (o1.getSource().equals(o2.getSource())) {
            return new CompareByDate().compare(o1, o2);
        } else {
            return o1.getSource().compareTo(o2.getSource());
        }
    }
    
}
