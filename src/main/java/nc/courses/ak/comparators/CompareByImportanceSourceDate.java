package nc.courses.ak.comparators;

import java.util.Comparator;
import nc.courses.ak.journal.Record;

/**
 *
 * @author Alexander Kovriga
 */
public class CompareByImportanceSourceDate implements Comparator<Record> {

    @Override
    public int compare(Record o1, Record o2) {
        if (o1.getImportance() == o2.getImportance()) {
            return new CompareBySourceDate().compare(o1, o2);
        } else {
            return o1.getImportance() - o2.getImportance();
        }
    }
    
}
