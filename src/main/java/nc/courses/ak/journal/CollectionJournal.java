package nc.courses.ak.journal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import nc.courses.ak.comparators.*;

/**
 *
 * @author Alexander Kovriga
 */
public class CollectionJournal implements Journal {
    
    private ArrayList<Record> array;
    
    /**
     * The class {@code ArrayJournal} represents the journal that collects
     * events and keeps them in a collection.
     * 
     * @param j the input {@code Journal}
     */
    public CollectionJournal(Journal j) {
        array = new ArrayList();
        for (int i = 0; i < j.size(); ++i) {
            array.add(j.get(i));
        }
    }
    
    /**
     * The empty constructor that creates an empty collection.
     */
    public CollectionJournal() {
        array = new ArrayList();
    }

    @Override
    public void add(Record r) {
        array.add(r);
    }

    @Override
    public void add(Journal j) {
        for (int i = 0; i < j.size(); ++i) {
            add(j.get(i));
        }
    }

    @Override
    public void remove(Record r) {
        array.remove(r);
    }

    @Override
    public Record get(int index) {
        return array.get(index);
    }

    @Override
    public void set(int index, Record record) {
        array.set(index, record);
    }

    @Override
    public void insert(int index, Record record) {
        array.add(index, record);
    }

    @Override
    public void remove(int index) {
        array.remove(index);
    }

    @Override
    public void remove(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex " + fromIndex +
                    " must be '<' or '=' toIndex " + toIndex);
        }
        for (int i = toIndex; i >= fromIndex; --i) {
            remove(i);
        }
    }

    @Override
    public void removeAll() {
        array = new ArrayList();
        System.gc();
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public Journal filter(String s) {
        CollectionJournal cj = new CollectionJournal();
        for (int i = 0; i < array.size(); ++i) {
            if (array.get(i).toString().contains(s)) {
                cj.add(array.get(i));
            }
        }
        return cj;
    }

    @Override
    public Journal filter(Date fromDate, Date toDate) {
        if (fromDate.compareTo(toDate) > 0) {
            throw new IllegalArgumentException("fromDate " + fromDate + " must"
                    + " be '<' or '=' toDate " + toDate);
        }
        Journal filtered = new CollectionJournal();
        for (int i = 0; i < array.size(); ++i) {
            if (array.get(i).getDate().compareTo(fromDate) >= 0 &&
                    array.get(i).getDate().compareTo(toDate) <= 0) {
                filtered.add(array.get(i));
            }
        }
        return filtered;
    }

    @Override
    public void sortByDate() {
        Collections.sort(array, new CompareByDate());
    }

    @Override
    public void sortByImportanceDate() {
        Collections.sort(array, new CompareByImportanceDate());
    }

    @Override
    public void sortByImportanceSourceDate() {
        Collections.sort(array, new CompareByImportanceSourceDate());
    }

    @Override
    public void sortBySourceDate() {
        Collections.sort(array, new CompareBySourceDate());
    }

    @Override
    public void printRecords() {
        for (int i = 0; i < array.size(); ++i) {
            System.out.println(array.get(i));
        }
    }
    
}
