package nc.courses.ak.journal;

import java.util.Arrays;
import java.util.Date;
import nc.courses.ak.comparators.*;
import nc.courses.ak.exceptions.IllegalBoundsJournalException;
import nc.courses.ak.exceptions.IndexOutOfBoundsJournalException;

/**
 * The class {@code ArrayJournal} represents the journal that collects events
 * and keeps them in a simple array.
 * 
 * @author Alexander Kovriga
 */
public class ArrayJournal implements Journal {

    private final int startSize = 100;
    private Record[] array;
    private int size;

    /**
     * The constructor accepts some {@code Journal) and creates on its basis
     * new array.
     * 
     * @param j the input {@code Journal}
     */
    public ArrayJournal(Journal j) {
        array = new Record[j.size()];
        size = array.length;
        for (int i = 0; i < size; ++i) {
            array[i] = j.get(i);
        }
    }

    /**
     * The empty constructor that creates an empty array.
     */
    public ArrayJournal() {
        array = new Record[startSize];
        size = 0;
    }

    /**
     * Increases the array in 1.5 times if needed.
     */
    private void increaseRange() {
        if (array.length == 0) {
            array = new Record[startSize];
        }
        Record[] tmp = new Record[(int) Math.round(array.length * 1.5)];
        for (int i = 0; i < array.length; ++i) {
            tmp[i] = array[i];
        }
        array = tmp;
    }

    /**
     * Decreases the array in 1.5 times if needed. If the size of the array is 0
     * the length of array becomes {@code startSize}.
     */
    private void decreaseRange() {
        if (size == 0) {
            array = new Record[startSize];
            System.gc();
        } else if (size < array.length / 2.25) {
            Record[] tmp = new Record[(int) Math.round(array.length / 1.5)];
            for (int i = 0; i < tmp.length; ++i) {
                tmp[i] = array[i];
            }
            array = tmp;
            System.gc();
        }
    }

    @Override
    public void add(Record r) {
        insert(size, r);
    }

    @Override
    public void add(Journal j) {
        while (size + j.size() > array.length) {
            increaseRange();
        }
        for (int i = 0; i < j.size(); ++i) {
            array[size + i] = j.get(i);
        }
        size += j.size();
    }

    @Override
    public void remove(Record r) {
        int position = -1;
        for (int i = 0; i < size; ++i) {
            if (array[i].equals(r)) {
                position = i;
                array[i] = null;
                break;
            }
        }
        if (position == -1) {
            return;
        }
        for (int i = position + 1; i < size; ++i) {
            array[i] = array[i + 1];
        }
        --size;
        array[size] = null;
    }

    @Override
    public Record get(int index) {
        if (index < 0 || index >= size) {
            try {
                throw new IndexOutOfBoundsJournalException("Index: " + index);
            } catch (IndexOutOfBoundsJournalException ex) {
                return null;
            }
        }
        return array[index];
    }

    @Override
    public void set(int index, Record record) {
        if (index < 0 || index >= size) {
            try {
                throw new IndexOutOfBoundsJournalException("Index: " + index);
            } catch (IndexOutOfBoundsJournalException ex) {
                return;
            }
        }
        array[index] = record;
    }

    @Override
    public void insert(int index, Record record) {
        if (index < 0 || index > size) {
            try {
                throw new IndexOutOfBoundsJournalException("Index: " + index);
            } catch (IndexOutOfBoundsJournalException ex) {
                return;
            }
        }
        if (array.length == size) {
            increaseRange();
        }
        ++size;
        for (int i = size - 1; i > index; --i) {
            array[i] = array[i - 1];
        }
        array[index] = record;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            try {
                throw new IndexOutOfBoundsJournalException("Index: " + index);
            } catch (IndexOutOfBoundsJournalException ex) {
                return;
            }
        }
        for (int i = index; i < size - 1; ++i) {
            array[i] = array[i + 1];
        }
        --size;
        array[size] = null;
        decreaseRange();
    }

    @Override
    public void remove(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex >= size) {
            try {
                throw new IndexOutOfBoundsJournalException("fromIndex: " + 
                        fromIndex);
            } catch (IndexOutOfBoundsJournalException ex) {
                return;
            }
        }
        if (toIndex < 0 || toIndex >= size) {
            try {
                throw new IndexOutOfBoundsJournalException("toIndex: " + 
                        toIndex);
            } catch (IndexOutOfBoundsJournalException ex) {
                return;
            }
        }
        if (fromIndex > toIndex) {
            try {
                throw new IllegalBoundsJournalException("fromIndex " + 
                        fromIndex + " must be '<' or '=' toIndex " + toIndex);
            } catch (IllegalBoundsJournalException ex) {
                return;
            }
        }
        int step = toIndex - fromIndex + 1;
        for (int i = fromIndex; i < size - step; ++i) {
            array[i] = array[i + step];
        }
        size -= step;
        decreaseRange();
    }

    @Override
    public void removeAll() {
        for (int i = 0; i < size; ++i) {
            array[i] = null;
        }
        size = 0;
        decreaseRange();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Journal filter(String s) {
        Journal filtered = new ArrayJournal();
        for (int i = 0; i < size; ++i) {
            if (array[i].toString().contains(s)) {
                filtered.add(array[i]);
            }
        }
        return filtered;
    }

    @Override
    public Journal filter(Date fromDate, Date toDate) {
        if (fromDate.compareTo(toDate) > 0) {
            try {
                throw new IllegalBoundsJournalException("fromDate " + fromDate +
                        " must be '<' or '=' toDate " + toDate);
            } catch (IllegalBoundsJournalException ex) {
                return null;
            }
        }
        Journal filtered = new ArrayJournal();
        for (int i = 0; i < size; ++i) {
            if (array[i].getDate().compareTo(fromDate) >= 0 &&
                    array[i].getDate().compareTo(toDate) <= 0) {
                filtered.add(array[i]);
            }
        }
        return filtered;
    }

    @Override
    public void sortByDate() {
        Arrays.sort(array, 0, size, new CompareByDate());
    }

    @Override
    public void sortByImportanceDate() {
        Arrays.sort(array, 0, size, new CompareByImportanceDate());
    }

    @Override
    public void sortByImportanceSourceDate() {
        Arrays.sort(array, 0, size, new CompareByImportanceSourceDate());
    }

    @Override
    public void sortBySourceDate() {
        Arrays.sort(array, 0, size, new CompareBySourceDate());
    }

    @Override
    public void printRecords() {
        for (int i = 0; i < size; ++i) {
            System.out.println(array[i]);
        }
    }

}
